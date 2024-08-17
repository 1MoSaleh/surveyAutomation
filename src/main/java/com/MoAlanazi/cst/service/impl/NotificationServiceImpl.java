package com.MoAlanazi.cst.service.impl;

import com.MoAlanazi.cst.entity.SurveyRequest;
import com.MoAlanazi.cst.repository.SurveyRepository;
import com.MoAlanazi.cst.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender javaMailSender;
    private final SurveyRepository surveyRepository;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;


    // Todo complete send email by logging and refactor the code.
    @Override
    public void sendBulkEmail(List<SurveyRequest> surveyRequestList) throws MessagingException {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        Context context =new Context();

        for (SurveyRequest surveyRequest: surveyRequestList) {
            try {
                log.info("start sending survey {} to the following email {}", surveyRequest.getSurveyUrl(),surveyRequest.getAdminEmail());

                mimeMessageHelper.setFrom(sender);
                mimeMessageHelper.setTo(surveyRequest.getAdminEmail());
                mimeMessageHelper.setText(this.setMessageTemplate(surveyRequest, context, "surveyEmailTemplate.html"), true);
                mimeMessageHelper.setSubject("CST Domain Survey");

                javaMailSender.send(mimeMessage);

                surveyRequest.setEmailSent(true);
                surveyRequest.setEmailSentAt(LocalDateTime.now());
            }catch (Exception e){
                log.error("failed sending survey {} to the following email {}", surveyRequest.getSurveyUrl(),surveyRequest.getAdminEmail());
                surveyRequest.setFailureMessage(e.getMessage());
                surveyRequest.setFailedAt(LocalDateTime.now());
            }
        }
        surveyRepository.saveAll(surveyRequestList);
    }

    private String setMessageTemplate(SurveyRequest surveyRequest, Context context, String templateName){
        context.setVariable("surveyUrl",surveyRequest.getSurveyUrl());
        return templateEngine.process(templateName, context);
    }

}
