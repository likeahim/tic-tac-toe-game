package com.likeahim.logic.exceptions;

public class IncorrectMoveException extends Exception {
    private final String message = "incorrect move, try again";

    @Override
    public String getMessage() {
        return message;
    }
}
