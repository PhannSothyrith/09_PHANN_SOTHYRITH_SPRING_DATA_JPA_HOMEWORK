package org.example.spring_data_jpa_homework.repository;

import org.example.spring_data_jpa_homework.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email , Long> {
}
