package com.likeahim.logic.marks;

import java.util.Objects;

public class Nought implements Marker {

    private static final String name = "O";

    @Override
    public String toString() {
        return name;
    }

    public static String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Marker nought = (Nought) obj;
        return Objects.equals(name, getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
