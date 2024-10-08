package org.example.spring_data_jpa_homework.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;
import org.example.spring_data_jpa_homework.model.response.EmailResponse;
import org.example.spring_data_jpa_homework.model.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String address;
    private String phoneNumber;
    @OneToOne
    private Email email;
    @OneToMany (mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> order;

    public Customer(Long id, String customerName, String address, String phoneNumber, Email email) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerResponse toResponse() {
        EmailResponse emailResponse = email.toResponse();
        List<OrderResponse> orderResponse = new ArrayList<OrderResponse>();
        for(Order o : order) {
            orderResponse.add(o.toResponse());
        }
        return new CustomerResponse(this.id, this.customerName, this.address, this.phoneNumber,emailResponse,orderResponse);
    }

}
