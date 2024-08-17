package com.MoAlanazi.cst.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SurveyCreateRequestDto {
    private String surveyUrl;
    private List<SurveyDomainDto> domains;
}
