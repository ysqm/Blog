package com.elm.exception;

public class FileReadException extends BaseException{
    private static final long serialVersionUID = 1L;
    public FileReadException() {

    }
    public FileReadException(String message) {
        super(message);
    }
}
