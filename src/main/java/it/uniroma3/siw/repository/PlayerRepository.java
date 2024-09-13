package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> findAll();

    @Query("SELECT p FROM Player p WHERE p.team.id = :teamId")
    List<Player> findByTeamId(@Param("teamId") Long teamId);
    
    List<Player> findByTeam(Team team);
    
    
}
