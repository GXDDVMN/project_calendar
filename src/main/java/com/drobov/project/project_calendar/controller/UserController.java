package com.drobov.project.project_calendar.controller;

import com.drobov.project.project_calendar.dto.DateDTO;
import com.drobov.project.project_calendar.dto.MonthDTO;
import com.drobov.project.project_calendar.dto.WorkDTO;
import com.drobov.project.project_calendar.service.DateService;
import com.drobov.project.project_calendar.service.MonthService;
import com.drobov.project.project_calendar.service.UserService;
import com.drobov.project.project_calendar.service.WorkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;
    @Autowired
    private MonthService monthService;
    @Autowired
    private WorkService workService;

    @GetMapping("/calendar/{id}")
    public String calendar(Model model, Principal principal, @PathVariable Integer id) throws JsonProcessingException {
        if (id == null) id = 0;
        LocalDate localDate = LocalDate.now().plusMonths(id);
        model.addAttribute("local", localDate);
        long user_id = userService.findUserByEmail(principal.getName()).getId();
        List<DateDTO> dates = dateService.showDatesForMonth(user_id, localDate);
        List<Integer> days = dates.stream().map(dateDTO -> Integer.parseInt(dateDTO.getDateof().substring(8))).toList();

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        model.addAttribute("datesjson", objectMapper.writeValueAsString(dates));
        model.addAttribute("days", days);


        List<MonthDTO> notes = monthService.getNotesForUser(user_id, YearMonth.from(localDate));
        model.addAttribute("notes", objectMapper.writeValueAsString(notes));
        MonthDTO newMonth = new MonthDTO();
        newMonth.setMonth(YearMonth.from(localDate));
        model.addAttribute("note", newMonth);
        List<WorkDTO> works = workService.showWorksForUser(user_id);
        model.addAttribute("works", works);
        model.addAttribute("worksjson", objectMapper.writeValueAsString(works));
        boolean[][] arr = workService.mapToMass(works, localDate);
        model.addAttribute("worksbool", objectMapper.writeValueAsString(arr));
        return "calendar";
    }

    @GetMapping("/calendar")
    public String nowCalendar() {
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
                        principal.getName()).getId(), LocalDate.now());
    }

    @GetMapping("/dates/save")
    public String getSaveDate(Model model) {
        DateDTO dateDTO = new DateDTO();
        model.addAttribute("date", dateDTO);
        return "/savedate";
    }

    @GetMapping("/dates/edit")
    public String getEditDate(@RequestParam("dateId") long id, Model model) {
        DateDTO dateDTO = dateService.showDate(id);
        model.addAttribute("date", dateDTO);
        return "/savedate";
    }

    @GetMapping("/dates/delete")
    public String getDeleteDate(@RequestParam("dateId") long id) {
        dateService.deleteDate(id);
        return "redirect:/calendar";
    }

    @PostMapping("/dates/save")
    public String saveDate(@Valid @ModelAttribute("date") DateDTO dateDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("date", dateDTO);
            return "/savedate";
        }
        dateService.saveDate(dateDTO);
        return "redirect:/calendar";
    }

    @PostMapping("/notes/save")
    public String saveNote(@ModelAttribute("note") MonthDTO monthDTO) {
        monthService.saveNote(monthDTO);
        return "redirect:/calendar";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("noteId") long id) {
        monthService.deleteNote(id);
        return "redirect:/calendar";
    }

    @GetMapping("/works/save")
    public String getSaveWork(Model model) {
        WorkDTO workDTO = new WorkDTO();
        model.addAttribute("work", workDTO);
        return "/savework";
    }

    @PostMapping("/works/save")
    public String saveWork(@Valid @ModelAttribute("work") WorkDTO workDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("work", workDTO);
            return "/savework";
        }
        workService.saveWork(workDTO);
        return "redirect:/calendar";
    }

    @GetMapping("/works/delete")
    public String deleteWork(@RequestParam("workId") long id) {
        workService.deleteWork(id);
        return "redirect:/calendar";
    }

    @GetMapping(value = "/works/saveColor")
    public String saveWorkColor(@RequestParam("workId") long id, @RequestParam("colorId") String colorId) {
        WorkDTO work = workService.findById(id);
        work.setColor("#" + colorId);
        workService.saveWork(work);
        return "redirect:/calendar";
    }

    @ResponseBody
    @GetMapping("/date")
    public DateDTO getDate(@RequestParam("id") Long id) {
        return dateService.showDate(id);
    }
}
