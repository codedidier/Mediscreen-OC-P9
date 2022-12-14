package com.codedidier.uiaccess.confirm;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmBirthDateValidator.class)
public @interface ConfirmBirthDate {

    String message() default "Veuillez entrer une date de naissance qui suit le format \"yyyy-mm-dd\" pattern's";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
