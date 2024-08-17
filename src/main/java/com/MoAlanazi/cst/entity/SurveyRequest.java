package com.MoAlanazi.cst.entity;

import com.MoAlanazi.cst.dto.SurveyDomainDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_SURVEY_REQUEST")
@Setter
@Getter
@NoArgsConstructor
public class SurveyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @Column(name = "survey_url")
    private String surveyUrl;

    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "admin_email")
    private String adminEmail;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "is_email_sent")
    private boolean emailSent;

    @Column(name = "email_sent_at")
    private LocalDateTime emailSentAt;

    private String failureMessage;

    @Column(name = "failed_at")
    private LocalDateTime failedAt;


    public SurveyRequest(String surveyUrl, SurveyDomainDto surveyDomainDto){
        this.surveyUrl = surveyUrl;
        this.domainName = surveyDomainDto.getDomainName();
        this.adminEmail = surveyDomainDto.getAdminEmail();
    }
}
