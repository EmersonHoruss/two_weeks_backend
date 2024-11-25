package com.two_weeks_backend.two_weeks_backend.DTOs.entities.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class OperationCreateDTO extends BaseCreateDTO<Operation> {
    @NotNull
    private String type;
    @NotNull
    @Min(1)
    private short amount;
    @NotNull
    private String date;
    @NotNull
    private Long product;

    @Override
    public Operation asEntity() {
        Operation operation = new Operation();
        operation.setType(this.getType());
        operation.setAmount(this.getAmount());
        operation.setDate(this.getDate());
        operation.setProduct(this.getProductEntity());
        return operation;
    }

    private Product getProductEntity(){
        if(this.getProduct()!=null){
            Product product = new Product();
            product.setId(this.getProduct());
            return product;
        }
        return null;
    }
}
