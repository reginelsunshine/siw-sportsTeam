package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        Optional<Player> result = playerRepository.findById(id);
        return result.orElse(null);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player player) {
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            Player updatedPlayer = existingPlayer.get();
            updatedPlayer.setFirstName(player.getFirstName());
            updatedPlayer.setLastName(player.getLastName());
            updatedPlayer.setDateOfBirth(player.getDateOfBirth());
            updatedPlayer.setPlaceOfBirth(player.getPlaceOfBirth());
            updatedPlayer.setRole(player.getRole());
            updatedPlayer.setStartDateOfRegistration(player.getStartDateOfRegistration());
            updatedPlayer.setEndDateOfRegistration(player.getEndDateOfRegistration());
            updatedPlayer.setTeam(player.getTeam());
            return playerRepository.save(updatedPlayer);
        } else {
            return null;
        }
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
