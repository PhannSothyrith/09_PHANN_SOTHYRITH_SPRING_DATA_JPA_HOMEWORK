package org.example.spring_data_jpa_homework.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    private Integer quantity;
    private Long productId;
}
