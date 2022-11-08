package com.codedidier.mshistory.HistoryRepository;

import com.codedidier.mshistory.document.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoryRepository  extends MongoRepository<Note, String> {

    /**
     * Récupère un historique avec l'ID d'un patient
     */
    List<Note> findAllNotesByPatientId(Integer patientId);
}
