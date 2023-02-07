package com.drobov.project.project_calendar.service;

import com.drobov.project.project_calendar.dto.WorkDTO;
import com.drobov.project.project_calendar.entity.Date;
import com.drobov.project.project_calendar.entity.User;
import com.drobov.project.project_calendar.entity.Work;
import com.drobov.project.project_calendar.repository.UserRepository;
import com.drobov.project.project_calendar.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<WorkDTO> showWorksForUser(long user_id) {
        return mapToListDTO(workRepository.findAllByUser_Id(user_id));
    }

    @Override
    public WorkDTO findById(long id) {
        return mapToWorkDTO(workRepository.findById(id).get());
    }

    @Override
    public WorkDTO mapToWorkDTO(Work work) {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setId(work.getId());
        workDTO.setColor(work.getColor());
        workDTO.setSchedule(work.getSchedule());
        workDTO.setStarttime(work.getStarttime());
        workDTO.setEndtime(work.getEndtime());
        workDTO.setSalary(work.getSalary());
        workDTO.setName(work.getName());
        workDTO.setUser_id(work.getUser().getId());
        workDTO.setStart_day(work.getStart_day());
        return workDTO;
    }

    @Override
    public List<WorkDTO> mapToListDTO(List<Work> works) {
        return works.stream().map(work -> mapToWorkDTO(work)).collect(Collectors.toList());
    }


    @Override
    public boolean[][] mapToMass(List<WorkDTO> works, LocalDate localdate) {
        boolean[][] arr = new boolean[works.size()][localdate.lengthOfMonth()];
        for (int i = 0; i < works.size(); i++) {
            LocalDate workdate = LocalDate.parse(works.get(i).getStart_day());
            if (workdate.withDayOfMonth(1).isAfter(localdate.withDayOfMonth(1))) continue;
            int k = (int) workdate.until(localdate.withDayOfMonth(1), ChronoUnit.DAYS);
            switch (works.get(i).getSchedule().charAt(0)) {

                case '5': {
                    k = localdate.withDayOfMonth(1).getDayOfWeek().getValue();
                    for (int j = k; j - k < arr[0].length; j++) {
                        if (j % 7 < 6 & (j % 7) != 0)
                            arr[i][j - k] = true;
                    }
                    if (workdate.withDayOfMonth(1).isEqual(localdate.withDayOfMonth(1))) {
                        for (int j = 0;j < workdate.getDayOfMonth(); j++)
                            arr[i][j]=false;
                    }
                    break;
                }
                case '2': {

                    k=(k%4)+1;
                    if (workdate.withDayOfMonth(1).isEqual(localdate.withDayOfMonth(1))) k = -workdate.getDayOfMonth()+2;
                    for (int j = 1-k, d=1; j < arr[0].length;d++, j++) {
                        if (((d % 4) < 3) & ((d % 4)!=0)){
                            if (j>=0)
                                arr[i][j] = true;
                        }
                    }
                    break;
                }
                case '3': {

                    k=(k%6)+1;
                    if (workdate.withDayOfMonth(1).isEqual(localdate.withDayOfMonth(1))) k = -workdate.getDayOfMonth()+2;
                    for (int j = 1-k, d=1; j < arr[0].length;d++, j++) {
                        if (((d % 6) < 4) & ((d % 6)!=0)){
                            if (j>=0)
                                arr[i][j] = true;
                        }
                    }
                    break;
                }
                case '1': {
                    k=(k%4)+1;
                    if (workdate.withDayOfMonth(1).isEqual(localdate.withDayOfMonth(1))) k = -workdate.getDayOfMonth()+2;
                    for (int j = 1-k, d=1; j < arr[0].length;d++, j++) {
                        if (((d % 4) < 2) & ((d % 4)!=0)){
                            if (j>=0)
                                arr[i][j] = true;
                        }
                    }
                    break;
                }


            }

        }
        return arr;
    }

    @Override
    public void saveWork(WorkDTO workDTO) {
        User user= userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Work work=mapDTOToWork(workDTO);
        work.setUser(user);
        workRepository.save(work);
    }

    @Override
    public void deleteWork(long id) {
        workRepository.deleteById(id);
    }

    private Work mapDTOToWork(WorkDTO workDTO) {
        Work work = new Work();
        work.setId(workDTO.getId());
        work.setStarttime(workDTO.getStarttime());
        work.setEndtime(workDTO.getEndtime());
        work.setSalary(workDTO.getSalary());
        work.setName(workDTO.getName());
        work.setColor(workDTO.getColor());
        work.setSchedule(workDTO.getSchedule());
        work.setStart_day(workDTO.getStart_day());
        return work;
    }
}
