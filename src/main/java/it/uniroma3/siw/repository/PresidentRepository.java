package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.President;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PresidentRepository extends CrudRepository<President, Long> {
    Optional<President> findByFiscalCode(String fiscalCode);
    Optional<President> findByUsername(String username);
    Optional<President> findByFiscalCodeAndPresidentCode(String fiscalCode, String presidentCode);
    Optional<President> findByPresidentCode(String presidentCode);
}
