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

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Team findTeamById(Long id) {
        Optional<Team> result = teamRepository.findById(id);
        return result.orElse(null);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team team) {
        Optional<Team> existingTeam = teamRepository.findById(id);
        if (existingTeam.isPresent()) {
            Team updatedTeam = existingTeam.get();
            updatedTeam.setName(team.getName());
            updatedTeam.setFoundationYear(team.getFoundationYear());
            updatedTeam.setAddress(team.getAddress());
            updatedTeam.setPlayers(team.getPlayers());
            updatedTeam.setPresident(team.getPresident());
            return teamRepository.save(updatedTeam);
        } else {
            return null;
        }
    }

    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}
