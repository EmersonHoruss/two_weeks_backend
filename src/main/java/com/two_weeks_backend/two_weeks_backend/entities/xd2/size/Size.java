package com.two_weeks_backend.two_weeks_backend.entities.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "size", uniqueConstraints = {
        @UniqueConstraint(name = "uq_name", columnNames = "name")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Size extends BaseEntity {
    @Column(length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (activated == null) {
            activated = true;
        }
    }

    @Override
    public SizeShowDTO asShowDTO() {
        SizeShowDTO sizeShowDTO = new SizeShowDTO();
        sizeShowDTO.setId(this.getId());
        sizeShowDTO.setActivated(this.getActivated());
        sizeShowDTO.setName(this.getName());
        return sizeShowDTO;
    }
}
