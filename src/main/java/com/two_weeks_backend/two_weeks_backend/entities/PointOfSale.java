package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.pointOfSale.PointOfSaleShowDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "point_of_sale")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointOfSale extends BaseEntity {
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private float initialAmount;
    @Column()
    private float brutalEarning;
    @Column()
    private float virtualAmount;
    @Column()
    private float phisicalAmount;

    @Override
    public PointOfSaleShowDTO asShowDTO(){
        PointOfSaleShowDTO pointOfSaleShowDTO = new PointOfSaleShowDTO();
        pointOfSaleShowDTO.setId(this.getId());
        pointOfSaleShowDTO.setActivated(this.getActivated());
        pointOfSaleShowDTO.setDate(this.getDate());
        pointOfSaleShowDTO.setInitialAmount(this.getInitialAmount());
        pointOfSaleShowDTO.setBrutalEarning(this.getBrutalEarning());
        pointOfSaleShowDTO.setVirtualAmount(this.getVirtualAmount());
        pointOfSaleShowDTO.setPhisicalAmount(this.getPhisicalAmount());
        return pointOfSaleShowDTO;
    }
}
