package com.yehorpolishchuk.periodicals.exceptions;

public class ServerException extends Exception {
    public ServerException(String message) {
        super(message);
    }

    public ServerException() {
        super();
    }
}
