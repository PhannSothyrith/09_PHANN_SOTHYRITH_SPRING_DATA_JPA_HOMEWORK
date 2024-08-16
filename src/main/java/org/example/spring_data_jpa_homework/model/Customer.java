package org.example.spring_data_jpa_homework.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;
import org.example.spring_data_jpa_homework.model.response.EmailResponse;

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
    @OneToMany (mappedBy = "customer")
    private List<Order> order;

    public CustomerResponse toResponse() {
        EmailResponse emailResponse = email.toResponse();
        return new CustomerResponse(this.id, this.customerName, this.address, this.phoneNumber,emailResponse);
    }

}
