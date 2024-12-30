package com.two_weeks_backend.two_weeks_backend.entities.management;

public enum Digits {
    TWO(2),
    THREE(3),
    FOUR(4);

    private int value;

    Digits(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Digits fromString(int value) {
        for (Digits digits : Digits.values()) {
            if (digits.value == value) {
                return digits;
            }
        }
        throw new IllegalArgumentException("Digito desconocido:  " + value);
    }
}
