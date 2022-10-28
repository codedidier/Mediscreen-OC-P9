package com.codedidier.uiaccess.confirm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConfirmBirthDateValidator implements ConstraintValidator <ConfirmBirthDate, String>{

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateOfBirth, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
