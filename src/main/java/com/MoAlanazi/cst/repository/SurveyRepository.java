package com.MoAlanazi.cst.repository;

import com.MoAlanazi.cst.entity.SurveyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyRequest,Long> {
    List<SurveyRequest> findByEmailSentFalse();



}
