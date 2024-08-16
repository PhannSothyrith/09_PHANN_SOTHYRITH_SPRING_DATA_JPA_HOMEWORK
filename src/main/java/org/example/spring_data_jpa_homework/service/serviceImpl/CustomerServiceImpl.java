package org.example.spring_data_jpa_homework.service.serviceImpl;

import org.example.spring_data_jpa_homework.model.Customer;
import org.example.spring_data_jpa_homework.model.Email;
import org.example.spring_data_jpa_homework.model.request.CustomerRequest;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;
import org.example.spring_data_jpa_homework.repository.CustomerRepository;
import org.example.spring_data_jpa_homework.repository.EmailRepository;
import org.example.spring_data_jpa_homework.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, EmailRepository emailRepository) {
        this.customerRepository = customerRepository;
        this.emailRepository = emailRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Email email = emailRepository.save(customerRequest.toEntity(customerRequest.getEmail()));
        return customerRepository.save(customerRequest.toCusEntity(email)).toResponse();

    }
}
