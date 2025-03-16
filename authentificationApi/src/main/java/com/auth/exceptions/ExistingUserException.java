package com.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExistingUserException  extends RuntimeException {

    public ExistingUserException() {
        super("User already exists");
    }
}
