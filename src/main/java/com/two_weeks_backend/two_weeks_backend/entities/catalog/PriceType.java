package com.two_weeks_backend.two_weeks_backend.entities.catalog;

public enum PriceType {
    NORMAL("Normal"),
    WHOLESALE("Wholesale"),
    SUPER_WHOLESAE("Super wholesale"),
    AUCTION("Auction"),
    PREFERENCE("Preference");

    private String value;

    PriceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
