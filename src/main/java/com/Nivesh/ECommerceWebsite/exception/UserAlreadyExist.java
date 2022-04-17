package com.Nivesh.ECommerceWebsite.exception;

public class UserAlreadyExist extends RuntimeException {
    String message;
    public UserAlreadyExist(String s) {
        this.message=s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
