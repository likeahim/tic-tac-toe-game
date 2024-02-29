package com.likeahim.display;

import com.likeahim.logic.control.Move;
import com.likeahim.logic.exceptions.IncorrectMoveException;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.players.Player;
import com.likeahim.ui.UserInput;

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

    public List<BoardRow> getRows() {
        return rows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public Player getRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(Player roundWinner) {
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

    public static void setPlayers(List<Player> players) {
        Board.players = players;
    }

    @Override
    public String toString() {
        String board = "";

        for (int row= 0; row < numberOfRows; row++) {
            board += rows.get(row).toString();
        }
        return board;
    }

    public String toSerialData() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < rows.size(); row++)
            for (int col = 0; col < rows.get(row).getCols().size(); col++)
                s.append(rows.get(row).getCols().get(col).toString());
        return s.toString();
    }
}
