package com.drobov.project.project_calendar.service;

import com.drobov.project.project_calendar.dto.WorkDTO;
import com.drobov.project.project_calendar.entity.Work;

import java.time.LocalDate;
import java.util.List;

public interface WorkService {
    List<WorkDTO> showWorksForUser(long user_id);

    WorkDTO findById(long id);

    WorkDTO mapToWorkDTO(Work work);

    List<WorkDTO> mapToListDTO(List<Work> works);

    boolean[][] mapToMass(List<WorkDTO> works, LocalDate localdate);

    void saveWork(WorkDTO workDTO);

    void deleteWork(long id);
}
