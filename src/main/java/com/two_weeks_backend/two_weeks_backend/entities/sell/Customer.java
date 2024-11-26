package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer.CustomerShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity {
    @Column(length = 255, nullable = false)
    private String identity;
    @Column(length = 255, nullable = false)
    private String firstName;
    @Column(length = 255, nullable = false)
    private String surname;
    @Column(length = 255)
    private String secondName;
    @Column(length = 255)
    private String secondSurname;
    @Column(length = 255)
    private String fullName;

    @Override
    public CustomerShowDTO asShowDTO(){
        CustomerShowDTO customerShowDTO = new CustomerShowDTO();
        customerShowDTO.setId(this.getId());
        customerShowDTO.setActivated(this.getActivated());
        customerShowDTO.setIdentity(this.getIdentity());
        customerShowDTO.setFirstName(this.getFirstName());
        customerShowDTO.setSurname(this.getSurname());
        customerShowDTO.setSecondName(this.getSecondName());
        customerShowDTO.setSecondSurname(this.getSecondSurname());
        customerShowDTO.setFullName(this.getFullName());
        return customerShowDTO;
    }
}
