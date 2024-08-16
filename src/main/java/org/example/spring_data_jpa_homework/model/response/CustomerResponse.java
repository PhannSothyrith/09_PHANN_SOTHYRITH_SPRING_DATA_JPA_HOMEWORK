package org.example.spring_data_jpa_homework.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private EmailResponse emailResponses;
    private List<OrderResponse>orderResponses;

}
