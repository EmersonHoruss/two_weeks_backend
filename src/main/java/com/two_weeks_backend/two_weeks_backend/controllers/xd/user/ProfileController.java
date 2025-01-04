package com.two_weeks_backend.two_weeks_backend.controllers.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile.ProfileActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile.ProfileCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile.ProfileShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile.ProfileUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.user.Profile;

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
