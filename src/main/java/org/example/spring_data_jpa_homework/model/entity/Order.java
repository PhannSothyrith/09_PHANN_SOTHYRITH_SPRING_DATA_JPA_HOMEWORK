package org.example.spring_data_jpa_homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_data_jpa_homework.model.enums.Status;
import org.example.spring_data_jpa_homework.model.response.OrderResponse;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ProductOrder> productOrder;

    public OrderResponse toResponse() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductOrder productOrder : productOrder) {
            productResponses.add(productOrder.getProduct().toResponse());
        }
        return new OrderResponse(this.id, this.orderDate, this.totalAmount, this.status, productResponses);
    }


}
