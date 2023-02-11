package com.drobov.project.project_calendar.repository;


import com.drobov.project.project_calendar.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
    List<Date> findAllByUser_Id(long user_id);
}
