package com.two_weeks_backend.two_weeks_backend.DTOs.entities.profile;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class ProfileUpdateDTO extends BaseUpdateDTO<Profile>{
    private String name;
    private String secondName;
    private String surname;
    private String secondSurname;
    @Size(min = 8, max = 8)
    private String dni;
    @Size(min = 9, max = 9)
    private String phone;
    @Size(min = 9, max = 9)
    private String familiarPhone;
    @Size(min = 9, max = 9)
    private String whatsapp;
    private String address;
    private String photo;

    @Override
    public Profile asEntity() {
        Profile profile = new Profile();
        profile.setId(this.getId());
        profile.setName(this.getName());
        profile.setSecondName(this.getSecondName());
        profile.setSurname(this.getSurname());
        profile.setSecondSurname(this.getSecondSurname());
        profile.setDni(this.getDni());
        profile.setPhone(this.getPhone());
        profile.setFamiliarPhone(this.getFamiliarPhone());
        profile.setWhatsapp(this.getWhatsapp());
        profile.setAddress(this.getAddress());
        profile.setPhoto(this.getPhoto());
        return profile;
    }

}
