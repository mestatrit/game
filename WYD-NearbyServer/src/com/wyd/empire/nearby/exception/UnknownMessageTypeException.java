package com.wyd.empire.nearby.exception;
public class UnknownMessageTypeException extends Exception {
    private static final long serialVersionUID = 3326040584380841642L;

    public UnknownMessageTypeException(String msg) {
        super(msg);
    }
}
