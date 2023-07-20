package com.drobov.project.project_calendar.repository;

import com.drobov.project.project_calendar.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
