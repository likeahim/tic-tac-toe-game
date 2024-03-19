package com.likeahim.logic.exceptions;

public class IncorrectMoveException extends RuntimeException {
    private final String message = "incorrect move, try again";

    @Override
    public String getMessage() {
        return message;
    }
}
