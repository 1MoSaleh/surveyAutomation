package com.MoAlanazi.cst.service;

import com.MoAlanazi.cst.dto.SurveyCreateRequestDto;

import javax.mail.MessagingException;

public interface SurveyService {
    void createSurvey(SurveyCreateRequestDto surveyCreateRequestDto);

    void sendBulkSurveyEmail() throws MessagingException;
}
