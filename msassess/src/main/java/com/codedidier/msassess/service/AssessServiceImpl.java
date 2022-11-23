package com.codedidier.msassess.service;

import com.codedidier.msassess.constants.Triggers;
import com.codedidier.msassess.model.AssessmentModel;
import com.codedidier.msassess.model.HistoryModel;
import com.codedidier.msassess.model.PatientModel;
import com.codedidier.msassess.model.RiskLevelsEnum;
import com.codedidier.msassess.proxy.HistoryProxy;
import com.codedidier.msassess.proxy.PatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Service qui gère le calcul des risques
 */
@Slf4j
@Service
public class AssessServiceImpl implements AssessService{

    private final PatientProxy patientProxy;
    private final HistoryProxy historyProxy;


    public AssessServiceImpl(PatientProxy patientProxy, HistoryProxy historyProxy) {
        this.patientProxy = patientProxy;
        this.historyProxy = historyProxy;
    }

    /**
     * calcul de l'évaluation des risques en fonction de l'age et du sex
     */
    @Override
    public AssessmentModel calculateRiskAssessment(long id) {
        PatientModel patientModel = patientProxy.getPatientById(id);
        List<HistoryModel> notes = historyProxy.allNotes(id);
        int age = calculateAge(patientModel.getDateOfBirth());
        int triggersNb = getTriggersOccurrences(notes);

        if (age >= 30) {
            return patientOverThirtyYearsOld(triggersNb);
        } else {
            return patientModel.getGender() == 'M'
                    ? riskForMaleUnderThirtyYearsOld(triggersNb)
                    : riskForFemaleUnderThirtyYearsOld(triggersNb);
        }
    }

    /**
     * Compte les termes déclencheurs
     */
    public int getTriggersOccurrences(List<HistoryModel> notes) {
        int termsCount = 0;
        for(HistoryModel historyModel : notes) {
            for(String trigger :  Triggers.TRIGGERS) {

                if (historyModel.getRecommendation().toLowerCase().contains(trigger)) {
                    termsCount++;
                }
            }
        }
        return termsCount;
    }

    /**
     * Calcul de l'age
     */
    public int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Calcul du risque pour les plus de 30 ans suivant le nombre de déclencheurs
     */
    private AssessmentModel patientOverThirtyYearsOld(int triggersNb) {

        //BorderLine
        if (triggersNb >= 2 && triggersNb < 6) {
            return new AssessmentModel(RiskLevelsEnum.BORDERLINE);
        }
        //In Danger
        if (triggersNb >= 6 && triggersNb < 8) {
            return new AssessmentModel(RiskLevelsEnum.IN_DANGER);
        }
        //Early Onset
        if (triggersNb >= 8) {
            return new AssessmentModel(RiskLevelsEnum.EARLY_ONSET);
        }
        //None
        return new AssessmentModel(RiskLevelsEnum.NONE);
    }

    /**
     * Calcul du risque pour les hommes de moins de 30 ans suivant le nombre de déclencheurs
     */
    private AssessmentModel riskForMaleUnderThirtyYearsOld(int triggersNb) {

        //In Danger
        if (triggersNb >= 3 && triggersNb < 5 ) {
            return new AssessmentModel(RiskLevelsEnum.IN_DANGER);
        }
        //Early Onset
        if (triggersNb >= 5) {
            return new AssessmentModel(RiskLevelsEnum.EARLY_ONSET);
        }
        //None
        return new AssessmentModel(RiskLevelsEnum.NONE);
    }

    /**
     * Calcul du risque pour les femmes de moins de 30 ans suivant le nombre de déclencheurs
     */
    private AssessmentModel riskForFemaleUnderThirtyYearsOld(int triggersNb) {

        //In Danger
        if (triggersNb >= 4 && triggersNb < 7 ) {
            return new AssessmentModel(RiskLevelsEnum.IN_DANGER);
        }
        //Early Onset
        if (triggersNb >= 7) {
            return new AssessmentModel(RiskLevelsEnum.EARLY_ONSET);
        }
        //None
        return new AssessmentModel(RiskLevelsEnum.NONE);
    }
}
