package com.likeahim.display;

import com.likeahim.logic.control.Move;
import com.likeahim.logic.control.Sequences;
import com.likeahim.logic.exceptions.IncorrectMoveException;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Player;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int numberOfRows = 3;
    private List<BoardRow> rows = new ArrayList<>();
    private Player roundWinner; //board
    private Player gameWinner; //board
    private Player playerWithMove; //board
    private static List<Player> players = new ArrayList<>();
    private final Sequences sequence = Sequences.THREE;


    public Board() {
        for (int row = 0; row < numberOfRows; row++) {
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
        if (isHorizontalScheme(mark)) return true;
        if (isVerticalScheme(mark)) return true;
        if (isDiagonalDownToRight(mark)) return true;
        if (isDiagonalLeftDown(mark)) return true;

        return false;
    }

    public boolean isDiagonalLeftDown(Marker mark) {
        int diagonal = 0;
        int col = numberOfRows - 1;
        for (int row = 0; row < numberOfRows; row++) {
            Marker marker = rows.get(row).getCols().get(col - row);
            if (mark.equals(marker))
                diagonal++;
            else
                diagonal = 0;
            if (diagonal == sequence.getRepetitions())
                return true;
        }
        return false;
    }

    public boolean isDiagonalDownToRight(Marker mark) {
        int diagonal = 0;
        for (int row = 0; row < numberOfRows; row++) {
            Marker marker = rows.get(row).getCols().get(row);
            if (mark.equals(marker))
                diagonal++;
            else
                diagonal = 0;
            if (diagonal == sequence.getRepetitions())
                return true;
        }
        return false;
    }

    public boolean isVerticalScheme(Marker mark) {
        for (int col = 0; col < numberOfRows; col++) {
            int duplicate = 0;
            for (int row = 0; row < numberOfRows; row++) {
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
        int duplicate = 0;
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfRows; col++) {
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

        for (int row = 0; row < numberOfRows; row++) {
            board.append(row).append(" ").append(rows.get(row).toString());
        }
        board.append("   0 1 2\n");
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

    public void makeAMove(Move move) {
        rows.get(move.getRow()).getCols().set(move.getCol(), playerWithMove.getMark());
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfFields() {
        return numberOfRows * numberOfRows;
    }
}
