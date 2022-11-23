package com.codedidier.uiaccess.dto;

import lombok.Data;

@Data
public class ResultDto {

    private long patientId;
    private String firstName;
    private String lastName;
    private String assess;

    public ResultDto(long patientId, String firstName, String lastName, String assess) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assess = assess;
    }
}
