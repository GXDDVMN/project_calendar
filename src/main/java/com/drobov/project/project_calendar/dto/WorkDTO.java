package com.drobov.project.project_calendar.dto;


import com.drobov.project.project_calendar.validation.CheckTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CheckTime
public class WorkDTO {
    private long id;
    private long user_id;
    @NotEmpty(message = "Enter your name")
    private String name;
    @NotNull(message = "Fill the form please")
    private LocalTime starttime;
    @NotNull(message = "Fill the form please")
    private LocalTime endtime;
    private double salary;
    private String color;
    @NotEmpty
    private String schedule;
    @NotEmpty(message = "Enter the beginning day")
    private String start_day;
}
