package com.contaazul.marsExploration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("Invalid command.");
    }
}
