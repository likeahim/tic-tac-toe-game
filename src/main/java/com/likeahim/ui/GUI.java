package com.likeahim.ui;

import com.likeahim.display.Board;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Computer;
import com.likeahim.logic.players.Player;
import com.likeahim.logic.players.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.event.MenuKeyListener;

public class GUI {


    public static void setStage(Stage stage) {
        Board board = new Board();
        if (!Board.getPlayers().isEmpty())
            Board.getPlayers().removeAll(Board.getPlayers());
        CheckBox checkBox = new CheckBox("Game with computer");
        //create and set boardGrid
        GridPane boardGrid = new GridPane();
        drawLines(boardGrid);

        //submit names of players
        final TextField name = new TextField();
        name.setPromptText("Enter name");
        name.setPrefColumnCount(5);
        name.getText();
        name.setPadding(new Insets(15, 15, 15, 15));
        boardGrid.add(name, 0, 5);
        boardGrid.add(ButtonControl.getSubmitName(), 0, 6);
        ButtonControl.getSubmitName().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setPlayersName(board, name, checkBox);
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setMinSize(98, 98);
                boardGrid.add(button, i, j); // Adds the button to the grid

                button.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-font-size:30");

                try {
                    String finalTurn = board.getPlayerWithMove().getMark().toString();
                    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            button.setText(finalTurn);
                        }
                    });
                    Integer rowIndex = GridPane.getRowIndex(button);
                    Integer columnIndex = GridPane.getColumnIndex(button);
                    Marker withMove = board.getPlayerWithMove().getMark();
                    board.getRows().get(rowIndex).getCols().set(columnIndex, withMove);
                    if (board.checkWinScheme(board.getPlayerWithMove())) {
                        GUI.setStage(stage);
                    }
                    board.changePlayerWithMove();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        //create and set menuGrid
        GridPane menuGrid = new GridPane();
        menuGrid.setPadding(new Insets(10, 10, 10, 10));
        menuGrid.setVgap(5);
        VBox buttons = new VBox();
        buttons.getChildren().addAll(ButtonControl.getNewGame(), ButtonControl.getSaveGame(), ButtonControl.getClose());
        menuGrid.add(buttons, 2, 0);
        menuGrid.add(checkBox, 2, 1);


        //create and set infoGrid
        GridPane infoGrid = new GridPane();
        Text text = createPlayersInfoField(board);
        infoGrid.add(text, 0, 2);
        infoGrid.setPadding(new Insets(10, 10, 10, 10));
        infoGrid.setGridLinesVisible(true);


        GridPane.setConstraints(boardGrid, 0, 0);
        GridPane.setConstraints(menuGrid, 1300, 0);
        GridPane.setConstraints(infoGrid, 0, 400);

        GridPane mainGrid = new GridPane();
        mainGrid.getChildren().addAll(boardGrid, menuGrid, infoGrid);


        Scene scene = new Scene(mainGrid, 600, 500);
        scene.setFill(Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Basic Tic Tac Toe Board");
        stage.setResizable(false);
        stage.show();
    }

    private static Text createPlayersInfoField(Board board) {
        Text result = new Text(" ");
        if (Board.getPlayers().size() < 2)
            return result;
        else {
            String name1 = Board.getPlayers().get(0).getName();
            String name2 = Board.getPlayers().get(1).getName();
            result.setText(name1 + " vs " + name2);
        }

        return result;
    }

    private static String changeMoveSigns(Board board) {
        if (!Board.getPlayers().isEmpty()) {
            String firstPlayerMark = Board.getPlayers().get(0).getMark().toString();
            String secondPlayerMark = Board.getPlayers().get(1).getMark().toString();
            String markWithMove = board.getPlayerWithMove().getMark().toString();
            return markWithMove.equals(firstPlayerMark) ? secondPlayerMark : firstPlayerMark;
        } else
            return new EmptyMark().toString();
    }

    private static void setPlayersName(Board board, TextField name, CheckBox checkBox) {

                if (board.getPlayerWithMove() == null) {
                    User user1 = new User(name.getText(), new Cross());
                    board.setPlayerWithMove(user1);
                    Board.getPlayers().add(user1);
                    name.clear();
                } else {
                    if (checkBox.isSelected()) {
                        Computer playerTwo = new Computer(new Nought());
                        Board.getPlayers().add(playerTwo);
                    } else {
                        User playerTwo = new User(name.getText(), new Nought());
                        Board.getPlayers().add(playerTwo);
                    }
                    name.clear();
                }
            }


    private static void showWinner(GridPane grid, Player playerWithMove) {
        Text winner = new Text(playerWithMove.toString() + " wins this round");
        grid.add(winner, 300, 300);
    }

    private static void drawLines(GridPane gridPane) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Line line1 = new Line();
                line1.setStartX(50.0);
                line1.setStartY(0.0);
                line1.setEndX(50.0);
                line1.setEndY(100.0);

                Line line2 = new Line();
                line2.setStartX(0.0);
                line2.setStartY(50.0);
                line2.setEndX(100.0);
                line2.setEndY(50.0);

                GridPane grid = new GridPane();
                grid.add(line1, 1, 0);
                grid.add(line2, 0, 1);

                gridPane.add(grid, j, i);
            }
        }
    }
}
