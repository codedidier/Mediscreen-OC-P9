package com.codedidier.mspatient.exception;

/**
 * Erreur créer lorsqu'un patient existe déjà
 */
public class PersonAlreadyExistException extends RuntimeException {

    private final String firstName;
    private final String lastName;

    public PersonAlreadyExistException(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getMessage() {
        return "this user : " + firstName + " " + lastName  + ", was already present in database";
    }
}
