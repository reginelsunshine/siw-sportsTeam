package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.President;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentRepository extends CrudRepository<President, Long> {

	   Optional<President> findByFiscalCode(String fiscalCode);

    President findByFirstNameAndLastName(String firstName, String lastName);
    
   
    
    Optional<President> findByFiscalCodeAndPresidentCode(String fiscalCode, String presidentCode);
  
}