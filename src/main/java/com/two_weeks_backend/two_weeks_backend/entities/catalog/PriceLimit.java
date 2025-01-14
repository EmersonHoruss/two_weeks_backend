package com.two_weeks_backend.two_weeks_backend.entities.catalog;

public enum PriceLimit {
    NORMAL_FROM(1),
    NORMAL_TO(2),
    WHOLESALE_FROM(3),
    WHOLESALE_TO(5),
    SUPER_WHOLESAE_FROM(6);

    private final int value;

    PriceLimit(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
