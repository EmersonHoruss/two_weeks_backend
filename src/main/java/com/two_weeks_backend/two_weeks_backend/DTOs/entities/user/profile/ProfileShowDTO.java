package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.user.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileShowDTO extends BaseShowDTO<Profile>{
    private String name;
    private String secondName;
    private String surname;
    private String secondSurname;
    private String dni;
    private String phone;
    private String familiarPhone;
    private String whatsapp;
    private String address;
    private String photo;
}
