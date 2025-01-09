package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import lombok.*;

@Getter
@Setter
public class UserShowDTO extends BaseShowDTO<User> {
    private String nickame;
    private String photo;
    private String roles;
    private boolean activated;
}
