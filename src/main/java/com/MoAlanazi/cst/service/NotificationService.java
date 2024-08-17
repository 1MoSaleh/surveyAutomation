package com.MoAlanazi.cst.service;

import com.MoAlanazi.cst.entity.SurveyRequest;

import javax.mail.MessagingException;
import java.util.List;

/*
* the notification service could be built with Factory design pattern in order to handle multiple types
* of notification. such as: email, sms, and push notification.
* For now we will have only send email service.
* */
public interface NotificationService {
    void sendBulkEmail(List<SurveyRequest> surveyRequestList) throws MessagingException;
}
