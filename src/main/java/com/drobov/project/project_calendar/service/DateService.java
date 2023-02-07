package com.drobov.project.project_calendar.service;




import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.entity.Date;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface DateService {
    List<DateDTO> showDates();
    DateDTO showDate(long id);
    List<DateDTO> showDatesForUser(long user_id);
    void saveDate(DateDTO dateDTO);
    void deleteDate(long id);
    List<DateDTO> mapToListDTO(List<Date> dates);
    DateDTO mapToDateDto(Date date);
    List<DateDTO> showDatesForMonth(long user_id, LocalDate month);
}
