package com.task.exeptions;

public class PianoLimitException extends RuntimeException {
    public PianoLimitException(String msg) {
        super(msg);
    }
}
