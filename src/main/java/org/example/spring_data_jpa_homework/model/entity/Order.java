package org.example.spring_data_jpa_homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_data_jpa_homework.model.enums.Status;
import org.example.spring_data_jpa_homework.model.response.OrderResponse;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate orderDate;
    private Float totalAmount;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ProductOrder> productOrder;

    public OrderResponse toResponse() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductOrder productOrder : productOrder) {
            productResponses.add(productOrder.getProduct().toResponse());
        }
        return new OrderResponse(this.id, this.orderDate, this.totalAmount, this.status, productResponses);
    }


}
