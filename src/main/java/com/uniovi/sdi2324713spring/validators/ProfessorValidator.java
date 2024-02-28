package com.uniovi.sdi2324713spring.validators;

import com.uniovi.sdi2324713spring.entities.Professor;
import com.uniovi.sdi2324713spring.services.ProfessorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfessorValidator implements Validator {
    private final ProfessorsService professorsService;
    public ProfessorValidator(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Professor.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "Error.empty");

        if (!Character.isLetter(professor.getDni().charAt(professor.getDni().length() - 1))
                || professor.getDni().length() != 9) {
            errors.rejectValue("dni", "Error.professor.dni.format");}
        if (professorsService.getProfessorByDni(professor.getDni()) != null) {
            errors.rejectValue("dni", "Error.professor.dni.duplicate");}

    }
}

