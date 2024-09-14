package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public String getTeams(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "teams";
    }

    @GetMapping("/teams/{id}")
    public String getTeam(@PathVariable("id") Long id, Model model) {
        Team team = teamService.findById(id).orElse(null);
        if (team == null) {
            return "redirect:/teams";
        }
        model.addAttribute("team", team);
        return "team";
    }
    
    @GetMapping("/admin/formNewTeam")
    public String showInsertTeamForm(Model model) {
        model.addAttribute("team", new Team());
        return "admin/formNewTeam";
    }

    @PostMapping("/admin/formNewTeam")
    public String insertTeam(@Valid @ModelAttribute("team") Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/formNewTeam";
        }
        teamService.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/admin/indexTeams")
    public String indexTeams(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "admin/indexTeams";
    }

    @GetMapping("/admin/formUpdateTeam")
    public String showUpdateTeamForm(Model model, @RequestParam(value = "teamId", required = false) Long teamId) {
        if (teamId != null) {
            Team team = teamService.findById(teamId).orElse(null);
            if (team != null) {
                model.addAttribute("team", team);
                return "admin/formUpdateTeamDetails";
            }
            return "redirect:/admin/formUpdateTeam";
        }

        model.addAttribute("teams", teamService.findAll());
        return "admin/formUpdateTeam";
    }

    @GetMapping("/admin/formUpdateTeamDetails/{id}")
    public String editTeam(@PathVariable("id") Long id, Model model) {
        Team team = teamService.findById(id).orElse(null);
        if (team == null) {
            return "redirect:/admin/formUpdateTeam";
        }
        model.addAttribute("team", team);
        return "admin/formUpdateTeamDetails";
    }

    @PostMapping("/admin/formUpdateTeamDetails/{id}")
    public String updateTeam(@PathVariable("id") Long id, @Valid @ModelAttribute("team") Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/formUpdateTeamDetails";
        }
        Team existingTeam = teamService.findById(id).orElse(null);
        if (existingTeam != null) {
            existingTeam.setName(team.getName());
            existingTeam.setFoundationYear(team.getFoundationYear());
            existingTeam.setAddress(team.getAddress());
            existingTeam.setPresident(team.getPresident());
            teamService.save(existingTeam);
        }
        return "redirect:/teams";
    }
    

    

    @GetMapping("/admin/formDeleteTeam")
    public String showDeleteTeamForm(Model model) {
        model.addAttribute("teams", teamService.findAll()); // Passa tutte le squadre alla vista
        return "admin/formDeleteTeam";
    }

    @PostMapping("/admin/deleteTeam")
    public String deleteTeam(@RequestParam("teamId") Long teamId, Model model) {
        Team team = teamService.findById(teamId).orElse(null);
        if (team == null) {
            model.addAttribute("errorMessage", "Team non trovato.");
            return "admin/indexTeams";
        }

        try {
            teamService.deleteTeam(teamId);
            model.addAttribute("successMessage", "Team eliminato con successo!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Errore durante l'eliminazione del team. Assicurati che non ci siano relazioni attive.");
        }

        return "redirect:/admin/indexTeams"; // Torna alla pagina delle squadre dopo l'eliminazione
    }



    
}
