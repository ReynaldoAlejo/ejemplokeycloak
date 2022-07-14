package com.tutorial.keycloakbackend.dto;



/**
 * @author reynaldo
 * @Date 9/07/2022
 */


public class ResponseMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String message) {
        this.message = message;
    }
}
