package com.example.person.handler;


public class CouldNotFindPersonException extends RuntimeException {

    public CouldNotFindPersonException(String message) {
        super(message);
    }
}
