package com.codedidier.uiaccess.controller;

import com.codedidier.uiaccess.dto.HomeDto;
import com.codedidier.uiaccess.dto.ResultDto;
import com.codedidier.uiaccess.model.AssessModel;
import com.codedidier.uiaccess.model.PatientModel;
import com.codedidier.uiaccess.proxy.AssessProxy;
import com.codedidier.uiaccess.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface HTTP REST de la page d'accueil"
 */
@Controller
public class HomeController {

    private final PatientProxy patientProxy;
    private final AssessProxy assessProxy;

    public HomeController(PatientProxy patientProxy, AssessProxy assessProxy) {
        this.patientProxy = patientProxy;
        this.assessProxy = assessProxy;
    }

    /**
     * Get home page.
     */
    @GetMapping("/home")
    public String home(Model model) {
        HomeDto dto = new HomeDto();
        dto.setPatients(patientProxy.allPatients());
        model.addAttribute("dto", dto);
        return "home/home";
    }

    /**
     * Get page de résultat du risque
     */
    @GetMapping("/result/{id}")
    public String result(@PathVariable long id, Model model) {
        PatientModel patient = patientProxy.getPatientById(id);
        AssessModel assess = assessProxy.getAssessById(id);
        ResultDto dto = new ResultDto(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                assess.getAssessmentModel()
        );

        model.addAttribute("dto", dto);

        return "home/result";
    }
}