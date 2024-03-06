package com.likeahim.tictactoegame;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.Marker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrossWinsSchemeTestSuite {
    Board board = new Board();
    Marker cross = new Cross();

    @Nested
    class TestsForCrossWinsInRows {

        boolean horizontalScheme = false;

        @DisplayName("Cross wins horizontal in first row")
        @Test
        void testCrossWinsHorizontalFirstRow() {
            //Given
            TestHelpingMethods.fillTheRow(board, 0, 0, cross);

            //When
            horizontalScheme = board.isHorizontalScheme(cross);

            //Then
            assertTrue(horizontalScheme);
        }

        @DisplayName("Cross wins horizontal in second row")
        @Test
        void testCrossWinsHorizontalSecondRow() {
            //Given
            TestHelpingMethods.fillTheRow(board, 1, 0, cross);

            //When
            horizontalScheme = board.isHorizontalScheme(cross);

            //Then
            assertTrue(horizontalScheme);
        }

        @DisplayName("Cross wins horizontal in third row")
        @Test
        void testCrossWinsHorizontalThirdRow() {
            //Given
            TestHelpingMethods.fillTheRow(board, 2, 0, cross);

            //When
            horizontalScheme = board.isHorizontalScheme(cross);

            //Then
            assertTrue(horizontalScheme);
        }
    }

    @Nested
    class TestsForCrossWinsInColumns {
        boolean verticalScheme = false;

        @DisplayName("Cross wins vertical in first column")
        @Test
        void testCrossWinsVerticalFirstColumn() {
            //Given
            TestHelpingMethods.fillTheColumn(board, 0, 0, cross);

            //When
            verticalScheme = board.isVerticalScheme(cross);

            //Then
            assertTrue(verticalScheme);
        }

        @DisplayName("Cross wins vertical in second column")
        @Test
        void testCrossWinsVerticalSecondColumn() {
            //Given
            TestHelpingMethods.fillTheColumn(board, 0, 1, cross);

            //When
            verticalScheme = board.isVerticalScheme(cross);

            //Then
            assertTrue(verticalScheme);
        }

        @DisplayName("Cross wins vertical in third column")
        @Test
        void testCrossWinsVerticalThirdColumn() {
            //Given
            TestHelpingMethods.fillTheColumn(board, 0, 2, cross);

            //When
            verticalScheme = board.isVerticalScheme(cross);

            //Then
            assertTrue(verticalScheme);
        }
    }

    @Nested
    class TestsForCrossWinsDiagonal {
        boolean diagonalScheme = false;

        @DisplayName("Cross wins diagonal from top left to down right")
        @Test
        void testCrossWinsDiagonalTopLeftDownRight() {
            //Given
            TestHelpingMethods.fillDiagonal(board, true, cross);

            //When
            diagonalScheme = board.isDiagonalDownToRight(cross);

            //Then
            assertTrue(diagonalScheme);
        }

        @DisplayName("Cross wins diagonal from top right to left down")
        @Test
        void testCrossWinsDiagonalTopRightLeftDown() {
            //Given
            TestHelpingMethods.fillDiagonal(board, false, cross);

            //When
            diagonalScheme = board.isDiagonalLeftDown(cross);

            //Then
            assertTrue(diagonalScheme);

        }
    }
}
