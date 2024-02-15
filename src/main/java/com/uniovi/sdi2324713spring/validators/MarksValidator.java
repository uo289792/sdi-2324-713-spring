package com.uniovi.sdi2324713spring.validators;

import com.uniovi.sdi2324713spring.entities.Mark;
import com.uniovi.sdi2324713spring.services.MarksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MarksValidator implements Validator {
    private final MarksService marksService;
    public MarksValidator(MarksService marksService) {
        this.marksService = marksService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "score", "Error.empty");

        if (mark.getDescription().length() < 20) {
            errors.rejectValue("description", "Error.marks.description.length");
        }

        if (mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.marks.score.range");
        }

    }
}

