package com.drobov.project.project_calendar.service;




import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.entity.Date;

import java.time.Month;
import java.util.List;

public interface DateService {
    public List<DateDTO> showDates();
    public DateDTO showDate(Long id);
    public List<DateDTO> showDatesForUser(long user_id);
    public Date showThisDate(Date date);
    public void saveDate(DateDTO dateDTO);
    public void deleteDate(long id);
    public List<DateDTO> mapToListDTO(List<Date> dates);
    public DateDTO mapToDateDto(Date date);
    public List<DateDTO> showDatesForMonth(Long user_id,Month month);
}
