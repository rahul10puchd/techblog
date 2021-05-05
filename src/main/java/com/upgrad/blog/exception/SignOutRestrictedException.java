package com.upgrad.blog.exception;

public class SignOutRestrictedException extends Exception {

    public SignOutRestrictedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignOutRestrictedException(String message) {
        super(message);
    }
}
