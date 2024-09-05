package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Player;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class PlayerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;

        String firstName = player.getFirstName().trim();
        String lastName = player.getLastName().trim();
        LocalDate dateOfBirth = player.getDateOfBirth();
        String role = player.getRole().trim();

        if (firstName.isEmpty()) {
            errors.rejectValue("firstName", "required");
        }

        if (lastName.isEmpty()) {
            errors.rejectValue("lastName", "required");
        }

        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            errors.rejectValue("dateOfBirth", "invalid");
        }

        if (role.isEmpty()) {
            errors.rejectValue("role", "required");
        }
    }
}
