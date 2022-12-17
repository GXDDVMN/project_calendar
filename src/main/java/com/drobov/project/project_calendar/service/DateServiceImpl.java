package com.drobov.project.project_calendar.service;


import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.dto.UserDTO;
import com.drobov.project.project_calendar.entity.Date;
import com.drobov.project.project_calendar.entity.User;
import com.drobov.project.project_calendar.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DateServiceImpl implements DateService{
    @Autowired
    private DateRepository dateRepository;
    @Override
    public List<DateDTO> showDates() {
        return mapToListDTO(dateRepository.findAll());
    }

    @Override
    public List<DateDTO> showDatesForUser(long user_id) {

        return mapToListDTO(dateRepository.findAllByUser_Id(user_id));
    }

    @Override
    public Date showThisDate(Date date) {
        return null;
    }

//    @Override
//    public void saveDate(DateDTO dateDTO) {
//        dateRepository.save()
//    }

    @Override
    public void deleteDate(int id) {

    }

    public List<DateDTO> mapToListDTO(List<Date> dates){
        return dates.stream().map(date -> mapToDateDto(date)).collect(Collectors.toList());
    }
    public DateDTO mapToDateDto(Date date){
        DateDTO dateDTO = new DateDTO();
        dateDTO.setDateof(date.getDateof());
        dateDTO.setStarttime(date.getStarttime());
        dateDTO.setEndtime(date.getEndtime());
        dateDTO.setSalary(date.getSalary());
        dateDTO.setDoname(date.getDoname());
        dateDTO.setDescrip(date.getDescrip());
        dateDTO.setWorkbool(date.getWorkbool());
        dateDTO.setUser_id(date.getUser().getId());
        return dateDTO;
    }

    @Override
    public List<DateDTO> showDatesForMonth(Long user_id, Month month) {
        List<DateDTO> allDates = mapToListDTO(dateRepository.findAllByUser_Id(user_id));
        return allDates.stream().filter(date->date.getDateof().getMonth()==month).toList();
    }
}
