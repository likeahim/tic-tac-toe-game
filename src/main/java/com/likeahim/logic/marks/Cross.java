package com.likeahim.logic.marks;

import java.util.Objects;

public class Cross implements Marker {

    private static final String name = "X";
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
        Marker cross = (Cross) obj;
        return Objects.equals(name, getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}