package com.drobov.project.project_calendar.service;

import com.drobov.project.project_calendar.dto.UserDTO;
import com.drobov.project.project_calendar.entity.User;

import java.util.List;

public interface UserService{
    void saveUser(UserDTO userDto);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();
}
