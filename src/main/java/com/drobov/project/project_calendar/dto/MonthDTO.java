package com.drobov.project.project_calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthDTO {
    private long id;
    private long user_id;
    private String notes;
    private YearMonth month;
}
