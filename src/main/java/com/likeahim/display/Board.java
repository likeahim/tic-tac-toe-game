package com.likeahim.display;

import com.likeahim.logic.control.Move;
import com.likeahim.logic.control.Sequences;
import com.likeahim.logic.exceptions.IncorrectMoveException;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static int COL;
    private static int NUMBER_OF_ROWS;
    public int numberOfRoundsToWin;
    private final List<BoardRow> rows = new ArrayList<>();
    private Player roundWinner; //board
    private Player gameWinner; //board
    private Player playerWithMove; //board
    private static List<Player> players = new ArrayList<>();
    private final Sequences sequence = NUMBER_OF_ROWS == 3 ? Sequences.THREE : Sequences.FIVE;


    public Board() {
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            rows.add(new BoardRow());
        }
    }

    public boolean checkMove(Move move) throws IncorrectMoveException {
        if (rows.get(move.getRow()).getCols().get(move.getCol()) instanceof EmptyMark)
            return true;
        else
            throw new IncorrectMoveException();
    }

    public boolean checkWinScheme(Player player) {
        Marker mark = player.getMark();
        //validate diagonal according to number of rows
        if (sequence.getRepetitions() == 3) {
            if (isDiagonalLeftDown(mark)) return true;
            if (isDiagonalDownToRight(mark)) return true;
        } else {
            if (isBigBoardDiagonalLeftDown1(mark)) return true;
            if (isBigBoardDiagonalRightDown1(mark)) return true;
        }
        if (isVerticalScheme(mark)) return true;
        if (isHorizontalScheme(mark)) return true;

        return false;
    }

    public boolean isDiagonalLeftDown(Marker mark) {
        int col = NUMBER_OF_ROWS - 1;
        int diagonal = 0;
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            Marker marker = rows.get(row).getCols().get(col - row);
            if (mark.equals(marker))
                diagonal++;
            else
                return false;
        }
        return diagonal == sequence.getRepetitions();
    }

    public boolean isBigBoardDiagonalLeftDown1(Marker mark) {
        int row = 4;
        while (row < NUMBER_OF_ROWS) {
            int tempRow = row;
            int diagonal = 0;
            for (int col = 0; col <= row; col++) {
                Marker tempMark = rows.get(tempRow).getCols().get(col);
                if (mark.equals(tempMark))
                    diagonal++;
                else
                    diagonal = 0;
                tempRow--;
                if (diagonal == sequence.getRepetitions())
                    return true;
            }

            row++;
        }
        return isBigBoardDiagonalLeftDown2(mark);
    }

    public boolean isBigBoardDiagonalLeftDown2(Marker mark) {
        int row = 5;
        while (row > 0) {
            int tempRow = row;
            int diagonal = 0;
            for (int col = 9; col >= row; col--) {
                Marker tempMarker = rows.get(tempRow).getCols().get(col);
                if (mark.equals(tempMarker))
                    diagonal++;
                else
                    diagonal = 0;
                tempRow++;
                if (diagonal == sequence.getRepetitions())
                    return true;
            }
            row--;
        }
        return false;
    }

    public boolean isDiagonalDownToRight(Marker mark) {
        int diagonal = 0;
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            Marker marker = rows.get(row).getCols().get(row);
            if (mark.equals(marker))
                diagonal++;
            else
                return false;
        }
        return diagonal == sequence.getRepetitions();
    }

    public boolean isBigBoardDiagonalRightDown1(Marker mark) {
        int row = 0;
        while (row < 6) {
            int tempRow = row;
            int diagonal = 0;
            for (int col = 0; tempRow < NUMBER_OF_ROWS; col++) {
                Marker marker = rows.get(tempRow).getCols().get(col);
                if (mark.equals(marker))
                    diagonal++;
                else
                    diagonal = 0;
                if (diagonal == sequence.getRepetitions())
                    return true;
                tempRow++;
            }
            row++;
        }
        return isBigBoardDiagonalRightDown2(mark);
    }

    private boolean isBigBoardDiagonalRightDown2(Marker mark) {
        int row = 8;
        while (row > 3) {
            int tempRow = row;
            int diagonal = 0;
            for (int col = 9; tempRow >= 0; col--) {
                Marker marker = rows.get(tempRow).getCols().get(col);
                if (mark.equals(marker))
                    diagonal++;
                else
                    diagonal = 0;
                if (diagonal == sequence.getRepetitions())
                    return true;
                tempRow--;
            }
            row--;
        }
        return false;
    }

    public boolean isVerticalScheme(Marker mark) {
        for (int col = 0; col < NUMBER_OF_ROWS; col++) {
            int duplicate = 0;
            for (int row = 0; row < NUMBER_OF_ROWS; row++) {
                Marker nextMarker = rows.get(row).getCols().get(col);
                if (mark.equals(nextMarker)) {
                    duplicate++;
                } else {
                    duplicate = 0;
                }
                if (duplicate == sequence.getRepetitions())
                    return true;
            }
        }
        return false;
    }

    public boolean isHorizontalScheme(Marker mark) {
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            int duplicate = 0;
            for (int col = 0; col < NUMBER_OF_ROWS; col++) {
                Marker nextMarker = rows.get(row).getCols().get(col);
                if (mark.equals(nextMarker)) {
                    duplicate++;
                } else {
                    duplicate = 0;
                }
                if (duplicate == sequence.getRepetitions())
                    return true;
            }
        }
        return false;
    }

    public Marker assignMark() {
        if (players.get(0).getMark() instanceof Cross)
            return new Nought();
        else
            return new Cross();
    }

    public void changePlayerWithMove() {
        playerWithMove = playerWithMove.equals(players.get(0)) ? players.get(1) : players.get(0);
    }

    public List<BoardRow> getRows() {
        return rows;
    }

    public Player getRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(Player roundWinner) {
        roundWinner.setWins(1);
        this.roundWinner = roundWinner;
    }

    public Player getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(Player gameWinner) {
        this.gameWinner = gameWinner;
    }

    public Player getPlayerWithMove() {
        return playerWithMove;
    }

    public void setPlayerWithMove(Player playerWithMove) {
        this.playerWithMove = playerWithMove;
    }

    public static List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();

        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            board.append(row).append(" ").append(rows.get(row).toString());
        }
        board.append("  ");
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            board.append(" ").append(row);
        }
        board.append('\n');
        return board.toString();
    }

    public void cleanRoundData() {
        for (int row = 0; row < rows.size(); row++) {
            for (int col = 0; col < rows.get(row).getCols().size(); col++)
                rows.get(row).getCols().set(col, new EmptyMark());
        }
        roundWinner = null;
    }

    public String toSerialData() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < rows.size(); row++)
            for (int col = 0; col < rows.get(row).getCols().size(); col++)
                s.append(rows.get(row).getCols().get(col).toString());
        return s.toString();
    }

    public void saveGameToFile(String fileName, String board) throws IOException {
        PrintWriter streamOut = new PrintWriter(new FileWriter(fileName));
        streamOut.write(board);
        streamOut.close();
    }

    public String fromSerialData(File savedGame) {

        return "";
    }

    public void makeAMove(Move move) {
        rows.get(move.getRow()).getCols().set(move.getCol(), playerWithMove.getMark());
    }

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getCOL() {
        return COL;
    }

    public static void setCOL(int COL) {
        Board.COL = COL;
    }

    public static void setNumberOfRows(int numberOfRows) {
        NUMBER_OF_ROWS = numberOfRows;
    }

    public int getNumberOfFields() {
        return NUMBER_OF_ROWS * NUMBER_OF_ROWS;
    }

    public int getNumberOfRoundsToWin() {
        return numberOfRoundsToWin;
    }

    public void setNumberOfRoundsToWin(int numberOfRoundsToWin) {
        this.numberOfRoundsToWin = numberOfRoundsToWin;
    }
}
