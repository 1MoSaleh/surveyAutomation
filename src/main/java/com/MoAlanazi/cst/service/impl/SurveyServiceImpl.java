package com.MoAlanazi.cst.service.impl;

import com.MoAlanazi.cst.dto.SurveyCreateRequestDto;
import com.MoAlanazi.cst.dto.SurveyDomainDto;
import com.MoAlanazi.cst.entity.SurveyRequest;
import com.MoAlanazi.cst.repository.SurveyRepository;
import com.MoAlanazi.cst.service.NotificationService;
import com.MoAlanazi.cst.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository repository;
    private final NotificationService notificationService;

    @Override
    public void createSurvey(SurveyCreateRequestDto surveyCreateRequestDto) {
        if (surveyCreateRequestDto.getDomains().isEmpty()){
            log.warn("domain.list.is.empty");
            // we can have custom exception to handle it and return It as Bad request.
            throw new RuntimeException("domain.list.is.empty");
        }
        // the following list initialized in order to make bulk save with single connection request instead of having multiple request to the db
        List<SurveyRequest> surveyRequestList = new ArrayList<>();

        for (SurveyDomainDto surveyDomainDto: surveyCreateRequestDto.getDomains()) {
            surveyRequestList.add(new SurveyRequest(surveyCreateRequestDto.getSurveyUrl(), surveyDomainDto));
        }

        repository.saveAll(surveyRequestList);
    }

    // to be called from Sending Email job
    @Override
    public void sendBulkSurveyEmail() throws MessagingException {
        List<SurveyRequest> surveyRequestList = repository.findByEmailSentFalse();
        if (surveyRequestList.isEmpty()){
            return;
        }
        notificationService.sendBulkEmail(surveyRequestList);
    }
}
