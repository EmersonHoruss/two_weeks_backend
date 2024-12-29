package com.two_weeks_backend.two_weeks_backend.entities.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type.TypeShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Type extends BaseEntity {
    @Column(length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @Column(name = "code", nullable = false)
    private String code;

    @PrePersist
    private void prePersist() {
        if (activated == null) {
            activated = true;
        }
    }

    @Override
    public TypeShowDTO asShowDTO() {
        TypeShowDTO typeShowDTO = new TypeShowDTO();
        typeShowDTO.setId(this.getId());
        typeShowDTO.setActivated(this.getActivated());
        typeShowDTO.setName(this.getName());
        return typeShowDTO;
    }
}
