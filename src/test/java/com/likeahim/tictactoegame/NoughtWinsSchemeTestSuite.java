package com.likeahim.tictactoegame;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests for noughts wins schemes")
public class NoughtWinsSchemeTestSuite {
    Board board = new Board();
    Marker nought = new Nought();

    @Nested
    class TestsForNoughtWinsInRows {

        boolean horizontalScheme = false;

        @DisplayName("Nought wins horizontal in first row")
        @Test
        void testNoughtWinsHorizontalFirstRow() {
            //Given
            TestHelpingMethods.fillTheRowForTestWith3Rows(board, nought);

            //When
            horizontalScheme = board.isHorizontalScheme(nought);

            //Then
            assertTrue(horizontalScheme);
        }

        @DisplayName("Nought doesn't win horizontal in first row")
        @Test
        void testNoughtWinsHorizontalSecondRow() {
            //Given
            TestHelpingMethods.fillTheRowForTestWith3Rows(board, nought);
            //replace nought in coordinate 0,0 with empty mark
            board.getRows().get(0).getCols().set(0, new EmptyMark());

            //When
            horizontalScheme = board.isHorizontalScheme(nought);

            //Then
            assertFalse(horizontalScheme);
        }
    }

    @Nested
    class TestsForNoughtWinsInColumns {
        boolean verticalScheme = false;

        @DisplayName("Nought wins vertical in first column")
        @Test
        void testNoughtWinsVerticalFirstColumn() {
            //Given
            TestHelpingMethods.fillTheColumnForTestWith3Columns(board, 0, nought);

            //When
            verticalScheme = board.isVerticalScheme(nought);

            //Then
            assertTrue(verticalScheme);
        }

        @DisplayName("Nought doesn't win vertical in second column")
        @Test
        void testNoughtWinsVerticalSecondColumn() {
            //Given
            TestHelpingMethods.fillTheColumnForTestWith3Columns(board, 1, nought);
            //replace nought in coordinate 1,1 with empty mark
            board.getRows().get(1).getCols().set(1, new EmptyMark());

            //When
            verticalScheme = board.isVerticalScheme(nought);

            //Then
            assertFalse(verticalScheme);
        }
    }

    @Nested
    class TestsForNoughtWinsDiagonal {
        boolean diagonalScheme = false;

        @DisplayName("Nought wins diagonal from top left to down right")
        @Test
        void testNoughtWinsDiagonalTopLeftDownRight() {
            //Given
            TestHelpingMethods.fillDiagonalForTestWith3Rows(board, true, nought);

            //When
            diagonalScheme = board.isDiagonalDownToRight(nought);

            //Then
            assertTrue(diagonalScheme);
        }

        @DisplayName("Nought doesn't win diagonal from top left to down right")
        @Test
        void testNoughtNoWinDiagonalTopLeftDownRight() {
            //Given
            TestHelpingMethods.fillDiagonalForTestWith3Rows(board, true, nought);
            //replace nought in coordinate 0,0 with empty mark
            board.getRows().get(0).getCols().set(0, new EmptyMark());

            //When
            diagonalScheme = board.isDiagonalDownToRight(nought);

            //Then
            assertFalse(diagonalScheme);
        }

        @DisplayName("Nought wins diagonal from top right to left down")
        @Test
        void testNoughtWinsDiagonalTopRightLeftDown() {
            //Given
            TestHelpingMethods.fillDiagonalForTestWith3Rows(board, false, nought);

            //When
            diagonalScheme = board.isDiagonalLeftDown(nought);

            //Then
            assertTrue(diagonalScheme);
        }

        @DisplayName("Nought doesn't win diagonal from top right to left down")
        @Test
        void testNoughtNoWinDiagonalTopRightLeftDown() {
            //Given
            TestHelpingMethods.fillDiagonalForTestWith3Rows(board, false, nought);
            //replace nought in coordinate 0,2 with empty mark
            board.getRows().get(0).getCols().set(2, new EmptyMark());

            //When
            diagonalScheme = board.isDiagonalLeftDown(nought);

            //Then
            assertFalse(diagonalScheme);
        }
    }
}
