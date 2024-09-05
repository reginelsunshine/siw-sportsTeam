package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Team;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeamValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Team team = (Team) target;

        String name = team.getName().trim();
        int foundationYear = team.getFoundationYear();
        String address = team.getAddress().trim();

        if (name.isEmpty()) {
            errors.rejectValue("name", "required");
        }

        if (foundationYear <= 0) {
            errors.rejectValue("foundationYear", "invalid");
        }

        if (address.isEmpty()) {
            errors.rejectValue("address", "required");
        }
    }
}
