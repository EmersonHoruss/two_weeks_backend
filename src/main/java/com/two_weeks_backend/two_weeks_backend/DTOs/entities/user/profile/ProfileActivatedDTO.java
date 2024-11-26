package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.profile;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.user.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileActivatedDTO extends BaseActivatedDTO<Profile> {
    @Override
    public Profile asEntity() {
        Profile profile = new Profile();
        profile.setId(this.getId());
        profile.setActivated(this.getActivated());
        return profile;
    }
}
