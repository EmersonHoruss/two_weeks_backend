package com.two_weeks_backend.two_weeks_backend.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user.UserActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user.UserCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user.UserShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user.UserUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;
import com.two_weeks_backend.two_weeks_backend.services.user.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController
        extends BaseControllerImplementation<User, UserCreateDTO, UserShowDTO, UserUpdateDTO, UserActivatedDTO> {
    @Autowired
    private UserService userService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody UserActivatedDTO dto) {
        User user = this.userService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(user.asShowDTO()));
    }
}
