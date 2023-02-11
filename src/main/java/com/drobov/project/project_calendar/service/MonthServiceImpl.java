package com.drobov.project.project_calendar.service;

import com.drobov.project.project_calendar.dto.MonthDTO;
import com.drobov.project.project_calendar.entity.Month;
import com.drobov.project.project_calendar.entity.User;
import com.drobov.project.project_calendar.repository.MonthRepository;
import com.drobov.project.project_calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.time.YearMonth;
import java.util.stream.Collectors;

@Service
public class MonthServiceImpl implements MonthService {

    @Autowired
    private MonthRepository monthRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MonthDTO> getNotesForUser(long user_id, YearMonth month) {
        return mapToListDTO(monthRepository.findAllByUser_IdAndMonth(user_id, LocalDate.of(month.getYear(), month.getMonth(), 1)));
    }

    @Override
    public void saveNote(MonthDTO monthDTO) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Month month = mapDTOToMonth(monthDTO);
        month.setUser(user);
        monthRepository.save(month);
    }

    @Override
    public Month mapDTOToMonth(MonthDTO monthDTO) {
        Month month = new Month();
        month.setMonth(monthDTO.getMonth().atDay(1));
        month.setId(monthDTO.getId());
        month.setNotes(monthDTO.getNotes());
        return month;
    }

    @Override
    public List<MonthDTO> mapToListDTO(List<Month> months) {
        return months.stream().map(month -> mapToMonthDTO(month)).collect(Collectors.toList());
    }

    @Override
    public MonthDTO mapToMonthDTO(Month month) {
        MonthDTO monthDTO = new MonthDTO();
        monthDTO.setId(month.getId());
        monthDTO.setMonth(YearMonth.from(month.getMonth()));
        monthDTO.setUser_id(month.getUser().getId());
        monthDTO.setNotes(month.getNotes());
        return monthDTO;
    }

    @Override
    public void deleteNote(long id) {
        monthRepository.deleteById(id);
    }
}
