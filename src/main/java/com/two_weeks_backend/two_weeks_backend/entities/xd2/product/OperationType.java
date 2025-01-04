package com.two_weeks_backend.two_weeks_backend.entities.product;

public enum OperationType {
    ADD("Add"),
    SUBTRACT("Subtract");

    private String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
