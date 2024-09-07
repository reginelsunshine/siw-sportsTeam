package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Mostra tutti i team
    @GetMapping
    public String getAllTeams(Model model) {
        List<Team> teams = teamService.findAllTeams();
        model.addAttribute("teams", teams);
        return "teams.html"; // Nome della vista Thymeleaf per visualizzare la lista dei team
    }

    // Mostra il form per creare un nuovo team
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("team", new Team());
        return "teamForm"; // Nome della vista Thymeleaf per il form di creazione di un team
    }

    // Gestisce l'invio del form per creare un nuovo team
    @PostMapping
    public String createTeam(@Valid @ModelAttribute("team") Team team, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "teamForm"; // Ritorna al form se ci sono errori di validazione
        }
        teamService.saveTeam(team);
        return "redirect:/teams"; // Reindirizza alla lista dei team dopo la creazione
    }

    // Mostra i dettagli di un team specifico
    @GetMapping("/team/{id}")
    public String getTeamDetails(@PathVariable("id") Long id, Model model) {
        Team team = teamService.findTeamById(id);
        if (team != null) {
            model.addAttribute("team", team);
            return "team"; // Nome della vista Thymeleaf per i dettagli del team
        } else {
            return "redirect:/teams"; // Reindirizza alla lista se il team non è trovato
        }
    }

    // Mostra il form per modificare un team esistente
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Team team = teamService.findTeamById(id);
        if (team != null) {
            model.addAttribute("team", team);
            return "teamForm"; // Riutilizza il form per la creazione anche per la modifica
        } else {
            return "redirect:/teams"; // Reindirizza alla lista se il team non è trovato
        }
    }

    // Gestisce l'invio del form per aggiornare un team esistente
    @PostMapping("/{id}")
    public String updateTeam(@PathVariable("id") Long id, @Valid @ModelAttribute("team") Team team, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "teamForm"; // Ritorna al form se ci sono errori di validazione
        }
        team.setId(id); // Imposta l'ID per assicurare che venga aggiornato l'entità esistente
        teamService.saveTeam(team);
        return "redirect:/teams"; // Reindirizza alla lista dei team dopo l'aggiornamento
    }

    // Cancella un team
    @GetMapping("/{id}/delete")
    public String deleteTeam(@PathVariable("id") Long id) {
        teamService.deleteTeamById(id);
        return "redirect:/teams"; // Reindirizza alla lista dei team dopo la cancellazione
    }
}
