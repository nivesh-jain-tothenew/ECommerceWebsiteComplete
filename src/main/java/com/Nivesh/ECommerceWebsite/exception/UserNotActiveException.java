package com.Nivesh.ECommerceWebsite.exception;

public class UserNotActiveException extends RuntimeException {

    String message;
    public UserNotActiveException(String s) {
        this.message=s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
