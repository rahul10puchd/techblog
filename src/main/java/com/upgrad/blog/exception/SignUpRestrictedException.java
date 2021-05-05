package com.upgrad.blog.exception;

public class SignUpRestrictedException extends Exception {

    public SignUpRestrictedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignUpRestrictedException(String message) {
        super(message);
    }
}
