package com.codedidier.uiaccess.dto;

import com.codedidier.uiaccess.model.HistoryModel;
import lombok.Data;

import java.util.List;

@Data
public class HistoryPageDto {

    private long patientId;
    private String firstName;
    private String lastName;
    private List<HistoryModel> notes;

    public HistoryPageDto(long patientId, String firstName,
                       String lastName,
                       List<HistoryModel> notes) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
    }
}
