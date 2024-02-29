package com.likeahim.logic.marks;

public class Nought implements Marker {

    private static final String name = "o";

    @Override
    public String toString() {
        return name;
    }

    public static String getName() {
        return name;
    }
}
