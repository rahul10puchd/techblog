package com.upgrad.blog.exception;

public class InvalidPostException extends Exception {

    public InvalidPostException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public InvalidPostException(String msg) {
        super(msg);
    }
}
