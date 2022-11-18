package com.codedidier.uiaccess.dto;

import com.codedidier.uiaccess.model.HistoryModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HistoryDto {

    private String id;
    private long patientId;

    @NotBlank(message = "la recommandation ne doit pas être vide")
    private String recommendation;

    public HistoryDto() {
    }

    public static HistoryModel convertToBBean(HistoryDto dto) {
        HistoryModel historyModel = new HistoryModel();
        historyModel.setId(dto.getId());
        historyModel.setPatientId((int) dto.getPatientId());
        historyModel.setRecommendation(dto.getRecommendation());

        return historyModel;
    }
}
