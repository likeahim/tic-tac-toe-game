package com.likeahim.logic.control;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.players.Computer;
import com.likeahim.logic.players.Player;
import com.likeahim.logic.players.User;
import com.likeahim.ui.UserInput;

public class Game {

    boolean gameWithComputer;

    public Game(boolean gameWithComputer) {
        this.gameWithComputer = gameWithComputer;
    }

    private final Board board = new Board();
    private static final UserInput UI = new UserInput();

    public void startGame() {
        int numberOfRoundsToWin = UI.putNumberOfRounds();
        int roundsPlayed = 0;
        String name1 = UI.putName();
        boolean markChoiceCorrect = false;
        User user1 = getUser1(markChoiceCorrect, name1);
        gameWithComputer = UI.isSinglePlayerGame();
        Player user2;
        user2 = getUser2();
        arrangeAQueue(user1);
        while (user1.getWins() < numberOfRoundsToWin && user2.getWins() < numberOfRoundsToWin) {
            board.cleanRoundData();
            playRound();
            UI.showRoundWinner(board.getRoundWinner());
            roundsPlayed++;
        }
        board.setGameWinner(board.getRoundWinner());
        UI.showGameWinner(board.getGameWinner(), roundsPlayed);
        UI.endGame();
    }

    private void playRound() {
        boolean correctMove;
        int moveCounter = 0;
        while (board.getRoundWinner() == null && moveCounter < board.getNumberOfFields()) {
            UI.printInfo(board.toString());
            UI.infoMove(board.getPlayerWithMove());
            try {
                Move move;
                if (!gameWithComputer || !(board.getPlayerWithMove() instanceof Computer))
                    move = UserInput.enterTheMove();
                else
                    move = GPTMoves.computersMove(board, board.getPlayerWithMove().getMark());
                correctMove = board.checkMove(move);

                if (correctMove) {
                    board.makeAMove(move);
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

    private Player getUser2() {
        Player user2;
        Marker mark2 = board.assignMark();
        if (!gameWithComputer) {
            String name2 = UI.putName();
            user2 = new User(name2, mark2);
        } else {
            user2 = new Computer(mark2);
        }
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