package com.elm.exception;

import com.elm.context.BaseContext;

public class FileNowFoundException extends BaseException {
    private static final long serialVersionUID = 1L;
    public FileNowFoundException() {

    }
    public FileNowFoundException(String message) {
        super(message);
    }
}
