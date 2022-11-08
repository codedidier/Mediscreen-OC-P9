package com.codedidier.mshistory.exception;

/**
 * Erreur créer lorsque qu'une note n'a pas été trouvée
 */
public class NoteNotFoundException extends RuntimeException {

    private final String id;

    public NoteNotFoundException(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "this id : " + id + ", was not found in database";
    }
}
