package com.be1.plant4you.exception;

public class NoPrevRequestException extends BoardException {

    public NoPrevRequestException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}