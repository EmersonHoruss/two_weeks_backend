package com.two_weeks_backend.two_weeks_backend.repositories;

import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.UsuarioEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

@Repository
public interface UsuarioRepository extends BaseRepository<UsuarioEntity> {}
