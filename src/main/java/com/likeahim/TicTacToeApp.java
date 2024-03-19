package com.likeahim;

import com.likeahim.display.Board;
import com.likeahim.logic.control.Game;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.Marker;
import com.likeahim.ui.ButtonControl;
import com.likeahim.ui.GUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

//to launch game with GUI extends main class with Application and uncomment methods launch and start
public class TicTacToeApp extends Application {
    public static void main(String[] args) {
//        Game game = new Game(true);
//        game.startGame();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
            GUI.setStage(stage);
        ButtonControl.controlGame(stage);
        }
    }




