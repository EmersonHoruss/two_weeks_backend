package com.two_weeks_backend.two_weeks_backend.entities.sell;

public enum DocumentType {
    BOLETA("Boleta"),
    FACTURA("Factura"),
    NOTA_DE_VENTA("Nota de venta");

    private String value;

    DocumentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
