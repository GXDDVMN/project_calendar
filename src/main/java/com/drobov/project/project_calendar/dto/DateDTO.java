package com.drobov.project.project_calendar.dto;

import com.drobov.project.project_calendar.validation.CheckTime;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class DateDTO {
    private long id;
    private long user_id;
    @NotEmpty(message = "Enter the date")
    private String dateof;
    @NotNull(message = "Enter start time of event")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime starttime;
    @NotNull(message = "Enter end time of event")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endtime;
    @NotEmpty
    private String doname;
    @NotEmpty
    private String descrip;
}
