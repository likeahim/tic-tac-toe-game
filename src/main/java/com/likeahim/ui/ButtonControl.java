package com.likeahim.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ButtonControl {
    private static Button newGame = new Button("NEW GAME");
    private static Button saveGame = new Button("SAVE GAME");
    private static Button close = new Button("QUIT");
    private static Button submitName = new Button("Submit");

    public static Button getSubmitName() {
        return submitName;
    }

    public static Button getNewGame() {
        newGame.setPadding(new Insets(15, 15, 15, 15));
        return newGame;
    }

    public static Button getSaveGame() {
        return saveGame;
    }

    public static Button getClose() {
        close.setPadding(new Insets(15, 15, 15, 15));
        return close;
    }

    public static void controlGame(Stage stage) {
        close.setOnMouseClicked(e -> stage.close());
        newGame.setOnMouseClicked(e -> {
            GUI.moveCounter.set(0);
            GUI.setStage(stage);
        });
    }
}
