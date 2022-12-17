package com.drobov.project.project_calendar.controller;

import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.service.DateService;
import com.drobov.project.project_calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;
    @GetMapping("/calendar")
    public String calendar(Model model) {
        LocalDate localdate = LocalDate.now();
        model.addAttribute("month", localdate);
        model.addAttribute("year", localdate.getYear());
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
//    @PostMapping("/dates/save")
//    public String saveDate(@ModelAttribute("date")DateDTO dateDTO){
//        dateService.saveDate(dateDTO);
//    }
}
