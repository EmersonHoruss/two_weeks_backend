package com.two_weeks_backend.two_weeks_backend.entities.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile.ProfileShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile extends BaseEntity {
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    private String secondName;
    @Column(length = 255)
    private String surname;
    @Column(length = 255)
    private String secondSurname;
    @Column(length = 8)
    private String dni;
    @Column(length = 9)
    private String phone;
    @Column(length = 9)
    private String familiarPhone;
    @Column(length = 9)
    private String whatsapp;
    @Column(length = 255)
    private String address;
    @Column(length = 255)
    private String photo;

    @Override
    public ProfileShowDTO asShowDTO(){
        ProfileShowDTO profileShowDTO = new ProfileShowDTO();
        profileShowDTO.setId(this.getId());
        profileShowDTO.setName(this.getName());
        profileShowDTO.setSecondName(this.getSecondName());
        profileShowDTO.setSurname(this.getSurname());
        profileShowDTO.setSecondSurname(this.getSecondSurname());
        profileShowDTO.setDni(this.getDni());
        profileShowDTO.setPhone(this.getPhone());
        profileShowDTO.setFamiliarPhone(this.getFamiliarPhone());
        profileShowDTO.setWhatsapp(this.getWhatsapp());
        profileShowDTO.setAddress(this.getAddress());
        profileShowDTO.setPhoto(this.getPhoto());
        return profileShowDTO;
    }
}
