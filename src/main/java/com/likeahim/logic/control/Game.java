package com.likeahim.logic.control;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Player;
import com.likeahim.logic.players.User;
import com.likeahim.ui.UserInput;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int numberOfRounds;
    private static final Board BOARD = new Board();
    private static final UserInput UI = new UserInput();

    public void startGame() {
        numberOfRounds = UI.putNumberOfRounds();
        String name1 = UI.putName();
        boolean markChoiceCorrect = false;
        User user1 = getUser1(markChoiceCorrect, name1);
        User user2 = getUser2();
        arrangeAQueue(user1);
        while (user1.getWins() < numberOfRounds || user2.getWins() < numberOfRounds) {
            playRound();
            UI.showRoundWinner(BOARD.getRoundWinner());
        }
        UI.showGameWinner(BOARD.getGameWinner(), numberOfRounds);
    }

    private void playRound() {
        boolean correctMove = false;
        while (BOARD.getRoundWinner() == null && !correctMove) {
            UI.printInfo(BOARD.toString());
            UI.infoMove(BOARD.getPlayerWithMove());
            try {
                Move move = UserInput.makeAMove();
                correctMove = BOARD.checkMove(move);

                if (correctMove) {
                    BOARD.getRows().get(move.getRow()).getCols().set(move.getCol(), BOARD.getPlayerWithMove().getMark());
                    changePlayerWithMove();
                }
            } catch (Exception e) {
                UI.incorrectMove(e);
            }
            correctMove = false;
        }
    }

    private User getUser2() {
        String name2 = UI.putName();
        Marker mark2 = assignMark();
        User user2 = new User(name2, mark2);
        Board.getPlayers().add(user2);
        return user2;
    }

    private static User getUser1(boolean markChoiceCorrect, String name1) {
        User user1 = null;
        while (!markChoiceCorrect) {
            try {
                Marker mark1 = UI.markChoice();
                user1 = new User(name1, mark1);
                Board.getPlayers().add(user1);
                markChoiceCorrect = true;
            } catch (Exception e) {
                UI.printInfo(BOARD.toString());
            }
        }
        return user1;
    }

    private void arrangeAQueue(User user1) {
        BOARD.setPlayerWithMove(user1);
    }

    private Marker assignMark() {
        if (Board.getPlayers().get(0).getMark() instanceof Cross)
            return new Nought();
        else
            return new Cross();
    }

    private void changePlayerWithMove() {
        Player temp = BOARD.getPlayerWithMove().equals(Board.getPlayers().get(0)) ? Board.getPlayers().get(1) : Board.getPlayers().get(0);
        BOARD.setPlayerWithMove(temp);
    }


    public int getNumberOfRounds() {
        return numberOfRounds;
    }

}