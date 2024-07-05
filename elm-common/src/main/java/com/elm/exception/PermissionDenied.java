package com.elm.exception;

public class PermissionDenied extends BaseException{
    public PermissionDenied(){}
    public PermissionDenied(String message){
        super(message);
    }
}
