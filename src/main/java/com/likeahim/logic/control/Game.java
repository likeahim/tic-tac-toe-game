package com.likeahim.logic.control;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.players.User;
import com.likeahim.ui.UserInput;

public class Game {

    private final Board board = new Board();
    private static final UserInput UI = new UserInput();

    public void startGame() {
        int numberOfRounds = UI.putNumberOfRounds();
        String name1 = UI.putName();
        boolean markChoiceCorrect = false;
        User user1 = getUser1(markChoiceCorrect, name1);
        User user2 = getUser2();
        arrangeAQueue(user1);
        while (user1.getWins() < numberOfRounds && user2.getWins() < numberOfRounds) {
            board.cleanRoundData();
            playRound();
            UI.showRoundWinner(board.getRoundWinner());
        }
        board.setGameWinner(board.getRoundWinner());
        UI.showGameWinner(board.getGameWinner(), numberOfRounds);
        UI.endGame();
    }

    private void playRound() {
        boolean correctMove;
        int moveCounter = 0;
        while (board.getRoundWinner() == null && moveCounter < 9) {
            UI.printInfo(board.toString());
            UI.infoMove(board.getPlayerWithMove());
            try {
                Move move = UserInput.makeAMove();
                correctMove = board.checkMove(move);

                if (correctMove) {
                    board.getRows().get(move.getRow()).getCols().set(move.getCol(), board.getPlayerWithMove().getMark());
                    if (board.checkWinScheme(board.getPlayerWithMove())) {
                        System.out.println("WINNNNNNNNNNEEEEEEERRRRRRRR");
                        board.setRoundWinner(board.getPlayerWithMove());
                        UI.printInfo(board.toString());
                    }
                    moveCounter++;
                    board.changePlayerWithMove();
                }
            } catch (Exception e) {
                UI.incorrectMove(e);
            }
        }
    }

    private User getUser2() {
        String name2 = UI.putName();
        Marker mark2 = board.assignMark();
        User user2 = new User(name2, mark2);
        Board.getPlayers().add(user2);
        return user2;
    }

    private User getUser1(boolean markChoiceCorrect, String name1) {
        User user1 = null;
        while (!markChoiceCorrect) {
            try {
                Marker mark1 = UI.markChoice();
                user1 = new User(name1, mark1);
                Board.getPlayers().add(user1);
                markChoiceCorrect = true;
            } catch (Exception e) {
                UI.printInfo(board.toString());
            }
        }
        return user1;
    }

    private void arrangeAQueue(User user1) {
        board.setPlayerWithMove(user1);
    }
}