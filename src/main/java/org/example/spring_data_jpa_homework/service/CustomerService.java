package org.example.spring_data_jpa_homework.service;


import org.example.spring_data_jpa_homework.model.entity.Customer;
import org.example.spring_data_jpa_homework.model.request.CustomerRequest;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomer (int page,int size,String sort,String direction);
    CustomerResponse getCustomerById(Long id);
   void deleteCustomerById(Long id);
   CustomerResponse updateCustomerById(Long id,CustomerRequest customerRequest);
}
