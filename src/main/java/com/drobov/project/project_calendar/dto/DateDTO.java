package com.drobov.project.project_calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateDTO {
    private long user_id;
    private LocalDate dateof;
    private LocalTime starttime;
    private LocalTime endtime;
    private Boolean workbool;
    private String doname;
    private String descrip;
    private int salary;
}
