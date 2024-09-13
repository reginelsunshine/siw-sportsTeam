package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PresidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PresidentController {

    @Autowired
    private PresidentService presidentService;
    
    @Autowired CredentialsService credentialsService;

    @GetMapping("/indexPresident")
    public String showIndexPresident(Model model) {
        return "admin/indexPresident";
    }

    @PostMapping("/login")
    public String loginPresident(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 Model model) {
        // Trova le credenziali per lo username dato
        Optional<Credentials> credentialsOpt = credentialsService.findByUsername(username);

        if (credentialsOpt.isPresent()) {
            Credentials credentials = credentialsOpt.get();

            // Verifica se la password fornita corrisponde alla password memorizzata
            if (credentials.getPassword().equals(password) && Credentials.PRESIDENT_ROLE.equals(credentials.getRole())) {
                // Trova il presidente usando lo username
                Optional<President> presidentOpt = presidentService.findByUsername(username);

                if (presidentOpt.isPresent()) {
                    return "redirect:/president/indexPlayers";
                } else {
                    model.addAttribute("error", "Presidente non trovato.");
                    return "login";
                }
            } else {
                model.addAttribute("error", "Password errata.");
                return "login";
            }
        } else {
            model.addAttribute("error", "Username non trovato.");
            return "login";
        }
    }

    
    // Metodo per accedere alla pagina indexPlayers
    @GetMapping("/president/indexPlayers")
    public String showIndexPlayers(Model model) {
        // Eventuali attributi da aggiungere al model
        return "president/indexPlayers";  // Assicurati che questa sia la vista corretta
    }
}
