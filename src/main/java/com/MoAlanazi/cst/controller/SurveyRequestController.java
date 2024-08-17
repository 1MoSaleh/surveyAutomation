package com.MoAlanazi.cst.controller;



import com.MoAlanazi.cst.service.SurveyService;
import com.MoAlanazi.cst.dto.SurveyCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("survey")
public class SurveyRequestController {
    @Autowired
    SurveyService surveyService;

    @GetMapping("/")
    public String getSurveyPage(Model model){
        model.addAttribute("surveyForm", new SurveyCreateRequestDto());
        return "surveyPage";
    }
    @PostMapping("create-survey")
    public ResponseEntity<String> createSurvey(@ModelAttribute SurveyCreateRequestDto surveyForm){
        surveyService.createSurvey(surveyForm);
        return ResponseEntity.ok("submittedSuccessfully");
    }
}
