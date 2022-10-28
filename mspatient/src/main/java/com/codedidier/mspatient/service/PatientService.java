package com.codedidier.mspatient.service;

import com.codedidier.mspatient.entity.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();

    Patient getPatientById(long id);

    Patient savePatient(Patient patient);

    Patient updatePatient(long id, Patient updatePatient);

    void deletePatient(long id);
}
