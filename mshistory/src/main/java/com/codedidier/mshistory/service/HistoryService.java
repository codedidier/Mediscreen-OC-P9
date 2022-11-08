package com.codedidier.mshistory.service;

import com.codedidier.mshistory.document.Note;

import java.util.List;

public interface HistoryService {

    List<Note> findAllNotesByPatientId(Integer patientId);

    Note saveNote(Note note);

    Note getNoteById(String id);

    Note updateNote(String id, Note note);

    void deleteNote(String id);
}
