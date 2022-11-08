package com.codedidier.mshistory.service;

import com.codedidier.mshistory.HistoryRepository.HistoryRepository;
import com.codedidier.mshistory.document.Note;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class HistoryServiceImplTest {

    HistoryRepository historyRepository = mock(HistoryRepository.class);

    HistoryServiceImpl classUnderTest = new HistoryServiceImpl(historyRepository);

    static Note getExpectedNote(){
        Note expectedNote = new Note();
        expectedNote.setId("idTest");
        expectedNote.setPatientId(1);
        expectedNote.setRecommendation("recommendation de test");
        return expectedNote;
    }
    static Note getUpdateNote(){
        Note expectedNote = new Note();
        expectedNote.setId("idTest");
        expectedNote.setPatientId(1);
        expectedNote.setRecommendation("recommendation de test Updated");
        return expectedNote;
    }

    @Test
    void findAllNotesByPatientId() {
        List<Note> notes = new ArrayList<>();
        when(historyRepository.findAllNotesByPatientId(1)).thenReturn(notes);

        classUnderTest.findAllNotesByPatientId(1);

        verify(historyRepository, times(1)).findAllNotesByPatientId(1);
    }

    @Test
    void getNoteById() {
        Note expectedNote = getExpectedNote();
        when(historyRepository.findById("idTest")).thenReturn(java.util.Optional.of(expectedNote));

        Note actualNote = classUnderTest.getNoteById("idTest");

        assertEquals(expectedNote.getId(), actualNote.getId());
    }

    @Test
    void saveNote() {
        Note expectedNote = getExpectedNote();
        when(historyRepository.save(expectedNote)).thenReturn(expectedNote);

        Note actualNote = classUnderTest.saveNote(expectedNote);

        assertEquals(expectedNote, actualNote);
    }

    @Test
    void updateNote() {
        Note existingNote = getExpectedNote();
        Note updateNote = getUpdateNote();
        when(historyRepository.findById("idTest")).thenReturn(java.util.Optional.of(existingNote));
        when(historyRepository.save(updateNote)).thenReturn(updateNote);

        Note actualNote = classUnderTest.updateNote("idTest", updateNote);

        assertEquals(updateNote.getRecommendation(),actualNote.getRecommendation());

    }
}
