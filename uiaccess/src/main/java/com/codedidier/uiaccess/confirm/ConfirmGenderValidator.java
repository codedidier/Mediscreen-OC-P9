package com.codedidier.uiaccess.confirm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ConfirmGenderValidator implements ConstraintValidator<ConfirmGender, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        String regex = "^[M|F]$";
        Pattern pattern = Pattern.compile(regex);

        if (pattern.matcher(gender).matches()) {
            return true;
        }
        return false;
    }
}
