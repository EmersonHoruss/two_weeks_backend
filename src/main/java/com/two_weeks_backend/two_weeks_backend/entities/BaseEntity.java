package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class BaseEntity<ShowDTO extends BaseShowDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version; 

    public abstract ShowDTO asShowDTO();
}
