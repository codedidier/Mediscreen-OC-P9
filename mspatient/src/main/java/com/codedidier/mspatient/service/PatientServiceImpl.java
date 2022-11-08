package com.codedidier.mspatient.service;

import com.codedidier.mspatient.entity.Patient;
import com.codedidier.mspatient.exception.PatientNotFoundException;
import com.codedidier.mspatient.exception.PersonAlreadyExistException;
import com.codedidier.mspatient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service qui gère les patients
 */
@Slf4j
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Récupère la liste de tous les patients
     */
    @Override
    public List<Patient> getAllPatients() {
        log.debug("service : get the list of all patients");
        return patientRepository.findAll();
    }
    /**
     * Récupère un patient par son ID
     */
    @Override
    public Patient getPatientById(long id) {
        log.debug("service : get patient by id : " + id);
        return getById(id);
    }

    /**
     * Ajouter un patient
     */
    @Override
    public Patient savePatient(Patient patient) {
        Optional<Patient> optional = patientRepository.findOneByFirstNameAndLastName(
                patient.getFirstName(),
                patient.getLastName());
        if (optional.isPresent()) {
            throw new PersonAlreadyExistException(
                    patient.getFirstName(),
                    patient.getLastName());
        }
        Patient patientCreated = patientRepository.save(patient);
        log.debug("service : create patient : " + patient);
        return patientCreated;
    }

    /**
     * Mettre à jour un patient
     */
    @Override
    public Patient updatePatient(long id, Patient updatePatient) {
        Patient dBPatient = getById(id);

        dBPatient.setFirstName(updatePatient.getFirstName());
        dBPatient.setLastName(updatePatient.getLastName());
        dBPatient.setDateOfBirth(updatePatient.getDateOfBirth());
        dBPatient.setGender(updatePatient.getGender());
        dBPatient.setAddress(updatePatient.getAddress());
        dBPatient.setPhone(updatePatient.getPhone());
        log.debug("service : update patient : " + updatePatient);
        return patientRepository.save(dBPatient);
    }

    /**
     * Supprime un patient dans la base de donnée par son ID
     */
    @Override
    public void deletePatient(long id) {
        Patient patient = getById(id);
        patientRepository.deleteById(patient.getId());
        log.debug("service : delete patient by id : " + id);
    }

    /**
     * Récupère un patient par son ID
     */
    private Patient getById(long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }
}
