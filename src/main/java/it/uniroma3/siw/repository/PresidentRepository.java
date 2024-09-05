package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.President;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentRepository extends CrudRepository<President, Long> {

    President findByFiscalCode(String fiscalCode);

    President findByFirstNameAndLastName(String firstName, String lastName);
}
