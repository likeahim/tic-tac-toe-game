package com.likeahim.ui;

import com.likeahim.display.Board;
import com.likeahim.logic.control.GPTMoves;
import com.likeahim.logic.control.Move;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.EmptyMark;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Computer;
import com.likeahim.logic.players.Player;
import com.likeahim.logic.players.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class GUI {

    private static Board board;
    private static final double WIDTH = 800;
    private static final double HEIGHT = 800;
    private static final Insets MAIN_INSETS = new Insets(20, 20, 20, 20);
    private static boolean gameWithComputer = false;
    private static Player playerOne;
    private static Player playerTwo;
    private static Button buttonArray[][];
    protected static AtomicInteger moveCounter = new AtomicInteger();
    private static Label gameplay = new Label();


    public static void setStage(Stage stage) {
        Button goBack = new Button("Back");
        //nodes for startScene
        VBox startLayout = new VBox();
        startLayout.setPadding(MAIN_INSETS);
        startLayout.setSpacing(10);
        startLayout.setAlignment(Pos.CENTER);
        Button newGameSingle = new Button("New single game");
        Button newGameMulti = new Button("New multi game");
        Button aboutGame = new Button("About game");
        Button close = new Button("Exit");
        startLayout.getChildren().addAll(newGameSingle, newGameMulti, aboutGame, close);
        Scene startScene = new Scene(startLayout, WIDTH, HEIGHT);

        //nodes for infoScene
        BorderPane infoPane = new BorderPane();
        infoPane.setPadding(MAIN_INSETS);
        Label infoTitle = new Label("Information about this game");
        infoTitle.setStyle("-fx-font-family: \"Chilanka\"; -fx-font-size: 20; -fx-text-fill: blue;");
        Label description = new Label(INFO);
        description.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 20; -fx-text-fill: darkred; -fx-background-color: grey;");
        description.setWrapText(true);
        infoPane.setTop(infoTitle);
        infoPane.setCenter(description);
        Image image = new Image("photos/tictactoeimg.png");
        ImageView img = new ImageView(image);
        infoPane.setRight(img);
        infoPane.setBottom(goBack);
        Scene infoScene = new Scene(infoPane, WIDTH, HEIGHT);


        //nodes for choiceScene
        VBox choiceLayout = new VBox();
        choiceLayout.setPadding(MAIN_INSETS);
        choiceLayout.setSpacing(20);
        choiceLayout.setAlignment(Pos.CENTER);
        Button choiceBack = new Button("Back");
        ComboBox<Integer> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(3, 10);
        Label choiceInfo = new Label("Choose size of board for for game");
        choiceLayout.getChildren().addAll(choiceInfo, comboBox, choiceBack);
        Scene choiceScene = new Scene(choiceLayout, WIDTH, HEIGHT);

        //nodes for playersScene
        VBox playersLayout = new VBox();
        playersLayout.setAlignment(Pos.CENTER);
        playersLayout.setPadding(MAIN_INSETS);
        playersLayout.setSpacing(15);
        Label playersChoiceInfo = new Label("Enter players names and number of round to win");
        playersChoiceInfo.setWrapText(true);
        TextField playerCross = new TextField();
        playerCross.setPromptText("players name (cross)");
        TextField playerNought = new TextField();
        playerNought.setPromptText("players name (nought");
        TextField numberOfRounds = new TextField();
        numberOfRounds.setPromptText("number of rounds to win");
        Button submit = new Button("Let's play");
        playersLayout.getChildren().addAll(playersChoiceInfo, playerCross, playerNought, numberOfRounds, submit);
        Scene playersScene = new Scene(playersLayout, WIDTH, HEIGHT);

        //nodes for mainScene
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(MAIN_INSETS);
        GridPane boardPane = new GridPane();
        boardPane.setAlignment(Pos.CENTER);
        Button computerMove = new Button("Show computers move");
        computerMove.setPadding(MAIN_INSETS);
        computerMove.setOnAction(e -> makeComputerMove(boardPane));
        mainPane.setTop(gameplay);
        gameplay.setPadding(MAIN_INSETS);
        gameplay.setAlignment(Pos.TOP_CENTER);
        VBox manePaneRightButtons = new VBox(10);
        manePaneRightButtons.getChildren().addAll(ButtonControl.getClose(), ButtonControl.getSaveGame(), computerMove);
        mainPane.setRight(manePaneRightButtons);
        mainPane.setLeft(ButtonControl.getNewGame());
        mainPane.setCenter(boardPane);
        Scene mainScene = new Scene(mainPane, 1200, 1400);

        //mainStage settings
        stage.setTitle("Tic tac toe game");
        stage.setScene(startScene);

        //buttons actions
        aboutGame.setOnAction(e -> stage.setScene(infoScene));
        goBack.setOnAction(e -> stage.setScene(startScene));
        choiceBack.setOnAction(e -> {
            stage.setScene(startScene);
            comboBox.cancelEdit();
        });
        close.setOnAction(e -> stage.close());
        newGameSingle.setOnAction(e -> {
            gameWithComputer = true;
            moveCounter.set(0);
            stage.setScene(choiceScene);
        });
        newGameMulti.setOnAction(e -> {
            gameWithComputer = false;
            moveCounter.set(0);
            stage.setScene(choiceScene);
        });
        comboBox.setOnAction(e -> {
            Board.setNumberOfRows(comboBox.getValue());
            Board.setCOL(comboBox.getValue());
            board = new Board();
            setBoard();
            if (gameWithComputer)
                playerNought.setDisable(true);
            else
                playerNought.setDisable(false);
            stage.setScene(playersScene);
        });
        submit.setOnAction(e -> {
            int givenNrRoundsToPlay = 1;
            Alert emptyField = new Alert(Alert.AlertType.INFORMATION, "default names assigned", ButtonType.OK);
            emptyField.setTitle("Problem with your input");
            try {
                board.setNumberOfRoundsToWin(Integer.parseInt(numberOfRounds.getText()));

                if (playerCross.getText().isEmpty() || (playerNought.getText().isEmpty() && !playerNought.isDisabled())) {
                    playerCross.setText("Player with Cross");
                    playerNought.setText("Player with Nought");
                    emptyField.showAndWait();
                } else if (numberOfRounds.getText().isEmpty()) {
                    board.setNumberOfRoundsToWin(givenNrRoundsToPlay);
                    emptyField.setContentText("default number of round: " + givenNrRoundsToPlay);
                    emptyField.showAndWait();
                }
            } catch (Exception exception) {
                board.setNumberOfRoundsToWin(givenNrRoundsToPlay);
                emptyField.setContentText("default number of round: " + givenNrRoundsToPlay);
                emptyField.showAndWait();
            }
            if (!Board.getPlayers().isEmpty()) {
                Board.getPlayers().removeAll(Board.getPlayers());
            }
            playerOne = new User(playerCross.getText(), new Cross());
            if (!gameWithComputer) {
                playerTwo = new User(playerNought.getText(), new Nought());
            } else {
                playerTwo = new Computer(new Nought());
            }
            board.setPlayerWithMove(playerOne);
            Board.getPlayers().add(playerOne);
            Board.getPlayers().add(playerTwo);
            gameplay.setText("G A M E P L A Y \n" + " move: " + board.getPlayerWithMove() + "\n  rounds to win: " + board.getNumberOfRoundsToWin());
            String alertAboutGame = playerOne + " '" + playerOne.getMark().toString() + "' vs " + playerTwo + " '" + playerTwo.getMark().toString() + "'";
            Alert alertBeforeGame = new Alert(Alert.AlertType.INFORMATION, alertAboutGame, ButtonType.OK);
            alertBeforeGame.showAndWait();
            Label whoPlayInfo = new Label(alertAboutGame);
            if (!gameWithComputer)
                computerMove.setDisable(true);
            else
                computerMove.setDisable(false);
            mainPane.setBottom(whoPlayInfo);
            drawLines(boardPane);
            stage.setScene(mainScene);
        });
        stage.show();
    }

    private static void setBoard() {
        for (int row = 0; row < Board.getNumberOfRows(); row++) {
            for (int col = 0; col < Board.getCOL(); col++) {
                board.getRows().get(row).getCols().set(col, new EmptyMark());
            }
        }
    }

    //draws buttons as board fields and assigns them actions
    private static void drawLines(GridPane boardPane) {
        int boardCols = Board.getCOL();
        int boardRows = Board.getNumberOfRows();
        buttonArray = new Button[boardCols][boardRows];
        for (int col = 0; col < boardCols; col++) {
            for (int row = 0; row < boardRows; row++) {
                Button button = new Button();
                button.setPrefSize((double) 500 / boardCols, (double) 500 / boardRows);
                String buttonStyle = "-fx-font-size: 20; -fx-font-family: Chilanka; -fx-background-color: pink; -fx-border-color: black";
                button.setStyle(buttonStyle);
                button.setOnMouseEntered(me -> button.setStyle("-fx-background-color: grey; -fx-font-size: 20"));
                button.setOnMouseExited(me -> button.setStyle(buttonStyle));
                GridPane.setConstraints(boardPane, col, row);
                boardPane.add(button, col, row);
                buttonArray[col][row] = button;
                int finalRow = row;
                int finalCol = col;
                button.setOnMouseClicked(e -> {
                    Alert falseMove = new Alert(Alert.AlertType.ERROR, "False move, try again", ButtonType.OK);
                    if (playerOne.getWins() < board.getNumberOfRoundsToWin() && playerTwo.getWins() < board.getNumberOfRoundsToWin()) {
                        if (!(board.getPlayerWithMove() instanceof Computer)) {
                            while (true) {
                                Move move = new Move(finalRow, finalCol);
                                try {
                                    if (board.checkMove(move)) {
                                        button.setText(board.getPlayerWithMove().getMark().toString());
                                        board.makeAMove(move);
                                        moveCounter.getAndIncrement();
                                        boolean isWinner = board.checkWinScheme(board.getPlayerWithMove());
                                        checkingWinner(boardPane, isWinner);
                                        board.changePlayerWithMove();
                                        gameplay.setText("G A M E P L A Y \n" + "move: " + board.getPlayerWithMove());
                                        if (moveCounter.get() == board.getNumberOfFields() && !isWinner) {
                                            endRoundWithDraw(boardPane);
                                        }
                                        break;
                                    }
                                } catch (Exception alert) {
                                    falseMove.showAndWait();
                                    break;
                                }
                            }
                        }
                    }
                    board.setGameWinner(board.getRoundWinner());
                });
            }
        }
    }

    private static void makeComputerMove(GridPane boardPane) {
        if (gameWithComputer && board.getPlayerWithMove() instanceof Computer) {
            Move move = GPTMoves.computersMove(board, board.getPlayerWithMove().getMark());
            if (board.checkMove(move)) {
                buttonArray[move.getCol()][move.getRow()].setText(playerTwo.getMark().toString());
                board.makeAMove(move);
                moveCounter.getAndIncrement();
                boolean isWinner = board.checkWinScheme(board.getPlayerWithMove());
                checkingWinner(boardPane, isWinner);
                board.changePlayerWithMove();
                gameplay.setText("G A M E P L A Y \n" + "move: " + board.getPlayerWithMove());
                if (moveCounter.get() == board.getNumberOfFields() && !isWinner) {
                    endRoundWithDraw(boardPane);
                }
            }
        } else if (!(board.getPlayerWithMove() instanceof Computer)) {
            Alert noMoveNow = new Alert(Alert.AlertType.ERROR, "now moves player", ButtonType.OK);
            noMoveNow.showAndWait();
        }
    }

    private static void checkingWinner(GridPane boardPane, boolean isWinner) {
        if (isWinner) {
            board.setRoundWinner(board.getPlayerWithMove());
            endRoundWithWinner(boardPane, board.getRoundWinner());
        }
    }

    private static void endRoundWithDraw(GridPane boardPane) {
        drawLines(boardPane);
        board.cleanRoundData();
        moveCounter.set(0);
        Alert drawInfo = new Alert(Alert.AlertType.INFORMATION, "this round finished with draw", ButtonType.OK);
        drawInfo.showAndWait();
    }

    private static void endRoundWithWinner(GridPane boardPane, Player player) {
        moveCounter.set(0);
        board.cleanRoundData();
        gameplay.setText("G A M E P L A Y \n" + "move: " + board.getPlayerWithMove());
        if (player.getWins() == board.getNumberOfRoundsToWin()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "the winner is " + player, ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "this round won " + player, ButtonType.OK);
            drawLines(boardPane);
            alert.showAndWait();
        }

    }

    private static final String INFO =
            "Tic-tac-toe is played on a three-by-three grid by two players, " +
            "who alternately place the marks X and O in one " +
            "of the nine spaces in the grid. " +
            "To place your mark just click in chosen space. " +
            "Alternatively, it is possible to play ten-by-ten board, where" +
            "every set of 5 equal marks together provides a win.";
}