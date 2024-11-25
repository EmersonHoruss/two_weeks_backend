package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;
import com.two_weeks_backend.two_weeks_backend.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImplementation<User>{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public User create(User user){
        User savedUser = super.create(user);
        savedUser.setProfile(this.loadProfile(savedUser.getProfile().getId()));
        return savedUser;
    }

    private Profile loadProfile(Long profileId){
        return profileRepository.findById(profileId)
            .orElseThrow(() -> new RuntimeException("profile not found"));
    }
}
