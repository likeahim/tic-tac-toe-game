package com.likeahim.logic.players;

import com.likeahim.logic.marks.Marker;

import java.util.Objects;

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
    public void setName(String name) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return wins == computer.wins && Objects.equals(name, computer.name) && Objects.equals(mark, computer.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mark, wins);
    }

    @Override
    public String toString() {
        return name;
    }
}