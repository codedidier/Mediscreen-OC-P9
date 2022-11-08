package com.codedidier.mshistory.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB Document
 */
@Data
@Document(collection = "notes")
public class Note {

    /**
     * ID unique d'un historique
     */
    @Id
    private String id;

    /**
     * ID du patient
     */
    private Integer patientId;

    /**
     * Recommendation de médecins
     */
    private String recommendation;
}
