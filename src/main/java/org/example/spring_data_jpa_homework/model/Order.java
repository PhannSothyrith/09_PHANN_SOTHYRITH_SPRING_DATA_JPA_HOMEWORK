package org.example.spring_data_jpa_homework.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    @Column(precision = 10 , scale = 2)
    private BigDecimal totalAmount;
    private String status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ProductOrder>productOrder;


}
