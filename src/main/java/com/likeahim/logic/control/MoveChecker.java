package com.likeahim.logic.control;

import com.likeahim.display.Board;
import com.likeahim.logic.exceptions.IncorrectMoveException;
import com.likeahim.logic.marks.EmptyMark;

public class MoveChecker {

    public static boolean checkMove(Board board, Move move) throws IncorrectMoveException {
        if (board.getRows().get(move.getRow()).getCols().get(move.getCol()) instanceof EmptyMark)
            return true;
        else
            throw new IncorrectMoveException();
    }
}