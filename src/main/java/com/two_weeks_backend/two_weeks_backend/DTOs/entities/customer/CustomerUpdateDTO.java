package com.two_weeks_backend.two_weeks_backend.DTOs.entities.customer;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class CustomerUpdateDTO extends BaseUpdateDTO<Customer>{
    @NotNull
    @Size(min = 8)
    private String identity;
    @NotNull
    private String firstName;
    @NotNull
    private String surname;
    private String secondName;
    private String secondSurname;
    private String fullName;

    @Override
    public Customer asEntity() {
        Customer customer = new Customer();
        customer.setId(this.getId());
        customer.setIdentity(this.getIdentity());
        customer.setFirstName(this.getFirstName());
        customer.setSurname(this.getSurname());
        customer.setSecondName(this.getSecondName());
        customer.setSecondSurname(this.getSecondSurname());
        customer.setFullName(this.getFullName());
        return customer;
    }

}
