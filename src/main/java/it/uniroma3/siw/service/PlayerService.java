package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PresidentService presidentService;


    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void deleteById(Long playerId) {
        if (playerId != null) {
            playerRepository.deleteById(playerId);
        }
    }

    public List<Player> findPlayersForLoggedInPresident(Long teamId) {
        if (teamId != null) {
            return playerRepository.findByTeamId(teamId);
        }
        return Collections.emptyList();
    }

    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }
    
 // Trova tutti i giocatori associati a un determinato team
    public List<Player> findByTeam(Team team) {
        return playerRepository.findByTeam(team);
    }
}
