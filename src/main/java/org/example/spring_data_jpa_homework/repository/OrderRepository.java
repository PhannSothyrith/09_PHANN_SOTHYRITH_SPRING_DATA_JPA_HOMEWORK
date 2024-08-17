package org.example.spring_data_jpa_homework.repository;

import org.example.spring_data_jpa_homework.model.entity.Customer;
import org.example.spring_data_jpa_homework.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(Customer customer);
}
