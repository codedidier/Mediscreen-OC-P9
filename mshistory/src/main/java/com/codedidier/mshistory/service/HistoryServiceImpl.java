package com.codedidier.mshistory.service;

import com.codedidier.mshistory.HistoryRepository.HistoryRepository;
import com.codedidier.mshistory.document.Note;
import com.codedidier.mshistory.exception.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service qui à la responsabilité des historiques
 */
@Slf4j
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /**
     * Récupère un historique par l'ID du patient
     */
    @Override
    public List<Note> findAllNotesByPatientId(Integer patientId) {
        log.debug("service : get the list of all notes by PatientId");
        return historyRepository.findAllNotesByPatientId(patientId);
    }

    /**
     * Récupère un historique par ID
     */
    @Override
    public Note getNoteById(String id) {
        log.debug("service : get note by id : " + id);
        return getById(id);
    }

    /**
     * Ajoute un historique à un patient
     */
    @Override
    public Note saveNote(Note note) {
        Note noteAdded = historyRepository.save(note);
        log.debug("service : add patient : " + note);
        return noteAdded;
    }

    /**
     * Mise à jour du document
     */
    @Override
    public Note updateNote(String id, Note updateNote) {
        Note dBNote = getNoteById(id);
        dBNote.setPatientId(updateNote.getPatientId());
        dBNote.setRecommendation(updateNote.getRecommendation());
        log.debug("service : update note : " + updateNote);
        return historyRepository.save(dBNote);
    }

    /**
     * Récupère un historique par ID
     */
    private Note getById(String id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }
}
