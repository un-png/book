package com.cqgc.exception;

public class InsufficientPermissionsException extends BaseException{
    public InsufficientPermissionsException() {
    }
    public InsufficientPermissionsException(String msg) {
        super(msg);
    }
}
