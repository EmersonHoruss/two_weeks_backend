package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile.ProfileShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShowDTO extends BaseShowDTO<User>{
    private String email;
    private String password;
    private String userStatus;
    private ProfileShowDTO profile;
    private boolean activated;
}
