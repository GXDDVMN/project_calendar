package com.drobov.project.project_calendar.repository;

import com.drobov.project.project_calendar.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllByUser_Id(long user_id);
}
