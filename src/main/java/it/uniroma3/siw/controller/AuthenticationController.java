package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@Autowired
	private CredentialsService credentialsService;

    @Autowired
	private UserService userService;

    @GetMapping(value = "/register") 
    public String showRegisterForm(Model model) {
        model.addAttribute("president", new User());  // Qui creiamo un nuovo oggetto User per il presidente
        return "formRegister";
    }

	
	@GetMapping(value = "/login")
	public String showLoginForm(Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
	        // Se l'utente è autenticato, passiamo i dettagli dell'utente al modello
	        model.addAttribute("userDetails", authentication.getPrincipal());
	        return "redirect:/"; // Redirect o altra pagina se l'utente è già loggato
	    }
	    return "formLogin";
	}

	
	@GetMapping(value = "/admin/indexAdmin") 
	public String showIndexAdmin(Model model) {
		return "indexAdmin";
	}
	
	@GetMapping(value = "/index") 
	public String showIndex(Model model) {
		return "index";
	}

	@GetMapping(value = "/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
	        return "index.html";
		} else {	
			Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
			String loggedUser = userDetails.getName();
			Credentials credentials = credentialsService.getCredentials(loggedUser);
			
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				return "admin/indexAdmin.html";
			} else if (credentials.getRole().equals("PRESIDENT")) {
				return "president/indexPresident.html";
			}
		}
        return "index.html";
	}

    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
    	Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = userDetails.getName();
    	Credentials credentials = credentialsService.getCredentials(loggedUser);
    	
    	if (credentials != null) {
    		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
                return "admin/indexAdmin.html";
            } else if (credentials.getRole().equals("PRESIDENT")) {
                return "president/indexPresident.html";
            }
    	}
        return "index.html";
    }
     
    @PostMapping(value = { "/register" })
    public String registerUser(@Valid @ModelAttribute("president") User president,
                               BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "formRegister";
        }
        
        // Logica per salvare l'utente presidente
        userService.saveUser(president);
        
        return "registrationSuccessful";
    }

}
