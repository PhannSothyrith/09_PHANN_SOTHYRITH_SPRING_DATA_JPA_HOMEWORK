package org.example.spring_data_jpa_homework.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.spring_data_jpa_homework.model.entity.Customer;
import org.example.spring_data_jpa_homework.model.entity.Email;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerRequest {
    @NotNull
    @NotBlank
    private String CustomerName;
    @NotNull
    @NotBlank
    private String Address;
    @NotNull
    @NotBlank
    private String PhoneNumber;
    @NotNull
    @NotBlank
    @jakarta.validation.constraints.Email
    private String email;

    public Customer toCusEntity(Email email){
        return  new Customer(null,this.CustomerName,this.Address,this.PhoneNumber,email,null);
    }
    public Email toEntity(String email){
        return new Email(null,this.email,null);
    }
    public Customer toCusEntity(Long id , Email email){
        return  new Customer(id,this.CustomerName,this.Address,this.PhoneNumber,email,null);
    }



}
