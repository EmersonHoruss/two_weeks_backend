package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.UserShowDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity {
    @Column(length = 255, nullable = false)
    private String email;
    @Column(length = 255, nullable = false)
    private String password;
    @Column(length = 255, nullable = false)
    private String userStatus;
    @ManyToOne
    private Profile profile;

    @Override
    public UserShowDTO asShowDTO(){
        UserShowDTO userShowDTO = new UserShowDTO();
        userShowDTO.setId(this.getId());
        userShowDTO.setActivated(this.getActivated());
        userShowDTO.setEmail(this.getEmail());
        userShowDTO.setPassword(this.getPassword());
        userShowDTO.setUserStatus(this.getUserStatus());
        userShowDTO.setProfile(this.getProfile().asShowDTO());
        return userShowDTO;
    }
}
