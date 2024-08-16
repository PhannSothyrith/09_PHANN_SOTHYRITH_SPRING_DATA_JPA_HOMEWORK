package org.example.spring_data_jpa_homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_data_jpa_homework.model.entity.ProductOrder;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ProductName;
    @Column(precision = 10 , scale = 2)
    private BigDecimal UnitPrice;
    private String Description;
    @OneToMany(mappedBy = "product")
    private List<ProductOrder>productOrder;

    public ProductResponse toResponse() {
        return new ProductResponse(this.id, this.ProductName,this.UnitPrice ,this.Description);

    }
}
