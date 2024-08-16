package org.example.spring_data_jpa_homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_data_jpa_homework.model.entity.Customer;
import org.example.spring_data_jpa_homework.model.response.EmailResponse;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true)
    private String email;
    @OneToOne(mappedBy = "email")
    Customer customer;

    public EmailResponse toResponse(){
        return new EmailResponse(this.id , this.email);
    }

}
