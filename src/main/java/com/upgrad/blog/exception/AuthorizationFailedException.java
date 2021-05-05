package com.upgrad.blog.exception;

public class AuthorizationFailedException extends Exception {

    public AuthorizationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationFailedException(String message) {
        super(message);
    }
}
