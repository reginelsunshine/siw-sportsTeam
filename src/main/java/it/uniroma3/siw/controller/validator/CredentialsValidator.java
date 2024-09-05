package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Credentials;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CredentialsValidator implements Validator {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 20;

    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Credentials credentials = (Credentials) target;

        String username = credentials.getUsername().trim();
        String password = credentials.getPassword().trim();

        if (username.isEmpty()) {
            errors.rejectValue("username", "required");
        }

        if (password.isEmpty()) {
            errors.rejectValue("password", "required");
        } else if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            errors.rejectValue("password", "size");
        }
    }
}
