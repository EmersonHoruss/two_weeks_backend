package com.two_weeks_backend.two_weeks_backend.entities.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.brand.BrandShowDTO;
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
@Table(name = "brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Brand extends BaseEntity {
    @Column(length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (activated == null) {
            activated = true;
        }
    }

    @Override
    public BrandShowDTO asShowDTO() {
        BrandShowDTO brandShowDTO = new BrandShowDTO();
        brandShowDTO.setId(this.getId());
        brandShowDTO.setActivated(this.getActivated());
        brandShowDTO.setName(this.getName());
        brandShowDTO.setCode(this.getCode());
        return brandShowDTO;
    }
}
