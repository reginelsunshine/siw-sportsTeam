package it.uniroma3.siw.controller;


import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public String listPlayers(Model model) {
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "players/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("player", new Player());
        return "players/create";
    }

    @PostMapping
    public String createPlayer(@ModelAttribute("player") Player player) {
        playerService.savePlayer(player);
        return "redirect:/players";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Player player = playerService.getPlayerById(id);
        model.addAttribute("player", player);
        return "players/edit";
    }

    @PostMapping("/update/{id}")
    public String updatePlayer(@PathVariable("id") Long id, @ModelAttribute("player") Player player) {
        playerService.updatePlayer(id, player);
        return "redirect:/players";
    }

    @GetMapping("/delete/{id}")
    public String deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
        return "redirect:/players";
    }
}
