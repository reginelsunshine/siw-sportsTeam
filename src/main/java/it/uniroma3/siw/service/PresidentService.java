package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.repository.PresidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PresidentService {

    @Autowired
    private PresidentRepository presidentRepository;
    
    @Autowired  
    private CredentialsService credentialsService;

    public Optional<President> findByFiscalCode(String fiscalCode) {
        return presidentRepository.findByFiscalCode(fiscalCode);
    }

    public Optional<President> findByUsername(String username) {
        Optional<Credentials> credentials = credentialsService.findByUsername(username);
        return credentials.map(Credentials::getPresident);
    }
    public void savePresident(President president) {
        presidentRepository.save(president);
    }

    public Optional<President> findByFiscalCodeAndPresidentCode(String fiscalCode, String presidentCode) {
        return presidentRepository.findByFiscalCodeAndPresidentCode(fiscalCode, presidentCode);
    }

    public Optional<President> findByPresidentCode(String presidentCode) {
        return presidentRepository.findByPresidentCode(presidentCode);
    }
    
    public Optional<President> findById(Long id) {
        return presidentRepository.findById(id);
    }
}
