package com.likeahim.logic.players;

import com.likeahim.logic.marks.Marker;

public class Computer implements Player {
    private final String name = "Computer";
    private Marker mark;
    private int wins;

    public Computer(Marker mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    @Override
    public Marker getMark() {
        return mark;
    }

    @Override
    public void setName(String name) {

    }
}