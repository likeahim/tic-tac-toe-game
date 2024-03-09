package com.likeahim.tictactoegame;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;

public class TestHelpingMethods {
    public static void fillDiagonalForTestWith3Rows(Board board, boolean isFromLeft, Marker mark) {
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

    public static void fillEverySecondFieldWithMark(Board board, Marker mark) {
        int counter = 0;
        Marker secondMark = (mark instanceof Cross) ? new Nought() : new Cross();
        int size = board.getRows().size();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (counter % 2 == 0)
                    board.getRows().get(row).getCols().set(col, mark);
                else
                    board.getRows().get(row).getCols().set(col, secondMark);
                counter++;
            }
        }
        //replace cross in coordinate 2,0 with nought
        board.getRows().get(2).getCols().set(0, secondMark);
        //replace nought in coordinate 2,1 with cross
        board.getRows().get(2).getCols().set(1, mark);
        //replace cross in coordinate 2,2 with nought
        board.getRows().get(2).getCols().set(2, secondMark);
    }
}
