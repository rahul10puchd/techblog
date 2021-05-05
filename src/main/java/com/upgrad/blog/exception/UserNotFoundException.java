package com.upgrad.blog.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
