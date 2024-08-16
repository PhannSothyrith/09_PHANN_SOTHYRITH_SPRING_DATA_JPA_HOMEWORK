package org.example.spring_data_jpa_homework.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ProductName;
    @Column(precision = 10 , scale = 2)
    private BigDecimal UnitPrice;
    private String Description;
    @OneToMany(mappedBy = "product")
    private List<ProductOrder>productOrder;
}
