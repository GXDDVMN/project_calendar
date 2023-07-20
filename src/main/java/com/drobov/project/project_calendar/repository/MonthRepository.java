package com.drobov.project.project_calendar.repository;

import com.drobov.project.project_calendar.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MonthRepository extends JpaRepository<Month, Long> {
    List<Month> findAllByUser_IdAndMonth(long user_id, LocalDate month);
}
