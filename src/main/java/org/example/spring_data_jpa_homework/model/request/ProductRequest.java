package org.example.spring_data_jpa_homework.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.spring_data_jpa_homework.model.entity.Product;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductRequest {
    @NotNull
    private String productName;
    private BigDecimal unitPrice;
    private String description;
    public Product toEntity(){
        return new Product(null ,this.productName,this.unitPrice,this.description ,null);
    }

}
