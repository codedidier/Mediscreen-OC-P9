package com.codedidier.uiaccess.controller;

import com.codedidier.uiaccess.dto.HistoryDto;
import com.codedidier.uiaccess.dto.HistoryPageDto;
import com.codedidier.uiaccess.model.HistoryModel;
import com.codedidier.uiaccess.model.PatientModel;
import com.codedidier.uiaccess.proxy.HistoryProxy;
import com.codedidier.uiaccess.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HistoryController {

    private final PatientProxy patientProxy;
    private final HistoryProxy historyProxy;

    public HistoryController(PatientProxy patientProxy, HistoryProxy historyProxy) {
        this.patientProxy = patientProxy;
        this.historyProxy = historyProxy;
    }

    @GetMapping("/history/{id}")
    public String historyPage(@PathVariable long id, Model model) {

        PatientModel patient = patientProxy.getPatientById(id);
        List<HistoryModel> notes = historyProxy.allNotes(id);

        HistoryPageDto dto = new HistoryPageDto(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                notes
        );

        model.addAttribute("dto", dto);
        return "history/history";
    }

    @GetMapping("/history/newNote/{id}")
    public String getForm(@PathVariable long id, Model model) {
        PatientModel patient = patientProxy.getPatientById(id);
        HistoryDto dto = new HistoryDto();
        dto.setPatientId(id);
        model.addAttribute("dto", dto);
        return "history/add";
    }
    @PostMapping("/addNote")
    public String postForm(@Valid @ModelAttribute("dto") HistoryDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "history/add";
        }
        historyProxy.saveNote(HistoryDto.convertToBBean(dto));

        return "redirect:history/"+dto.getPatientId();
    }
    @GetMapping("/noteUpdate/{id}")
    public String getForm(@PathVariable String id, Model model) {
        HistoryModel note = historyProxy.getNoteById(id);
        HistoryDto dto = new HistoryDto();
        dto.setPatientId(note.getPatientId());
        dto.setId(id);
        dto.setRecommendation(note.getRecommendation());
        model.addAttribute("dto", dto);
        return "history/update";
    }
    @PostMapping("/updateNote")
    public String postUpDateForm(@Valid @ModelAttribute("dto") HistoryDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "history/update";
        }
        historyProxy.updateNote(dto.getId(), HistoryDto.convertToBBean(dto));
        return "redirect:history/"+dto.getPatientId();
    }
}
