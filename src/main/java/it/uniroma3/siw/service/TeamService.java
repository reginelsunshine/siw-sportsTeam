package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    // Trova tutti i team
    public List<Team> findAll() {
        return (List<Team>) teamRepository.findAll();
    }

    // Trova un team per ID
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    // Salva un team
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    // Aggiorna un team esistente
    public Team update(Long id, Team updatedTeam) {
        Optional<Team> existingTeam = teamRepository.findById(id);
        if (existingTeam.isPresent()) {
            Team team = existingTeam.get();
            team.setName(updatedTeam.getName());
            team.setFoundationYear(updatedTeam.getFoundationYear());
            team.setAddress(updatedTeam.getAddress());
            team.setPresident(updatedTeam.getPresident());
            // Nota: Non si aggiorna players perché la relazione è gestita in modo unidirezionale.
            return teamRepository.save(team);
        } else {
            return null;
        }
    }

    // Cancella un team per ID
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
