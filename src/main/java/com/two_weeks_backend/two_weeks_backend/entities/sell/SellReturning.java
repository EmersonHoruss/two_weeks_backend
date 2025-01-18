package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning.SellReturningShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sell_returning")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellReturning extends BaseEntity<SellReturningShowDTO> {
    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "sell_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_returning_sell"))
    private Sell sell;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public SellReturningShowDTO asShowDTO() {
        SellReturningShowDTO sellReturningShowDTO = new SellReturningShowDTO();
        sellReturningShowDTO.setId(this.getId());
        sellReturningShowDTO.setDate(this.getDate());
        sellReturningShowDTO.setActivated(this.getActivated());
        return sellReturningShowDTO;
    }
}
