package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.service.PlayerService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/president/formNewPlayer")
    public String showInsertPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "president/formNewPlayer";
    }

    @PostMapping("/president/formNewPlayer")
    public String insertPlayer(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "president/formNewPlayer";
        }
        playerService.save(player);
        return "redirect:/president/indexPlayers";
    }

    @GetMapping("/president/formDeletePlayer")
    public String showDeletePlayerForm(Model model) {
        model.addAttribute("players", playerService.getAllPlayers());
        return "president/formDeletePlayer";
    }

    @PostMapping("/president/deletePlayer")
    public String deletePlayer(@RequestParam("playerId") Long playerId) {
        playerService.deleteById(playerId);
        return "redirect:/president/indexPlayers";
    }
}
