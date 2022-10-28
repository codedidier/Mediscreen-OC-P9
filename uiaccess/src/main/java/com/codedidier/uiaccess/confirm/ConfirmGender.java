package com.codedidier.uiaccess.confirm;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmGenderValidator.class)
public @interface ConfirmGender {
    String message() default "Genre : M ou F attendu";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
