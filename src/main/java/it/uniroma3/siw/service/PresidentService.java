package it.uniroma3.siw.service;

import it.uniroma3.siw.model.President;
import it.uniroma3.siw.repository.PresidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresidentService {

    @Autowired
    private PresidentRepository presidentRepository;

    public List<President> getAllPresidents() {
        return (List<President>) presidentRepository.findAll();
    }

    public President getPresidentById(Long id) {
        Optional<President> result = presidentRepository.findById(id);
        return result.orElse(null);
    }

    public President savePresident(President president) {
        return presidentRepository.save(president);
    }

    public President updatePresident(Long id, President president) {
        Optional<President> existingPresident = presidentRepository.findById(id);
        if (existingPresident.isPresent()) {
            President updatedPresident = existingPresident.get();
            updatedPresident.setFirstName(president.getFirstName());
            updatedPresident.setLastName(president.getLastName());
            updatedPresident.setFiscalCode(president.getFiscalCode());
            updatedPresident.setDateOfBirth(president.getDateOfBirth());
            updatedPresident.setPlaceOfBirth(president.getPlaceOfBirth());
            updatedPresident.setTeam(president.getTeam());
            return presidentRepository.save(updatedPresident);
        } else {
            return null;
        }
    }

    public void deletePresident(Long id) {
        presidentRepository.deleteById(id);
    }
}
