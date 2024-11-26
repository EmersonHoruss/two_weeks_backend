package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivatedDTO extends BaseActivatedDTO<User> {
    @Override
    public User asEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setActivated(this.getActivated());
        return user;
    }
}
