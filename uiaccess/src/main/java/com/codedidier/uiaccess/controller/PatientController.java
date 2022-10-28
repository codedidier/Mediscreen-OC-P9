package com.codedidier.uiaccess.controller;

import com.codedidier.uiaccess.dto.PatientDto;
import com.codedidier.uiaccess.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * UI du microservice mspatient
 */
@Controller
public class PatientController {

    private final PatientProxy patientProxy;

    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    /**
     * Page d'ajout d'un patient
     */
    @GetMapping("/patient")
    public String getForm(Model model) {
        PatientDto dto = new PatientDto();
        model.addAttribute("dto", dto);

        return "patient/add";
    }

    /**
     * Formulaire d'ajout d'un patient
     */
    @PostMapping("/patient")
    public String postForm(@Valid @ModelAttribute("dto") PatientDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "patient/add";
        }
        try {
            patientProxy.savePatient(PatientDto.converterToModel(dto));
        } catch (RuntimeException e) {
            bindingResult.rejectValue(
                    "", "",
                    "cet utilisateur, est déjà présent dans la base de données");
            return "patient/add";
        }

        return "redirect:home";
    }

    /**
     * Page pour voir un patient
     */
    @GetMapping("/patient/{id}")
    public String getUpDateForm(@PathVariable long id, Model model) {
        PatientDto dto = PatientDto.converterToDto(
                patientProxy.getPatientById(id));
        model.addAttribute("dto", dto);
        return "patient/update";

    }

    /**
     * Formulaire de mise à jour d'un patient
     */
    @PostMapping("/patientUpdate")
    public String postUpDateForm(@Valid @ModelAttribute("dto") PatientDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "patient/update";
        }
        patientProxy.updatePatient(dto.getId(), PatientDto.converterToModel(dto));
        return "redirect:home";
    }
}
