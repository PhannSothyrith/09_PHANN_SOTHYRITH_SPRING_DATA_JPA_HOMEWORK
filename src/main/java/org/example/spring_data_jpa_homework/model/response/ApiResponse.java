package org.example.spring_data_jpa_homework.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApiResponse <T> {
    private String message;
    private T payload;
    private Integer statusCode;
    private HttpStatus status;
    private LocalDateTime timestamp;


}