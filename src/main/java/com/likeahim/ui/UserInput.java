package com.likeahim.ui;

import com.likeahim.logic.control.Move;
import com.likeahim.logic.exceptions.IncorrectMoveException;
import com.likeahim.logic.marks.Cross;
import com.likeahim.logic.marks.Marker;
import com.likeahim.logic.marks.Nought;
import com.likeahim.logic.players.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public String putName() {
        System.out.println("Enter your name: ");
        return scanner.nextLine();
    }

    public int putNumberOfRounds() {
        System.out.println("Enter number of rounds to win");
        while (true) {
            try {
                int rounds = scanner.nextInt();
                return rounds;
            } catch (Exception e) {
                System.out.println("wrong input, try again");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public Marker markChoice() {
        System.out.println("Cross or nought? Enter which mark you want to play,\n" + "enter 'x' to play cross\n" + "enter 'o' to play nought");
        while (true) {
            try {
                return getMarker();
            } catch (Exception e) {
                System.out.println("wrong choice, try again");
            }
        }
    }

    private static Marker getMarker() {
        String mark = scanner.nextLine();
        if (mark.equals("x".toLowerCase())) return new Cross();
        else if (mark.equals("o".toLowerCase())) return new Nought();
        else throw new InputMismatchException("no such a mark, try again");
    }

    //InputMissmatchEx.
    public static Move makeAMove() {
        int row = intWithEnter();
        int col = intWithEnter();
        return new Move(row, col);

    }

    private static int intWithEnter() {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            input = scanner.nextInt();
            correctInput = true;
            scanner.nextLine();
        }
        return input;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public void incorrectMove(Exception e) {
        System.out.println("incorrect move, try again");
    }

    public void showRoundWinner(Player roundWinner) {
        try {
            System.out.println("this round won - " + roundWinner.getName());
        } catch (NullPointerException e) {
            System.out.println("no winner for this round, status quo");
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

}