package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UserUpdateDTO extends BaseUpdateDTO<User> {
    @NotBlank
    private String nickanme;

    private String photo;

    @NotBlank
    private String roles;

    @Override
    public User asEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setNickname(this.getNickanme());
        user.setPhoto(this.getPhoto());
        user.setRoles(this.getRoles());
        return user;
    }
}
