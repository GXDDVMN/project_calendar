package com.drobov.project.project_calendar.service;


import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.dto.UserDTO;
import com.drobov.project.project_calendar.entity.Date;
import com.drobov.project.project_calendar.entity.User;
import com.drobov.project.project_calendar.repository.DateRepository;
import com.drobov.project.project_calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.security.AuthProvider;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DateServiceImpl implements DateService{
    @Autowired
    private DateRepository dateRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<DateDTO> showDates() {
        return mapToListDTO(dateRepository.findAll());
    }

    @Override
    public DateDTO showDate(Long id) {
        return mapToDateDto(dateRepository.findById(id));
    }

    @Override
    public List<DateDTO> showDatesForUser(long user_id) {

        return mapToListDTO(dateRepository.findAllByUser_Id(user_id));
    }

    @Override
    public Date showThisDate(Date date) {
        return null;
    }

    @Override
    public void saveDate(DateDTO dateDTO)
    {
        User user= userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Date date=mapDTOToDate(dateDTO);
        date.setUser(user);
        dateRepository.save(date);
    }

    @Override
    public void deleteDate(int id) {

    }

    public List<DateDTO> mapToListDTO(List<Date> dates){
        return dates.stream().map(date -> mapToDateDto(date)).collect(Collectors.toList());
    }
    public DateDTO mapToDateDto(Date date){
        DateDTO dateDTO = new DateDTO();
        dateDTO.setDateof(date.getDateof().toString());
        dateDTO.setStarttime(date.getStarttime());
        dateDTO.setEndtime(date.getEndtime());
        dateDTO.setSalary(date.getSalary());
        dateDTO.setDoname(date.getDoname());
        dateDTO.setDescrip(date.getDescrip());
        dateDTO.setWorkbool(date.getWorkbool());
        dateDTO.setUser_id(date.getUser().getId());
        return dateDTO;
    }

    public Date mapDTOToDate(DateDTO dateDTO){
        Date date = new Date();
        date.setDateof(LocalDate.parse(dateDTO.getDateof()));
        date.setStarttime(dateDTO.getStarttime());
        date.setEndtime(dateDTO.getEndtime());
        date.setSalary(dateDTO.getSalary());
        date.setDoname(dateDTO.getDoname());
        date.setDescrip(dateDTO.getDescrip());
        date.setWorkbool(dateDTO.getWorkbool());
        //date.setUser(userRepository.findById(dateDTO.getUser_id()).get());
        return date;
    }
    @Override
    public List<DateDTO> showDatesForMonth(Long user_id, Month month) {
        List<DateDTO> allDates = mapToListDTO(dateRepository.findAllByUser_Id(user_id).stream().filter(date->date.getDateof().getMonth()==month).toList());
        return allDates;
    }
}
