package it.uniroma3.siw.service;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.repository.PresidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PresidentService {

    @Autowired
    private PresidentRepository presidentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<President> findByFiscalCode(String fiscalCode) {
        return presidentRepository.findByFiscalCode(fiscalCode);
    }

    public boolean authenticatePresident(President president) {
        Optional<President> foundPresident = presidentRepository.findByFiscalCodeAndPresidentCode(
                president.getFiscalCode(), president.getPresidentCode());
        return foundPresident.isPresent();
    }

    public boolean registerPresident(President president, String presidentCode) {
        if (presidentRepository.findByFiscalCode(president.getFiscalCode()).isPresent()) {
            return false; // Il presidente esiste già
        }
        // Imposta la password e codifica
        president.setPasswordHash(passwordEncoder.encode(presidentCode));
        presidentRepository.save(president);
        return true;
    }

    public Optional<President> findByFiscalCodeAndPresidentCode(String fiscalCode, String presidentCode) {
        return presidentRepository.findByFiscalCodeAndPresidentCode(fiscalCode, presidentCode);
    }
    
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
