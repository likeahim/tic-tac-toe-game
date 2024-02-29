package com.likeahim.logic.marks;

public class EmptyMark implements Marker {

    private static final String name = " ";

    @Override
    public String toString() {
        return name;
    }

    public static String getName() {
        return name;
    }
}