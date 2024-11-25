package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist(){
        if(activated == null){
            activated = true;
        }
    }

    public abstract ShowDTO asShowDTO();
}
