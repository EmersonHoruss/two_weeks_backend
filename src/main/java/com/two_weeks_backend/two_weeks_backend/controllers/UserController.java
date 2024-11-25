package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.UserActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.UserCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.UserShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.UserUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController extends BaseControllerImplementation<
    User,
    UserCreateDTO,
    UserShowDTO,
    UserUpdateDTO,
    UserActivatedDTO
>{
}
