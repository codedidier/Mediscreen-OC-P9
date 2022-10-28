package com.codedidier.mspatient.controller;

import com.codedidier.mspatient.entity.Patient;
import com.codedidier.mspatient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Acceuil MsPatient
     * @return Welcome to MsPatient API !
     */
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Welcome to MsPatient API !");
    }
    /**
     *Liste de tous les patients
     */
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> allPatients() {
        List<Patient> allPatients = patientService.getAllPatients();
        log.debug("controller : get the list of all patients");
        return  ResponseEntity.ok().body(allPatients);
    }

    /**
     * Voir un patient
     * @param id
     * @return patient
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id) {
        Patient patient = patientService.getPatientById(id);
        log.debug("controller : get patient by id : " + id);
        return ResponseEntity.ok().body(patient);
    }

    /**
     * Ajout d'un patient
     * @param patient
     * @return patient créer
     */
    @PostMapping("/add")
    public ResponseEntity<Patient> savePatient(@RequestBody @Valid Patient patient) {
        Patient patientCreated = patientService.savePatient(patient);
        log.debug("controller : create patient : " + patient);
        return ResponseEntity.ok().body(patientCreated);
    }

    /**
     * Mise a jour d'un patient
     * @param id
     * @param patient
     * @return la mise à jour du patient
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable long id, @RequestBody @Valid Patient patient) {
        Patient patientUpdated = patientService.updatePatient(id, patient);
        log.debug("controller : update patient : " + patientUpdated);
        return ResponseEntity.ok().body(patientUpdated);
    }
}
