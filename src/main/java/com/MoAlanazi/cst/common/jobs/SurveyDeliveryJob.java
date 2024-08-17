package com.MoAlanazi.cst.common.jobs;


import com.MoAlanazi.cst.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SurveyDeliveryJob {
    private final SurveyService surveyService;

    @Scheduled(cron = "0 * * * * ?")
    public void sendSurvey() throws MessagingException {
        log.info("[SurveyDeliveryJob].[sendSurvey]: sending email job started");
        surveyService.sendBulkSurveyEmail();
    }

}
