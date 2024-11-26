package com.two_weeks_backend.two_weeks_backend.services.user;

import com.two_weeks_backend.two_weeks_backend.entities.user.Profile;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends BaseServiceImplementation<Profile>{

    @Override
    @Transactional
    public Profile create(Profile profile){
        Profile savedProfile = super.create(profile);
        return savedProfile;
    }

}
