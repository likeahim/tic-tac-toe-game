package com.likeahim.display;

import com.likeahim.logic.control.Move;
import com.likeahim.logic.exceptions.IncorrectMoveException;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int numberOfRows = 3;
    private List<BoardRow> rows = new ArrayList<>();
    private Player roundWinner; //board
    private Player gameWinner; //board
    private Player playerWithMove; //board
    private static List<Player> players = new ArrayList<>();


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
        int diagonal = 0;
        if (isHorizontalScheme(mark)) return true;
        if (isVerticalScheme(mark)) return true;
        if (isDiagonalDownToRight(mark, diagonal)) return true;
        if (isDiagonalLeftDown(mark, diagonal)) return true;

        return false;
    }

    private boolean isDiagonalLeftDown(Marker mark, int diagonal) {
        int col = 2;
        for (int row = 0; row < numberOfRows; row++) {
            Marker marker = rows.get(row).getCols().get(col - row);
            if (mark.equals(marker))
                diagonal++;
        }
        return diagonal == 3;
    }

    private boolean isDiagonalDownToRight(Marker mark, int diagonal) {
        for (int row = 0; row < numberOfRows; row++) {
            Marker marker = rows.get(row).getCols().get(row);
            if (mark.equals(marker))
                diagonal++;
        }
        return diagonal == 3;
    }

    private boolean isVerticalScheme(Marker mark) {
        for (int col = 0; col < numberOfRows; col++) {
            int counter = 0;
            for (int row = 0; row < numberOfRows; row++) {
                Marker marker = rows.get(row).getCols().get(col);
                if (mark.equals(marker))
                    counter++;
            }
            if (counter == 3)
                return true;
        }
        return false;
    }

    private boolean isHorizontalScheme(Marker mark) {
        for (int row = 0; row < numberOfRows; row++) {
            long count = rows.get(row).getCols().stream()
                    .filter(marker -> marker.equals(mark))
                    .count();
            if (count == 3)
                return true;
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
}
