package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.President;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class PresidentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return President.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        President president = (President) target;

        String firstName = president.getFirstName().trim();
        String lastName = president.getLastName().trim();
        String fiscalCode = president.getFiscalCode().trim();
        LocalDate dateOfBirth = president.getDateOfBirth();

        if (firstName.isEmpty()) {
            errors.rejectValue("firstName", "required");
        }

        if (lastName.isEmpty()) {
            errors.rejectValue("lastName", "required");
        }

        if (fiscalCode.isEmpty()) {
            errors.rejectValue("fiscalCode", "required");
        } else if (fiscalCode.length() != 16) {
            errors.rejectValue("fiscalCode", "size");
        }

        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            errors.rejectValue("dateOfBirth", "invalid");
        }
    }
}
