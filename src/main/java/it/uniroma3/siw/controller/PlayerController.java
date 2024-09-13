package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PlayerService;
import it.uniroma3.siw.service.PresidentService;
import it.uniroma3.siw.service.TeamService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    
    @Autowired 
    private TeamService teamService;
    
    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private PresidentService presidentService;

    @GetMapping("/president/formNewPlayer")
    public String showInsertPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "president/formNewPlayer";
    }

    @PostMapping("/president/formNewPlayer")
    public String savePlayer(@ModelAttribute("player") Player player, Principal principal) {
        // Recupera il nome utente del presidente corrente
        String username = principal.getName();
        
        // Trova le credenziali utilizzando il nome utente
        Optional<Credentials> credentialsOpt = credentialsService.findByUsername(username);
        
        if (credentialsOpt.isPresent()) {
            Credentials credentials = credentialsOpt.get();
            President president = credentials.getPresident();
            
            if (president != null) {
                // Imposta automaticamente il team del presidente per il nuovo giocatore
                player.setTeam(president.getTeam());
                
                // Salva il giocatore
                playerService.savePlayer(player);
                
                return "redirect:/president/indexPlayers";
            } else {
                throw new IllegalStateException("President not found for the given credentials");
            }
        } else {
            throw new IllegalStateException("Credentials not found for the username: " + username);
        }
    }





    @GetMapping("/president/formDeletePlayer")
    public String showDeletePlayerForm(Model model) {
        model.addAttribute("players", playerService.getAllPlayers());
        return "president/formDeletePlayer";
    }

    @PostMapping("/president/deletePlayer")
    public String deletePlayer(@RequestParam("playerId") Long playerId) {
        if (playerId != null) {
            playerService.deleteById(playerId);
        }
        return "redirect:/president/indexPlayers";
    }

   

    @GetMapping("/players/{id}")
    public String getPlayer(@PathVariable("id") Long id, Model model, Principal principal) {
        String username = principal.getName();
        Optional<President> optionalPresident = presidentService.findByUsername(username);

        if (optionalPresident.isPresent()) {
            President president = optionalPresident.get();
            Long teamId = president.getTeam().getId();
            Optional<Player> optionalPlayer = playerService.findById(id);

            if (optionalPlayer.isPresent() && optionalPlayer.get().getTeam().getId().equals(teamId)) {
                model.addAttribute("player", optionalPlayer.get());
                return "player";
            }
        }
        return "redirect:/players";
    }
    
    @GetMapping("/players")
    public String listPlayers(Model model, Principal principal) {
        String username = principal.getName();
        Optional<President> presidentOpt = presidentService.findByUsername(username);

        if (presidentOpt.isPresent()) {
            President president = presidentOpt.get();
            
            if (president.getTeam() != null) {
                Long teamId = president.getTeam().getId();
                List<Player> players = playerService.findPlayersForLoggedInPresident(teamId);
                
                model.addAttribute("players", players); // Passa i giocatori al modello
            } else {
                model.addAttribute("players", Collections.emptyList()); // Passa una lista vuota
                model.addAttribute("errorMessage", "Non sei associato a nessun team.");
            }
        } else {
            model.addAttribute("players", Collections.emptyList()); // Passa una lista vuota
            model.addAttribute("errorMessage", "Presidente non trovato.");
        }

        return "players"; // Nome del template Thymeleaf
    }



}
