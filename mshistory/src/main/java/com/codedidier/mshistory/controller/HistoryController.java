package com.codedidier.mshistory.controller;

import com.codedidier.mshistory.document.Note;
import com.codedidier.mshistory.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("API pour les opérations CRUD sur les notes")
@Slf4j
@RestController
@RequestMapping("/api/note")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * Accueil msHistory
     */
    @ApiOperation(value = "home application")
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Welcome form mshistory API!");
    }

    /**
     * Récupère un historique avec l'ID du patient
     */
    @ApiOperation(value = "Récupère l'historique d'un patient avec l'ID du patient")
    @GetMapping("/all/{patientId}")
    public ResponseEntity<List<Note>> listNote(@PathVariable("patientId") Integer patientId) {
        List<Note> notes = historyService.findAllNotesByPatientId(patientId);
        log.debug("controller : obtenir la liste de toutes les notes par le patientId");
        return ResponseEntity.ok().body(notes);
    }

    /**
     * Récupère une note par son ID
     */
    @ApiOperation(value = "Récupère l'historique d'un patient avec l'ID de la note")
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        Note note = historyService.getNoteById(id);
        log.debug("controller : obtenir une note par l'id : " + id);
        return ResponseEntity.ok().body(note);
    }

    /**
     * Ajoute une note au patient
     */
    @ApiOperation(value = "ajouter une nouvelle note")
    @PostMapping("/add")
    public ResponseEntity<Note> saveNote(@RequestBody @Valid Note note) {
        Note noteAdded = historyService.saveNote(note);
        log.debug("controller : note ajoutée : " + note);
        return ResponseEntity.ok().body(noteAdded);
    }

    /**
     * Met à jour les notes de l'historique d'un patient
     */
    @ApiOperation(value = "met à jour les notes de l'historique d'un patient")
    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updatePatient(@PathVariable String id, @RequestBody @Valid Note note) {
        Note noteUpdated = historyService.updateNote(id, note);
        log.debug("controller : mise à jour des notes : " + noteUpdated);
        return ResponseEntity.ok().body(noteUpdated);
    }
}
