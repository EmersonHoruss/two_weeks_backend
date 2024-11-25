package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.profile.ProfileActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.profile.ProfileCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.profile.ProfileShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.profile.ProfileUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/profiles")
public class ProfileController extends BaseControllerImplementation<
    Profile,
    ProfileCreateDTO,
    ProfileShowDTO,
    ProfileUpdateDTO,
    ProfileActivatedDTO
>{
}
