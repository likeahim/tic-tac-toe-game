package com.likeahim.logic.control;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GPTMoves {
    public static Move computersMove(Board board, Marker mark) {
        List<Move> allPossibleMoves = new ArrayList<>();
        for (int row = 0; row < board.getNumberOfRows(); row++) {
            for (int col = 0; col < board.getNumberOfRows(); col++) {
                Marker marker = board.getRows().get(row).getCols().get(col);
                if (marker instanceof EmptyMark)
                    allPossibleMoves.add(new Move(row, col));
            }
        }
        Random random = new Random();
        int i = random.nextInt(allPossibleMoves.size());
        return allPossibleMoves.get(i);
    }
}
