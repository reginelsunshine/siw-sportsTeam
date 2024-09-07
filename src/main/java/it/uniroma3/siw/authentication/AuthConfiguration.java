package it.uniroma3.siw.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
            .usersByUsernameQuery("SELECT username, password, 1 AS enabled FROM credentials WHERE username=?");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().and().cors().disable()
            .authorizeHttpRequests()
                // Permetti l'accesso alle pagine pubbliche
                .requestMatchers(HttpMethod.GET, "/", "/index", "/register", "/css/**", "/images/**", "favicon.ico").permitAll()
                .requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                // Permetti solo agli utenti con il ruolo ADMIN di accedere a queste pagine
                .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority("ADMIN_ROLE")
                .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority("ADMIN_ROLE")
                // Permetti solo ai presidenti di accedere agli endpoint di gestione giocatori
                .requestMatchers(HttpMethod.GET, "/teams/{id}/addPlayer", "/teams/{id}/removePlayer").hasAuthority("PRESIDENT_ROLE")
                .requestMatchers(HttpMethod.POST, "/teams/{id}/addPlayer", "/teams/{id}/removePlayer").hasAuthority("PRESIDENT_ROLE")
                // Permetti a tutti gli utenti autenticati di accedere alle altre pagine
                .anyRequest().authenticated()
                // Configura la pagina di login
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/success", true)
                .failureUrl("/login?error=true")
                // Configura la pagina di logout
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true).permitAll();
        return httpSecurity.build();
    }
}
