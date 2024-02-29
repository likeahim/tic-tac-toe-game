package com.likeahim.logic.marks;

public class Cross implements Marker {

    private static final String name = "x";
    @Override
    public String toString() {
        return name;
    }

    public static String getName() {
        return name;
    }
}