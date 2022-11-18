package com.codedidier.mshistory.HistoryRepository;

import com.codedidier.mshistory.document.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository  extends MongoRepository<Note, String> {

    /**
     * Récupère un historique avec l'ID d'un patient
     */
    List<Note> findAllNotesByPatientId(Integer patientId);
}
