package com.leo.springboogapi.resource;

public class ErrorResource {
    private String message;

    public ErrorResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
