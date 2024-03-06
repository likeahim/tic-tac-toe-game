package com.likeahim.tictactoegame;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            TestHelpingMethods.fillTheRow(board, 0, 0, nought);

            //When
            horizontalScheme = board.isHorizontalScheme(nought);

            //Then
            assertTrue(horizontalScheme);
        }

        @DisplayName("Nought wins horizontal in second row")
        @Test
        void testNoughtWinsHorizontalSecondRow() {
            //Given
            TestHelpingMethods.fillTheRow(board, 1, 0, nought);

            //When
            horizontalScheme = board.isHorizontalScheme(nought);

            //Then
            assertTrue(horizontalScheme);
        }

        @DisplayName("Nought wins horizontal in third row")
        @Test
        void testNoughtWinsHorizontalThirdRow() {
            //Given
            TestHelpingMethods.fillTheRow(board, 2, 0, nought);

            //When
            horizontalScheme = board.isHorizontalScheme(nought);

            //Then
            assertTrue(horizontalScheme);
        }
    }

    @Nested
    class TestsForNoughtWinsInColumns {
        boolean verticalScheme = false;

        @DisplayName("Nought wins vertical in first column")
        @Test
        void testNoughtWinsVerticalFirstColumn() {
            //Given
            TestHelpingMethods.fillTheColumn(board, 0, 0, nought);

            //When
            verticalScheme = board.isVerticalScheme(nought);

            //Then
            assertTrue(verticalScheme);
        }

        @DisplayName("Nought wins vertical in second column")
        @Test
        void testNoughtWinsVerticalSecondColumn() {
            //Given
            TestHelpingMethods.fillTheColumn(board, 0, 1, nought);

            //When
            verticalScheme = board.isVerticalScheme(nought);

            //Then
            assertTrue(verticalScheme);
        }

        @DisplayName("Nought wins vertical in third column")
        @Test
        void testNoughtWinsVerticalThirdColumn() {
            //Given
            TestHelpingMethods.fillTheColumn(board, 0, 2, nought);

            //When
            verticalScheme = board.isVerticalScheme(nought);

            //Then
            assertTrue(verticalScheme);
        }
    }

    @Nested
    class TestsForNoughtWinsDiagonal {
        boolean diagonalScheme = false;

        @DisplayName("Nought wins diagonal from top left to down right")
        @Test
        void testNoughtWinsDiagonalTopLeftDownRight() {
            //Given
            TestHelpingMethods.fillDiagonal(board, true, nought);

            //When
            diagonalScheme = board.isDiagonalDownToRight(nought);

            //Then
            assertTrue(diagonalScheme);
        }

        @DisplayName("Nought wins diagonal from top right to left down")
        @Test
        void testNoughtWinsDiagonalTopRightLeftDown() {
            //Given
            TestHelpingMethods.fillDiagonal(board, false, nought);

            //When
            diagonalScheme = board.isDiagonalLeftDown(nought);

            //Then
            assertTrue(diagonalScheme);

        }
    }
}
