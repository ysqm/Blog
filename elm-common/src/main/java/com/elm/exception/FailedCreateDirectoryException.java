package com.elm.exception;

public class FailedCreateDirectoryException extends BaseException {
    private static final long serialVersionUID = 1L;
    public FailedCreateDirectoryException() {
    }
    public FailedCreateDirectoryException(String message) {
        super(message);
    }
}
