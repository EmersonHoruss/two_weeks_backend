package com.two_weeks_backend.two_weeks_backend.entities.product;

import com.two_weeks_backend.two_weeks_backend.entities.company.Company;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bar_code")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// This barcode follows the EAN-13 standard, but not strictly. The reason for
// this is that the company code
// is not an officially registered GS1 company code. In reality, official
// barcodes are assigned and regulated
// by organizations like GS1 (e.g., GS1 Peru), and obtaining a legitimate
// company code requires payment, which
// we are not doing here. This approach is being used as a cost-saving measure
// since registering for a GS1 code
// can be expensive. As a result, the company code included in our barcodes is a
// placeholder and should not
// be used for official product identification or supply chain management that
// strictly adheres to GS1 standards.
public class BarCode {
    public static int CODE_LENGTH = 13;
    public static int CODE_WITHOUT_CONTROL_DIGIT_LENGTH = 12;
    public static int COUNTRY_CODE_MAX_LENGTH = 3;
    public static int COMPANY_CODE_MAX_LENGTH = 4;
    public static String CONTROL_DIGIT_VALUE = "1";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "last_consecutive_bar_code", nullable = false)
    private String lastConsecutiveBarCode;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public BarCode(Company company) {
        if (company == null)
            throw new RuntimeException("Creación de código de barra fallido, necesita a la compañía");

        this.company = company;
        this.generateLastConsecutiveBarCode();
    }

    public void generateLastConsecutiveBarCode() {
        if (company == null)
            throw new RuntimeException(
                    "La compañía debe existir en código de barras para generar el último consecutivo código de barras");

        if (this.lastConsecutiveBarCode == null)
            this.lastConsecutiveBarCode = this.getFirstBarCode();

        int auxBarCode = Integer.parseInt(this.lastConsecutiveBarCode) + 1;
        String format = "%0" + this.getProductCodeLength() + "d";
        this.lastConsecutiveBarCode = String.format(format, auxBarCode);
    }

    private String getFirstBarCode() {
        if (company == null)
            throw new RuntimeException(
                    "La compañía debe existir en código de barras para generar el primer código de barra");

        int productCodeLength = this.getProductCodeLength();
        String firstProductCode = "0".repeat(productCodeLength);

        return this.company.getCountryCode() + this.company.getCompanyCode() + firstProductCode
                + BarCode.CONTROL_DIGIT_VALUE;
    }

    private int getProductCodeLength() {
        if (company == null)
            throw new RuntimeException(
                    "La compañía debe existir en código de barras para obtener la longitud del código del producto");

        int productLength = BarCode.CODE_WITHOUT_CONTROL_DIGIT_LENGTH - company.getCountryCode().length()
                - company.getCompanyCode().length();

        return productLength;
    }

}
