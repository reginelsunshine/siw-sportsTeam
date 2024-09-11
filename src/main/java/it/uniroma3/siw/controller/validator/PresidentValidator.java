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

        // Recupero dei dati del presidente
        String firstName = president.getFirstName().trim();
        String lastName = president.getLastName().trim();
        String fiscalCode = president.getFiscalCode().trim();
        String presidentCode = president.getPresidentCode().trim();
        LocalDate dateOfBirth = president.getDateOfBirth();

        // Validazione del nome
        if (firstName.isEmpty()) {
            errors.rejectValue("firstName", "required", "Il nome è obbligatorio.");
        }

        // Validazione del cognome
        if (lastName.isEmpty()) {
            errors.rejectValue("lastName", "required", "Il cognome è obbligatorio.");
        }

        // Validazione del codice fiscale
        if (fiscalCode.isEmpty()) {
            errors.rejectValue("fiscalCode", "required", "Il codice fiscale è obbligatorio.");
        } else if (fiscalCode.length() != 16) {
            errors.rejectValue("fiscalCode", "invalid", "Il codice fiscale deve essere lungo 16 caratteri.");
        }

        // Validazione del codice presidente
        if (presidentCode.isEmpty()) {
            errors.rejectValue("presidentCode", "required", "Il codice presidente è obbligatorio.");
        }

        // Validazione della data di nascita
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            errors.rejectValue("dateOfBirth", "invalid", "La data di nascita non può essere futura.");
        }
    }
}
