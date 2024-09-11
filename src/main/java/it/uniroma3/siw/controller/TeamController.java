package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TeamController {

    @GetMapping("/indexTeams")
    public String indexTeams() {
        return "admin/indexTeams"; // Questo deve corrispondere alla struttura della cartella dei template
    }
}
