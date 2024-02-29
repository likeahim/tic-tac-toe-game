package com.likeahim;

import com.likeahim.display.Board;
import com.likeahim.logic.control.Game;
import com.likeahim.ui.UserInput;

public class TicTacToeApp {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
