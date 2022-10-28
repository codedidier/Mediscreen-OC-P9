package com.codedidier.mspatient.exception;

/**
 * Erreur créer lorsqu'un patient n'a pas été trouvée
 */
public class PatientNotFoundException extends RuntimeException {

    private final long id;
    public PatientNotFoundException(long id) {
        this.id = id;
    }
    @Override
    public String getMessage(){
        return "this id : " + id + ", not found in database";
    }
}
