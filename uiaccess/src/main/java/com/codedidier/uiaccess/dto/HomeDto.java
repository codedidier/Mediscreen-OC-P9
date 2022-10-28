package com.codedidier.uiaccess.dto;

import com.codedidier.uiaccess.model.PatientModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class HomeDto {

    private List<PatientModel> patients = new ArrayList<>();
    private Long patientId;
    private LocalDate dateOfBirth;
    private char gender;
    private String address;
    private String phone;
}
