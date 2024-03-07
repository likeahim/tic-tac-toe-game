package com.likeahim.logic.control;

public enum Sequences {
    THREE(3),
    FIVE(5);
    private final int repetitions;

    Sequences(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getRepetitions() {
        return repetitions;
    }
}
