/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Random;
import utils.Validator;

/**
 *
 * @author win
 */
public class Game {

    private int totalGames;
    private int totalGuesses;
    private int bestGame;
    private double avgGuesses;
    private static final int MAXIMUM = 100;

    /**
     * Constructs a new Game instance with initial values.
     */
    public Game() {
        this.totalGames = 0;
        this.totalGuesses = 0;
        this.bestGame = Integer.MAX_VALUE;
        this.avgGuesses = 0;
    }

    /**
     * Plays a single game of number guessing.
     * The user is prompted to guess a number between 0 and MAXIMUM.
     * Provides feedback if the guess is too high or too low, and continues until the correct number is guessed.
     */
    public void playGame() {
        Random random = new Random();
        int luckyNumber = random.nextInt(MAXIMUM + 1);
        int guesses = 0;
        while (true) {
            int guess = Validator.getInt("Đoán số may mắn từ 0 tới " + MAXIMUM + ": ",
                    "Must be 0->" + MAXIMUM, "Invalid!", 0, MAXIMUM);
            guesses++;
            totalGuesses++;
            if (guess > luckyNumber) {
                System.out.println("Số may mắn nhỏ hơn số dự đoán của bạn.");
            } else if (guess < luckyNumber) {
                System.out.println("Số may mắn lớn hơn số dự đoán của bạn.");
            } else {
                System.out.println("Chúc mừng bạn đã đoán đúng con số may mắn sau " + guesses + " lần dự đoán.");
                break;
            }
        }

        bestGame = Math.min(bestGame, guesses);
        totalGames++;
    }

    /**
     * Prints a report of the game statistics including total games played,
     * total guesses made, average guesses per game, and best game (fewest guesses).
     */
    public void printReport() {
        avgGuesses = totalGames > 0 ? ((double) totalGuesses) / totalGames : 0;
        System.out.println("Tổng số lần chơi: " + totalGames);
        System.out.println("Tổng số lần dự đoán: " + totalGuesses);
        System.out.println("Số lần dự đoán trung bình mỗi lượt: " + avgGuesses);
        System.out.println("Lượt chơi tốt nhất (ít lần dự đoán nhất): " + bestGame);
    }

    // Getters and setters for the fields

    public int getTotalGames() {
        return totalGames;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getBestGame() {
        return bestGame;
    }

    public double getAvgGuesses() {
        return avgGuesses;
    }
}
