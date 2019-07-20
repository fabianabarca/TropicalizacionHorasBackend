package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class CustomResponse {

    private String messages;
    private String errorMessages;
    private Object response;

    public CustomResponse(Object response) {
        this.response = response;
    }

    public CustomResponse(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public CustomResponse(String messages, String errorMessages) {
        this.messages = messages;
        this.errorMessages = errorMessages;
    }
}