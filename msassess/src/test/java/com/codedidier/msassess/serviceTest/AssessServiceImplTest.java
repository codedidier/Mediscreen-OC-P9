package com.codedidier.msassess.serviceTest;


import com.codedidier.msassess.constants.Triggers;
import com.codedidier.msassess.model.AssessmentModel;
import com.codedidier.msassess.model.HistoryModel;
import com.codedidier.msassess.model.PatientModel;
import com.codedidier.msassess.proxy.HistoryProxy;
import com.codedidier.msassess.proxy.PatientProxy;
import com.codedidier.msassess.service.AssessServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AssessServiceImplTest {

    PatientProxy patientProxy = mock(PatientProxy.class);
    HistoryProxy historyProxy = mock(HistoryProxy.class);

    AssessServiceImpl classUnderTest =
            new AssessServiceImpl(patientProxy,historyProxy);
    @InjectMocks
    static
    AssessServiceImpl assessServiceImpl;


    static List<HistoryModel> getTriggers(int nb) {
        List<HistoryModel> notes = new ArrayList<>();

        for (int i = 0; i < nb ; i++) {
            HistoryModel historyModel = new HistoryModel();
            historyModel.setRecommendation(Triggers.TRIGGERS.get(0));
            notes.add(historyModel);
        }
        System.out.println(notes);
        return notes;
    }
    @Test
    void patientOverThirtyYearsOldTest() {
        PatientModel patientModel = new PatientModel();
        patientModel.setId(8000);
        patientModel.setDateOfBirth(LocalDate.of(1990, 01, 01));
        when(patientProxy.getPatientById(patientModel.getId())).thenReturn(patientModel);

        //BorderLine
        List<HistoryModel> notes = getTriggers(4);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        AssessmentModel assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "BORDERLINE");

        //In Danger
        notes = getTriggers(7);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "IN_DANGER");

        //Early Onset
        notes = getTriggers(10);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "EARLY_ONSET");

        //None
        notes = getTriggers(0);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "NONE");
    }

    @Test
    void riskForMaleUnderThirtyYearsOldTest() {
        PatientModel patientModel = new PatientModel();
        patientModel.setId(8000);
        patientModel.setGender('M');
        patientModel.setDateOfBirth(LocalDate.of(2000, 01, 01));
        when(patientProxy.getPatientById(patientModel.getId())).thenReturn(patientModel);

        //In Danger
        List<HistoryModel> notes = getTriggers(4);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        AssessmentModel assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "IN_DANGER");

        //Early Onset
        notes = getTriggers(6);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "EARLY_ONSET");

        //None
        notes = getTriggers(0);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "NONE");
    }

    @Test
    void riskForFemaleUnderThirtyYearsOldTest() {
        PatientModel patientModel = new PatientModel();
        patientModel.setId(8000);
        patientModel.setGender('F');
        patientModel.setDateOfBirth(LocalDate.of(2000, 01, 01));
        when(patientProxy.getPatientById(patientModel.getId())).thenReturn(patientModel);

        //In Danger
        List<HistoryModel> notes = getTriggers(5);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        AssessmentModel assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "IN_DANGER");

        //Early Onset
        notes = getTriggers(8);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "EARLY_ONSET");

        //None
        notes = getTriggers(0);
        when(historyProxy.allNotes(patientModel.getId())).thenReturn(notes);
        assessmentModel = classUnderTest.calculateRiskAssessment(8000);
        assertEquals(assessmentModel.getAssessmentModel().toString(), "NONE");
    }
}
