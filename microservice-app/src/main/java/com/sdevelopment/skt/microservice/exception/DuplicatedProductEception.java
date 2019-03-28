package com.sdevelopment.skt.microservice.exception;

public class DuplicatedProductEception extends Exception {
    public DuplicatedProductEception() {

    }

    public DuplicatedProductEception(String message) {
        super(message);
    }
}
