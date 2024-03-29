package com.likeahim.ui;

import com.likeahim.logic.control.Move;
import com.likeahim.logic.exceptions.IncorrectNumberOfRoundsException;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String putName() {
        System.out.println("Enter your name: ");
        return SCANNER.nextLine();
    }

    public int putNumberOfRounds() {
        System.out.println("Enter number of rounds to win");
        while (true) {
            try {
                int input = SCANNER.nextInt();
                if (input > 0)
                    return input;
                else
                    throw new IncorrectNumberOfRoundsException();
            } catch (Exception e) {
                System.out.println("wrong input, try again");
            } finally {
                SCANNER.nextLine();
            }
        }
    }

    public Marker markChoice() {
        System.out.println("""
                Cross or nought? Enter which mark you want to play,
                enter 'x' to play cross
                enter 'o' to play nought""");
        while (true) {
            try {
                return getMarker();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //with try-catch
    private static Marker getMarker() {
        String mark = SCANNER.nextLine();
        if (mark.equals("x".toLowerCase())) return new Cross();
        else if (mark.equals("o".toLowerCase())) return new Nought();
        else throw new InputMismatchException("no such a mark, try again");
    }

    public static Move enterTheMove() {
        while (true) {
            try {
                int row = SCANNER.nextInt();
                SCANNER.nextLine();
                int col = SCANNER.nextInt();
                return new Move(row, col);
            } catch (InputMismatchException e) {
                System.out.println("something went wrong, try again");
            } finally {
                SCANNER.nextLine();
            }
        }
    }

    public boolean isSinglePlayerGame() {
        while (true) {
            System.out.println("""
                    Enter:
                    y -> single player
                    n -> multiplayer
                    """);
            try {
                String result = SCANNER.nextLine();
                if (result.equals("y".toLowerCase()))
                    return true;
                else if (result.equals("n".toLowerCase()))
                    return false;
                else
                    throw new InputMismatchException("no such a option, try again");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void incorrectMove(Exception e) {
        System.out.println("incorrect move, try again");
    }

    public void showRoundWinner(Player roundWinner) {
        try {
            System.out.println("this round won - " + roundWinner.getName() + "\n");
        } catch (NullPointerException e) {
            System.out.println("no winner for this round, status quo\n");
        }
    }

    public void showGameWinner(Player gameWinner, int numberOfRounds) {
        System.out.println("this game after " + numberOfRounds + " rounds won - " + gameWinner.getName());
    }

    public void infoMove(Player player) {
        System.out.println("Player " + player.getName() + " moves now");
        System.out.println("""
                enter coordinates for your next mark
                coordinate row and confirm with enter
                coordinate col and confirm with enter
                """);
    }

    public void printInfo(String info) {
        System.out.println(info);
    }

    public void endGame() {
        System.out.println("good bye, thanks for a game");
        SCANNER.close();
    }

    public int putSizeOfBoard() {
        System.out.println("""
                which version do you want to play?
                enter 'c' for classic 3x3 game
                enter 'e' for extended 10x10 game
                """);
        while (true) {
            String s = SCANNER.nextLine();
            if (s.equals("c".toLowerCase()))
                return 3;
            else if (s.equals("e".toLowerCase()))
                return 10;
            else
                System.out.println("no such a version, try again");
        }
    }
}