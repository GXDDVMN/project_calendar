package com.drobov.project.project_calendar.controller;

import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.service.DateService;
import com.drobov.project.project_calendar.service.UserService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;
    @GetMapping("/calendar")
    public String calendar(Model model,Principal principal) throws JsonProcessingException {
        LocalDate localdate = LocalDate.now();
        model.addAttribute("month", localdate);
        model.addAttribute("year", localdate.getYear());
        List<DateDTO> dates=dateService.showDatesForMonth(userService.findUserByEmail(
                principal.getName()).getId(), LocalDate.now().getMonth());
        List<Integer> days = dates.stream().map(dateDTO -> Integer.parseInt(dateDTO.getDateof().substring(8))).toList();
        model.addAttribute("dates",dates);

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        model.addAttribute("datesjson", objectMapper.writeValueAsString(dates));
        model.addAttribute("days", days);
        return "calendar";
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
    @PostMapping("/dates/save")
    public String saveDate(@ModelAttribute("date")DateDTO dateDTO){
        dateService.saveDate(dateDTO);
        return "redirect:/dates";
    }
    @ResponseBody
    @GetMapping("/date")
    public DateDTO getDate(@RequestParam("id") Long id){
        return dateService.showDate(id);
    }
}
