package com.likeahim.logic.players;

import com.likeahim.logic.marks.Marker;

import java.util.Objects;

public class User implements Player {
    private String name;
    private int wins;
    private Marker mark;

    public User(String name, Marker mark) {
        this.name = name;
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
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return wins == user.wins && Objects.equals(name, user.name) && Objects.equals(mark, user.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wins, mark);
    }

    @Override
    public String toString() {
        return name;
    }
}