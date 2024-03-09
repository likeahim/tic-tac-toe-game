package com.likeahim.tictactoegame;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for cross wins schemes")

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
            TestHelpingMethods.fillTheRowForTestWith3Rows(board, cross);

            //When
            horizontalScheme = board.isHorizontalScheme(cross);

            //Then
            assertTrue(horizontalScheme);
        }

        @DisplayName("Cross doesn't win horizontal in first row")
        @Test
        void testCrossNoWinHorizontalSecondRow() {
            //Given
            TestHelpingMethods.fillTheRowForTestWith3Rows(board, cross);
            //replace cross in coordinate 0,0 with empty mark
            board.getRows().get(0).getCols().set(0, new EmptyMark());

            //When
            horizontalScheme = board.isHorizontalScheme(cross);

            //Then
            assertFalse(horizontalScheme);
        }

        @Nested
        class TestsForCrossWinsInColumns {
            boolean verticalScheme = false;

            @DisplayName("Cross wins vertical in first column")
            @Test
            void testCrossWinsVerticalFirstColumn() {
                //Given
                TestHelpingMethods.fillTheColumnForTestWith3Columns(board, 0, cross);

                //When
                verticalScheme = board.isVerticalScheme(cross);

                //Then
                assertTrue(verticalScheme);
            }

            @DisplayName("Cross doesn't win vertical in second column")
            @Test
            void testCrossNoWinVerticalSecondColumn() {
                //Given
                TestHelpingMethods.fillTheColumnForTestWith3Columns(board, 1, cross);
                //replace cross in coordinate 0,1 with empty mark
                board.getRows().get(0).getCols().set(1, new EmptyMark());

                //When
                verticalScheme = board.isVerticalScheme(cross);

                //Then
                assertFalse(verticalScheme);
            }
        }

        @Nested
        class TestsForCrossWinsDiagonal {
            boolean diagonalScheme = false;

            @DisplayName("Cross wins diagonal from top left to down right")
            @Test
            void testCrossWinsDiagonalTopLeftDownRight() {
                //Given
                TestHelpingMethods.fillDiagonalForTestWith3Rows(board, true, cross);

                //When
                diagonalScheme = board.isDiagonalDownToRight(cross);

                //Then
                assertTrue(diagonalScheme);
            }

            @DisplayName("Cross doesn't win diagonal from top left to down right")
            @Test
            void testCrossNoWinDiagonalTopLeftDownRight() {
                //Given
                TestHelpingMethods.fillDiagonalForTestWith3Rows(board, true, cross);
                //replace cross in coordinate 0,0 with empty mark
                board.getRows().get(0).getCols().set(0, new EmptyMark());

                //When
                diagonalScheme = board.isDiagonalDownToRight(cross);

                //Then
                assertFalse(diagonalScheme);
            }

            @DisplayName("Cross wins diagonal from top right to left down")
            @Test
            void testCrossWinsDiagonalTopRightLeftDown() {
                //Given
                TestHelpingMethods.fillDiagonalForTestWith3Rows(board, false, cross);

                //When
                diagonalScheme = board.isDiagonalLeftDown(cross);

                //Then
                assertTrue(diagonalScheme);
            }

            @DisplayName("Cross doesn't win diagonal from top right to left down")
            @Test
            void testCrossNoWinDiagonalTopRightLeftDown() {
                //Given
                TestHelpingMethods.fillDiagonalForTestWith3Rows(board, false, cross);
                //replace cross in coordinate 0,2 with empty mark
                board.getRows().get(0).getCols().set(2, new EmptyMark());

                //When
                diagonalScheme = board.isDiagonalLeftDown(cross);

                //Then
                assertFalse(diagonalScheme);

            }
        }
    }
}
