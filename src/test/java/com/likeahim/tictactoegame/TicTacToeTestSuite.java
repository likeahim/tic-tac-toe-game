package com.likeahim.tictactoegame;

import com.likeahim.display.Board;
import com.likeahim.logic.control.Move;
import com.likeahim.logic.exceptions.IncorrectMoveException;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Player;
import com.likeahim.logic.players.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Tests of various scenarios in Tic-tac-toe game")

public class TicTacToeTestSuite {

    Board board = new Board();
    Cross cross = new Cross();
    Nought nought = new Nought();
    Player player = new User("Marek", new Cross());

    @DisplayName("Test for draw after filling all places with marks")
    @Test
    void testDrawAfterAllMoves() {
        //Given
        TestHelpingMethods.fillEverySecondFieldWithMarkWith3Rows(board, cross);

        //When
        boolean isWinScheme = board.checkWinScheme(player);

        //Then
        assertFalse(isWinScheme);
    }

    @DisplayName("Throws exception if move is invalid")
    @Test
    void testForThrowingAnException() {
        //Given
        TestHelpingMethods.fillEverySecondFieldWithMarkWith3Rows(board, cross);

        //When
        Move move = new Move(0, 0);

        //Then
        assertThrows(IncorrectMoveException.class, () -> board.checkMove(move));

    }
}
