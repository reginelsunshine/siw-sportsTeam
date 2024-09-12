package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.President;
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

    @GetMapping("/indexPresident")
    public String showIndexPresident(Model model) {
        return "admin/indexPresident";
    }

    @PostMapping("/login")
    public String loginPresident(@RequestParam("fiscalCode") String fiscalCode,
                                 @RequestParam("presidentCode") String presidentCode,
                                 Model model) {
        Optional<President> presidentOpt = presidentService.findByFiscalCodeAndPresidentCode(fiscalCode, presidentCode);

        if (presidentOpt.isPresent()) {
            President president = presidentOpt.get();
            return "redirect:/president/indexPlayers";
        } else {
            model.addAttribute("error", "Codice fiscale o codice presidente errati.");
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
