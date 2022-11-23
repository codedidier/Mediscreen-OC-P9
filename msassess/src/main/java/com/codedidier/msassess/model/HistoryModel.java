package com.codedidier.msassess.model;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
public class HistoryModel {

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
     * Recommendation des médecins
     */
    private String recommendation;

}