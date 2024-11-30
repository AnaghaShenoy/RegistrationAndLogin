package com.study.registrationsystem.service;

import com.study.registrationsystem.dto.UserDto;
import com.study.registrationsystem.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
