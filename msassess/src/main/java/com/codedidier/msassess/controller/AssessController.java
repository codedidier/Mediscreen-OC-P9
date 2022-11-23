package com.codedidier.msassess.controller;

import com.codedidier.msassess.model.AssessmentModel;
import com.codedidier.msassess.service.AssessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Api pour évaluation des risques diabétique")
@RestController
@Slf4j
@RequestMapping("/api/assess")
public class AssessController {

    private final AssessService assessService;

    @Autowired
    public AssessController(AssessService assessService) {
        this.assessService = assessService;
    }

    /**
     * Accueil msassess
     */
    @ApiOperation(value = "home application")
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Welcome form msassess API!");
    }

    /**
     * Récupère l'évaluation des risques avec l'ID du patient
     */
    @ApiOperation(value = "Récupère les déclencheurs par PatientId")
    @GetMapping("/{id}")
    public ResponseEntity<AssessmentModel> getPatientById(@PathVariable long id) {
        AssessmentModel assessmentModel = assessService.calculateRiskAssessment(id);
        log.debug("controller : Récupère l'évaluation des risques avec l'ID du patient : " + id);
        return ResponseEntity.ok().body(assessmentModel);
    }
}
