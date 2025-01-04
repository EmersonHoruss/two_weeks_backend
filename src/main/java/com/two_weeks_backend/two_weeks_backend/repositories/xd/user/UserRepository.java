package com.two_weeks_backend.two_weeks_backend.repositories.user;

import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.user.User;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User>{
}
