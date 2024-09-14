package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.dto.PresidentRegistrationDTO;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PresidentService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CredentialsValidator credentialsValidator;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PresidentService presidentService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("presidentRegistration", new PresidentRegistrationDTO());
        return "formRegister";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("presidentRegistration") PresidentRegistrationDTO dto,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            return "formRegister";
        }

        // Create and save the User (president)
        User user = new User();
        user.setName(dto.getFirstName());
        user.setSurname(dto.getLastName());
        user.setUsername(dto.getUsername());
        userService.saveUser(user);

        // Create and save the Credentials
        Credentials credentials = new Credentials();
        credentials.setUsername(dto.getUsername());
        credentials.setRole(Credentials.PRESIDENT_ROLE);
        credentials.setPassword(credentialsService.encodePassword(dto.getPassword()));
        credentialsService.saveCredentials(credentials);

        // Create and save the President
        President president = new President();
        president.setFirstName(dto.getFirstName());
        president.setLastName(dto.getLastName());
        president.setFiscalCode(dto.getFiscalCode());
        president.setDateOfBirth(dto.getDateOfBirth());
        president.setPresidentCode(dto.getPresidentCode());
        president.setUsername(dto.getUsername());
        presidentService.savePresident(president);

        // Associate credentials with the president and the user
        credentials.setPresident(president);
        credentials.setUser(user);
        credentialsService.saveCredentials(credentials);

        return "registrationSuccessful";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            // Se l'utente è autenticato, passiamo i dettagli dell'utente al modello
            model.addAttribute("userDetails", authentication.getPrincipal());
            return "redirect:/"; // Redirect o altra pagina se l'utente è già loggato
        }
        return "formLogin";
    }

    @GetMapping("/admin/indexAdmin")
    public String showIndexAdmin(Model model) {
        return "indexAdmin";
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "index.html";
        } else {
            Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
            String loggedUser = userDetails.getName();
            Credentials credentials = credentialsService.getCredentials(loggedUser);

            if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
                return "admin/indexAdmin.html";
            } else if (credentials.getRole().equals("PRESIDENT")) {
                return "president/indexPresident.html";
            }
        }
        return "index.html";
    }

    @GetMapping("/success")
    public String defaultAfterLogin(Model model) {
        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        String loggedUser = userDetails.getName();
        Credentials credentials = credentialsService.getCredentials(loggedUser);

        if (credentials != null) {
            if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
                return "admin/indexAdmin.html";
            } else if (credentials.getRole().equals("PRESIDENT")) {
                return "president/indexPresident.html";
            }
        }
        return "index.html";
    }
}
