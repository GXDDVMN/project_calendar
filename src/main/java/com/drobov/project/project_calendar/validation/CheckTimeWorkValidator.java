package com.drobov.project.project_calendar.validation;

import com.drobov.project.project_calendar.dto.WorkDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CheckTimeWorkValidator implements ConstraintValidator<CheckTime, WorkDTO> {
    @Override
    public boolean isValid(WorkDTO workDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (workDTO.getStarttime()==null|workDTO.getEndtime()==null) return true;
        return !workDTO.getStarttime().isAfter(workDTO.getEndtime());
    }
}

