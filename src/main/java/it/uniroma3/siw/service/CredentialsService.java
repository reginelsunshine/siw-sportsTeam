package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {

    @Autowired
    protected PasswordEncoder passwordEncoder;
    

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void saveCredentials(Credentials credentials) {
        credentialsRepository.save(credentials);
    }
    

    @Autowired
    protected CredentialsRepository credentialsRepository;

    @Transactional
    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = this.credentialsRepository.findById(id);
        return result.orElse(null);
    }

  /*  @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }*/
    
    public Credentials getCredentials(String username) {
        // Questo metodo ora restituisce Optional<Credentials> 
        Optional<Credentials> optionalCredentials = credentialsRepository.findByUsername(username);
        
        // Estrai l'oggetto Credentials dall'Optional
        return optionalCredentials.orElse(null);
    }
    
    public Optional<Credentials> findByPassword(String password) {
        return credentialsRepository.findByPassword(password);
    }
    
    public Optional<Credentials> findByUsername(String username) {
        return credentialsRepository.findByUsername(username);
    }
}
