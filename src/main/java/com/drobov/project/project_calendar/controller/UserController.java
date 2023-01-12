package com.drobov.project.project_calendar.controller;

import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.service.DateService;
import com.drobov.project.project_calendar.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;
    //private LocalDate localDate=LocalDate.now();
    @GetMapping("/calendar/{id}")
    public String calendar(Model model,Principal principal,@PathVariable Integer id) throws JsonProcessingException {
        if (id == null) id=0;
        LocalDate localDate=LocalDate.now().plusMonths(id);
        model.addAttribute("local", localDate);
        List<DateDTO> dates=dateService.showDatesForMonth(userService.findUserByEmail(
                    principal.getName()).getId(), localDate.getMonth());
        List<Integer> days = dates.stream().map(dateDTO -> Integer.parseInt(dateDTO.getDateof().substring(8))).toList();
        model.addAttribute("dates",dates);

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        model.addAttribute("datesjson", objectMapper.writeValueAsString(dates));
        model.addAttribute("days", days);
        return "calendar";
    }

    @GetMapping("/calendar")
    public String nowCalendar(){
        return "redirect:/calendar/0";
    }
    @ResponseBody
    @GetMapping("/dates")
    public List<DateDTO> showDates(Principal principal) {
        return dateService.showDatesForUser(
                userService.findUserByEmail(principal.getName()).getId());
    }

    @ResponseBody
    @GetMapping("/dates/formonth")
    public List<DateDTO> showDatesForMonth(Principal principal) {
        return dateService.showDatesForMonth(
                userService.findUserByEmail(
                        principal.getName()).getId(), LocalDate.now().getMonth());
    }

    @GetMapping("/dates/save")
    public String getSaveDate(Model model){
        DateDTO dateDTO = new DateDTO();
        model.addAttribute("date",dateDTO);
        return "/savedate";
    }
    @GetMapping("/dates/edit")
    public String getEditDate(@RequestParam("dateId")long id, Model model){
        DateDTO dateDTO = dateService.showDate(id);
        model.addAttribute("date",dateDTO);
        return "/savedate";
    }
    @GetMapping("/dates/delete")
    public String getDeleteDate(@RequestParam("dateId")long id){
        dateService.deleteDate(id);
        return "redirect:/calendar";
    }
    @PostMapping("/dates/save")
    public String saveDate(@ModelAttribute("date")DateDTO dateDTO){
        dateService.saveDate(dateDTO);
        return "redirect:/calendar";
    }
    @ResponseBody
    @GetMapping("/date")
    public DateDTO getDate(@RequestParam("id") Long id){
        return dateService.showDate(id);
    }
}
