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

    public static void fillTheColumnForTestWith3Columns(Board board, int col, Marker mark) {
        for (int row = 0; row < 3; row ++) {
            board.getRows().get(row).getCols().set(col, mark);
        }
    }

    public static void fillTheRowForTestWith3Rows(Board board, Marker mark) {
        for (int col = 0; col < 3; col++) {
            board.getRows().get(0).getCols().set(col, mark);
        }
    }
}
