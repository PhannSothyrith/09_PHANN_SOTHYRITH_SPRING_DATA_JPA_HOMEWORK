package org.example.spring_data_jpa_homework.exception;

public class 
CustomNotfoundException extends RuntimeException{
    public CustomNotfoundException(String message) {
        super(message);
    }
}
