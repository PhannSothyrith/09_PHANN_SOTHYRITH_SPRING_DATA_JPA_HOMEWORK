package org.example.spring_data_jpa_homework.service;

import org.example.spring_data_jpa_homework.model.Customer;
import org.example.spring_data_jpa_homework.model.request.CustomerRequest;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;

public interface CustomerService {
    public CustomerResponse createCustomer(CustomerRequest customerRequest);
}
