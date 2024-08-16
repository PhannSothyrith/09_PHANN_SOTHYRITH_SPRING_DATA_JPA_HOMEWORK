package org.example.spring_data_jpa_homework.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String Address;
    private String phoneNumber;
    private EmailResponse emailResponses;


}
