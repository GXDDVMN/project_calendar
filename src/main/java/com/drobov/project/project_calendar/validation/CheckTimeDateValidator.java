package com.drobov.project.project_calendar.validation;

import com.drobov.project.project_calendar.dto.DateDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CheckTimeDateValidator implements ConstraintValidator<CheckTime, DateDTO> {
    @Override
    public boolean isValid(DateDTO dateDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (dateDTO.getStarttime()==null|dateDTO.getEndtime()==null) return true;
        return !dateDTO.getStarttime().isAfter(dateDTO.getEndtime());
    }
}
