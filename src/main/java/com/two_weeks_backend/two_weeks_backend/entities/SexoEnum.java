package com.two_weeks_backend.two_weeks_backend.entities;

enum SexoEnum {
    MASCULINO("Masculino"), FEMENINO("Femenino");

    private final String value;

    SexoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
