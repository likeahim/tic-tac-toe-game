package com.likeahim.display;

import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {
    private final int numberOfCols = 3;
    private final List<Marker> cols = new ArrayList<>();

    public List<Marker> getCols() {
        return cols;
    }

    public BoardRow() {
        for (int col = 0; col < numberOfCols; col++) {
            cols.add(new EmptyMark());
        }
    }

    @Override
    public String toString() {
        String boardRow = "|";
        for (int col = 0; col < numberOfCols; col++) {
            boardRow += cols.get(col) + "|";
        }
        boardRow += "\n";
        return boardRow;
    }
}