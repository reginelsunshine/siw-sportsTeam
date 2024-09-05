package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findByName(String name);

    List<Team> findByFoundationYear(int foundationYear);

    List<Team> findByPresidentId(Long presidentId);
    
    List<Team> findAll();
}
