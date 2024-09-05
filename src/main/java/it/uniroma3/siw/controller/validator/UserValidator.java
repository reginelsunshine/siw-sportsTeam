package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        String name = user.getName().trim();
        String surname = user.getSurname().trim();

        if (name.isEmpty()) {
            errors.rejectValue("name", "required");
        }

        if (surname.isEmpty()) {
            errors.rejectValue("surname", "required");
        }
    }
}
