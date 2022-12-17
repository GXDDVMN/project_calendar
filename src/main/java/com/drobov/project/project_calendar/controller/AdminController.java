package com.drobov.project.project_calendar.controller;


import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.dto.UserDTO;
import com.drobov.project.project_calendar.entity.Date;
import com.drobov.project.project_calendar.service.DateService;
import com.drobov.project.project_calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/all-dates")
    public List<DateDTO> showAllDates(){
        return dateService.showDates();
    }
    @GetMapping("/users")
    public String users(Model model){
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

}
