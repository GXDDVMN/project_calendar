package com.drobov.project.project_calendar.service;

import com.drobov.project.project_calendar.dto.MonthDTO;
import com.drobov.project.project_calendar.entity.Month;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface MonthService {
    List<MonthDTO> getNotesForUser(long user_id, YearMonth month);
    void saveNote(MonthDTO monthDTO);

    Month mapDTOToMonth(MonthDTO monthDTO);

    List<MonthDTO> mapToListDTO(List<Month> months);

    MonthDTO mapToMonthDTO(Month month);
}
