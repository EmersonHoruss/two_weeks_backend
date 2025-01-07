package com.two_weeks_backend.two_weeks_backend.utils;

public class BarCodeGenerator {
    public static final int COUNTRY_CODE_LENGTH = 3;
    public static final int COMPANY_CODE_MIN_LENGTH = 1;
    public static final int COMPANY_CODE_MAX_LENGTH = 6;
    public static final int EAN12_LENGTH = 12;
    public static final int EAN13_LENGTH = 13;

    public static String firstCodeBar(String countryCode, String companyCode) {
        if (countryCode.length() != COUNTRY_CODE_LENGTH)
            throw new IllegalArgumentException("El código del país debe tener " + COUNTRY_CODE_LENGTH + " dígitos");

        if (companyCode.length() < COMPANY_CODE_MIN_LENGTH || companyCode.length() > COMPANY_CODE_MAX_LENGTH)
            throw new IllegalArgumentException("El código de la empresa debe tener entre " + COMPANY_CODE_MIN_LENGTH
                    + " y " + COMPANY_CODE_MAX_LENGTH + " dígitos");

        int productCodeLength = EAN12_LENGTH - (countryCode.length() + companyCode.length());
        String productCode = String.format("%0" + productCodeLength + "d", 0);

        String ean12 = countryCode + companyCode + productCode;

        int checkDigit = BarCodeGenerator.calculateCheckDigit(ean12);

        String ean13 = ean12 + checkDigit;
        return ean13;
    }

    public static String getNextCodeBar(String countryCode, String companyCode, String ean13) {
        if (countryCode.length() != COUNTRY_CODE_LENGTH)
            throw new IllegalArgumentException("El código del país debe tener " + COUNTRY_CODE_LENGTH + " dígitos");

        if (ean13.length() != EAN13_LENGTH)
            throw new IllegalArgumentException("El código de barras debe tener " + EAN13_LENGTH + " dígitos");

        String extractedCountryCode = ean13.substring(0, COUNTRY_CODE_LENGTH);
        String extractedCompanyCode = ean13.substring(COUNTRY_CODE_LENGTH, COUNTRY_CODE_LENGTH + companyCode.length());
        String currentProductCode = ean13.substring(COUNTRY_CODE_LENGTH + companyCode.length(), EAN12_LENGTH);

        if (!extractedCountryCode.equals(countryCode)) {
            throw new IllegalArgumentException("El código de país extraído no coincide con el proporcionado.");
        }
        if (!extractedCompanyCode.equals(companyCode)) {
            throw new IllegalArgumentException("El código de empresa extraído no coincide con el proporcionado.");
        }

        int productCode = Integer.parseInt(currentProductCode);
        productCode++;

        int productCodeLength = EAN12_LENGTH - (countryCode.length() + companyCode.length());
        String nextProductCode = String.format("%0" + productCodeLength + "d", productCode);

        String ean12 = countryCode + companyCode + nextProductCode;

        int checkDigit = calculateCheckDigit(ean12);

        return ean12 + checkDigit;
    }

    public static boolean validateCodeBar(String ean13) {
        if (ean13.length() != EAN13_LENGTH) {
            return false;
        }

        String ean12 = ean13.substring(0, EAN12_LENGTH);

        int expectedCheckDigit = BarCodeGenerator.calculateCheckDigit(ean12);

        int actualCheckDigit = Character.getNumericValue(ean13.charAt(EAN12_LENGTH));

        return expectedCheckDigit == actualCheckDigit;
    }

    public static int calculateCheckDigit(String ean13) {
        if (ean13.length() != EAN12_LENGTH)
            throw new IllegalArgumentException(
                    "El código de barras debe tener " + EAN12_LENGTH + " dígitos sin el dígito de verificación");

        int sumOdd = 0;
        int sumEven = 0;

        for (int i = 0; i < EAN12_LENGTH; i++) {
            int digit = Character.getNumericValue(ean13.charAt(i));

            if (i % 2 == 0) {
                sumOdd += digit;
            } else {
                sumEven += digit * 3;
            }
        }

        int totalSum = sumOdd + sumEven;

        return (10 - (totalSum % 10)) % 10;
    }
}
