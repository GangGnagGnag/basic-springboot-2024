package com.jkh9610.backboard.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity isn't found")
public class NotFountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFountException(String message) {
        super(message);    // RuntimeException에서 처리
    }    
}
