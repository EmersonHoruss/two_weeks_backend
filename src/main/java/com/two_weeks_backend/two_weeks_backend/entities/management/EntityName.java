package com.two_weeks_backend.two_weeks_backend.entities.management;

public enum EntityName {
    TYPE("Type"),
    BRAND("Brand"),
    SIZE("Size"),
    PRODUCT("Product");

    private String value;

    EntityName(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static EntityName fromString(String value) {
        for (EntityName entityName : EntityName.values()) {
            if (entityName.value.equalsIgnoreCase(value)) {
                return entityName;
            }
        }
        throw new IllegalArgumentException("Entidad desconocida:  " + value);
    }
}
