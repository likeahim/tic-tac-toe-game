package com.likeahim.tictactoegame;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Marker;

public class TestHelpingMethods {
    public static void fillDiagonal(Board board, boolean isFromLeft, Marker mark) {
        if (isFromLeft) {
            for (int row = 0; row < board.getRows().size(); row++) {
                board.getRows().get(row).getCols().set(row, mark);
            }
        } else {
            int col = 2;
            for (int row = 0; row < board.getRows().size(); row++) {
                board.getRows().get(row).getCols().set(col - row, mark);
            }
        }
    }

    public static void fillTheColumn(Board board, int row, int col, Marker mark) {
        board.getRows().get(row).getCols().set(col, mark);
        board.getRows().get(row + 1).getCols().set(col, mark);
        board.getRows().get(row + 2).getCols().set(col, mark);
    }

    public static void fillTheRow(Board board, int row, int col, Marker mark) {
        board.getRows().get(row).getCols().set(0, mark);
        board.getRows().get(row).getCols().set(col + 1, mark);
        board.getRows().get(row).getCols().set(col + 2, mark);
    }
}
