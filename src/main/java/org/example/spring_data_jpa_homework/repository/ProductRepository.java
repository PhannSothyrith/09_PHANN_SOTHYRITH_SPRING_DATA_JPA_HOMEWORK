package org.example.spring_data_jpa_homework.repository;

import org.example.spring_data_jpa_homework.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
}
