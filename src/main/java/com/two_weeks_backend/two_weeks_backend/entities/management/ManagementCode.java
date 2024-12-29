package com.two_weeks_backend.two_weeks_backend.entities.management;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "management_code")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagementCode extends BaseEntity {
    @Column(name = "entity_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityName entityName;

    @Column(name = "codes", length = 3000, nullable = false)
    private String codes;

    @Column(name = "digits", nullable = false)
    @Enumerated(EnumType.STRING)
    private Digits digits;

    public String getCode() {
        return "";
    }

    public void addCode(String code) {
    }

    public void subtractCode(String code) {
    }

    public String generateCodes() {
        return "";
    }

    public boolean allCodesAreUsed() {
        return true;
    }

    @Override
    public BaseShowDTO asShowDTO() {
        throw new UnsupportedOperationException("Unimplemented method 'asShowDTO'");
    }
}
