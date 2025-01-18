package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change.SellChangeShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sell_change")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellChange extends BaseEntity<SellChangeShowDTO> {
    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "sell_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_change_sell"))
    private Sell sell;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public SellChangeShowDTO asShowDTO() {
        SellChangeShowDTO sellChangeShowDTO = new SellChangeShowDTO();
        sellChangeShowDTO.setId(this.getId());
        sellChangeShowDTO.setDate(this.getDate());
        sellChangeShowDTO.setActivated(this.getActivated());
        return sellChangeShowDTO;
    }
}
