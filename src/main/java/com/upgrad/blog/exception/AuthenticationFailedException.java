package com.upgrad.blog.exception;

public class AuthenticationFailedException extends Exception {

    public AuthenticationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
