package com.codedidier.uiaccess.controller;

import com.codedidier.uiaccess.dto.HomeDto;
import com.codedidier.uiaccess.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PatientProxy patientProxy;

    public HomeController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/home")
    public String home(Model model) {
        HomeDto dto = new HomeDto();
        dto.setPatients(patientProxy.allPatients());
        model.addAttribute("dto", dto);
        return "home/home";
    }
}
