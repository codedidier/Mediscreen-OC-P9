package com.codedidier.msassess.service;

import com.codedidier.msassess.model.AssessmentModel;

public interface AssessService {

    AssessmentModel calculateRiskAssessment(long id);
}
