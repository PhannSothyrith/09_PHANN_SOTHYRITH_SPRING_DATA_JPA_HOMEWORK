package org.example.spring_data_jpa_homework.service.serviceImpl;

import org.example.spring_data_jpa_homework.model.entity.Customer;
import org.example.spring_data_jpa_homework.model.entity.Email;
import org.example.spring_data_jpa_homework.model.request.CustomerRequest;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;
import org.example.spring_data_jpa_homework.repository.CustomerRepository;
import org.example.spring_data_jpa_homework.repository.EmailRepository;
import org.example.spring_data_jpa_homework.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, EmailRepository emailRepository) {
        this.customerRepository = customerRepository;
        this.emailRepository = emailRepository;
    }

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        Email email = emailRepository.save(customerRequest.toEntity(customerRequest.getEmail()));
        return customerRepository.save(customerRequest.toCusEntity(email));

    }

    @Override
    public List<CustomerResponse> getAllCustomer(int page,int size,String sortBy,String direction) {
        Sort sort = direction.equalsIgnoreCase
                (Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page-1 , size ,sort);
        Page <Customer> customers = customerRepository.findAll(pageable);

        return customers.getContent().stream().map(Customer::toResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponse updateCustomerById(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhoneNumber(customer.getPhoneNumber());
        Email email = customer.getEmail();
        email.setEmail(customerRequest.getEmail());
        Customer customer1 = customerRepository.save(customer);
        return customer1.toResponse();


    }
}
