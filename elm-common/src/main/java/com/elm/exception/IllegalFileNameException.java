package com.elm.exception;

public class IllegalFileNameException extends BaseException {
    private static final long serialVersionUID = 1L;
    public IllegalFileNameException() {

    }
    public IllegalFileNameException(String message) {
        super(message);
    }
}
