package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.model.Team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> findByFirstName(String firstName);

    List<Player> findByLastName(String lastName);

    List<Player> findByRole(String role);

    List<Player> findByTeamId(Long teamId);
    
    List<Player> findByTeam(Team team);
}