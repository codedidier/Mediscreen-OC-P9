package com.codedidier.mspatient.controller;

import com.codedidier.mspatient.entity.Patient;
import com.codedidier.mspatient.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * API REST du microservice mspatient
 */
@Api("API pour les opérations CRUD sur les patients")
@Slf4j
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Acceuil MsPatient
     * @return Welcome to MsPatient API !
     */
    @ApiOperation(value = "home application")
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Welcome to MsPatient API !");
    }
    /**
     *Récupère la liste de tous les patients en BDD
     */
    @ApiOperation(value = "Get all patients")
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> allPatients() {
        List<Patient> allPatients = patientService.getAllPatients();
        log.debug("controller : Récupère la liste de tous les patients");
        return  ResponseEntity.ok().body(allPatients);
    }

    /**
     * Récupère les données d'un patient par son ID
     * @param id
     * @return patient
     */
    @ApiOperation(value = "Get patient by id")
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id) {
        Patient patient = patientService.getPatientById(id);
        log.debug("controller : Récupère les données d'un patient par son ID : " + id);
        return ResponseEntity.ok().body(patient);
    }

    /**
     * Ajoute un patient dans la BDD
     * @param patient
     * @return patient créer
     */
    @ApiOperation(value = "Add new patient")
    @PostMapping("/add")
    public ResponseEntity<Patient> savePatient(@RequestBody @Valid Patient patient) {
        Patient patientCreated = patientService.savePatient(patient);
        log.debug("controller : add patient : " + patient);
        return ResponseEntity.ok().body(patientCreated);
    }

    /**
     * Mise a jour d'un patient dans la BDD
     * @param id
     * @param patient
     * @return la mise à jour du patient
     */
    @ApiOperation(value = "Update patient")
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable long id, @RequestBody @Valid Patient patient) {
        Patient patientUpdated = patientService.updatePatient(id, patient);
        log.debug("controller : update patient : " + patientUpdated);
        return ResponseEntity.ok().body(patientUpdated);
    }
}
