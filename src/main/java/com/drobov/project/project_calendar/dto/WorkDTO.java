package com.drobov.project.project_calendar.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDTO {
    private long id;
    private long user_id;
    private String name;
    private LocalTime starttime;
    private LocalTime endtime;
    private double salary;
    private String color;
    private String schedule;
    private String start_day;
}
