package com.two_weeks_backend.two_weeks_backend.entities.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.two_weeks_backend.two_weeks_backend.exceptions.NoCodeAvailable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "management_code")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagementCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EntityName entityName;

    @Column(name = "codes", length = 50000, nullable = false)
    private String codes;

    @Column(name = "digits", nullable = false)
    @Enumerated(EnumType.STRING)
    private Digits digits;

    public ManagementCode(EntityName entityName) {
        this.entityName = entityName;
        this.digits = ManagementCode.generateDigits(this.entityName);
        List<String> codes = ManagementCode.generateCodes(this.digits);
        this.saveCodes(codes);
    }

    public String getFirstCode() {
        List<String> codes = this.parseCodes();
        if (!this.allCodesAreUsed())
            return codes.get(0);
        else {
            String pronoum = this.entityName.getValue().equals(EntityName.PRODUCT.getValue())
                    || this.entityName.getValue().equals(EntityName.TYPE.getValue()) ? "el" : "la";
            String message = "No hay código disponibles para " + pronoum + " " + entityName.getValue();
            throw new NoCodeAvailable(message);
        }
    }

    private List<String> parseCodes() {
        if (this.codes == null || this.codes.isEmpty()) {
            return new ArrayList<>();
        }
        String[] codes = this.codes.split(",");
        List<String> codeList = new ArrayList<>();
        Collections.addAll(codeList, codes);
        return codeList;
    }

    public boolean allCodesAreUsed() {
        if (this.codes == null)
            throw new RuntimeException("códigos no inicializados");
        return this.codes.isEmpty();
    }

    public void addCode(String code) {
        List<String> codeList = this.parseCodes();
        if (!codeList.contains(code)) {
            codeList.add(code);
            this.saveCodes(codeList);
        }
    }

    private void saveCodes(List<String> codes) {
        this.codes = String.join(",", codes);
    }

    public void subtractCode(String code) {
        List<String> codeList = this.parseCodes();
        if (codeList.contains(code)) {
            codeList.remove(code);
            this.saveCodes(codeList);
        }
    }

    public static List<String> generateCodes(Digits digits) {
        int maxDigits = digits == Digits.TWO
                ? 99
                : digits == Digits.THREE
                        ? 999
                        : 9999;
        List<String> generatedCodes = new ArrayList<>();

        for (int i = 0; i <= maxDigits; i++) {
            String code = String.format("%0" + digits.getValue() + "d", i);
            generatedCodes.add(code);
        }

        return generatedCodes;
    }

    public static Digits generateDigits(EntityName entityName) {
        if (entityName == EntityName.TYPE)
            return Digits.THREE;
        if (entityName == EntityName.BRAND)
            return Digits.TWO;
        if (entityName == EntityName.SIZE)
            return Digits.TWO;
        if (entityName == EntityName.PRODUCT)
            return Digits.FOUR;

        throw new IllegalArgumentException("nombre de entidad no reconocida: " + entityName);
    }
}
