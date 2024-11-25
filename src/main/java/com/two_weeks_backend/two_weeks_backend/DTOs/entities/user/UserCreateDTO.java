package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
import org.springframework.lang.Nullable;
import java.time.LocalDate;

@Getter
@Setter
public class UserCreateDTO extends BaseCreateDTO<User> {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String userStatus;
    private Long profile;

    @Override
    public User asEntity() {
        User user = new User();
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setUserStatus(this.getUserStatus());
        user.setProfile(this.getProfileEntity());
        return user;
    }

    private Profile getProfileEntity(){
        if(this.getProfile()!=null){
            Profile profile = new Profile();
            profile.setId(this.getProfile());
            return profile;
        }
        return null;
    }
}
