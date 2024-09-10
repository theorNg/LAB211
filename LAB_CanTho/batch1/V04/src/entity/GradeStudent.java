/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import contant.Contant;
import validate.Validator;

/**
 *
 * @author win
 */
public class GradeStudent {

    private double totalMidtermScore;
    private double totalFinalScore;
    private double totalHomeworkScore;
    private int weightedMidtermScore;
    private int weightedFinalScore;
    private int weightedHomeworkScore;

    /**
     * This method starts the program and displays an introductory message.
     */
    public void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade");
    }

    public void inputMidTerm(int weight) {
        int scoreEarned = Validator.getInt("Score earned? ", "Please enter number 0->100", "Invalid!", 0, Contant.TOTAL_MAX);
        int scoresShifted = Validator.getInt("Were scores shifted (1 = yes, 2=no)? ", "Just 1 pr 2", "Invalid", 1, 2);
        int shiftAmount = 0;
        int totalPoints;
        if (scoresShifted == 1) {
            shiftAmount = Validator.getInt("Shift amount? ", "Please enter number >0", "Invalid!", 1, Integer.MAX_VALUE);
        }
        totalPoints = scoreEarned + shiftAmount;
        if (totalPoints > 100) {
            totalPoints = Contant.TOTAL_MAX;
        }
        System.out.println("Total points = " + totalPoints + " / " + Contant.TOTAL_MAX);
        totalMidtermScore = ((double) totalPoints / (double) Contant.TOTAL_MAX) * (double) weight;
        System.out.printf("Weighted score = %.1f", totalMidtermScore);
        System.out.println(" / " + weight);
    }

    public void inputFinalTerm(int weight) {
        int scoreEarned = Validator.getInt("Score earned? ", "Please enter number 0->100", "Invalid!", 0, Contant.TOTAL_MAX);
        int scoresShifted = Validator.getInt("Were scores shifted (1 = yes, 2=no)? ", "Just 1 pr 2", "Invalid", 1, 2);
        int shiftAmount = 0;
        int totalPoints;
        if (scoresShifted == 1) {
            shiftAmount = Validator.getInt("Shift amount? ", "Please enter number >0", "Invalid!", 0, Integer.MAX_VALUE);
        }
        totalPoints = scoreEarned + shiftAmount;
        if (totalPoints > 100) {
            totalPoints = Contant.TOTAL_MAX;
        }
        System.out.println("Total points = " + totalPoints + " / " + Contant.TOTAL_MAX);
        totalFinalScore = ((double) totalPoints / (double) Contant.TOTAL_MAX) * (double) weight;
        System.out.println("Weighted score = " + totalFinalScore + " / " + weight);
    }

    public void inputHomeWork(int weight) {
        int number = Validator.getInt("Number of assignments? ", "Please enter number >0", "Invalid!", 0, Integer.MAX_VALUE);
        double[] scores = new double[number];
        double[] maxScores = new double[number];
        int totalPoints = 0;
        int maxPoint = 0;
        String str;
        for (int i = 0; i < number; i++) {
            do {
                str = Validator.getString("Assignment " + (i + 1) + " score and max? ", "Invalid!", "^\\d+\\s+\\d+$");
                scores[i] = Integer.parseInt(str.trim().split(" ")[0]);
                maxScores[i] = Integer.parseInt(str.trim().split(" ")[1]);
                if (scores[i] > maxScores[i]) {
                    System.out.println("Score must be <= max score");
                } else {
                    break;
                }
            } while (true);
            totalPoints += scores[i];
            maxPoint += maxScores[i];
        }
        int attend = Validator.getInt("How many sections did you attend? ", "Enter number 0->6", "Invalid", 0, 6);
        int sectionPoints = attend * 5;
        if (sectionPoints > Contant.ATTEND_MAX) {
            sectionPoints = Contant.ATTEND_MAX;
        }
        System.out.println("Section points = " + sectionPoints + " / " + Contant.ATTEND_MAX);
        totalPoints += sectionPoints;
        maxPoint += Contant.ATTEND_MAX;
        System.out.println("Total points = " + totalPoints + " / " + maxPoint);
        totalHomeworkScore = ((double) totalPoints / (double) maxPoint) * (double) weight;
        System.out.printf("Weighted score = %.1f", totalHomeworkScore);
        System.out.println(" / " + weight);
    }

    public void input() {
        System.out.println("\nMidterm:");
        weightedMidtermScore = Validator.getInt("Weight (0-100)? ", "Please enter number 0->100", "Invalid!", 0, Contant.WEIGHT_MAX);
        inputMidTerm(weightedMidtermScore);
        System.out.println("\nFinal:");
        weightedFinalScore = Validator.getInt("Weight (0-100)? ", "Please enter number 0->" + (Contant.WEIGHT_MAX - weightedMidtermScore), "Invalid!", 0, Contant.WEIGHT_MAX - weightedMidtermScore);
        inputFinalTerm(weightedFinalScore);
        System.out.println("\nHomework:");
        weightedHomeworkScore = Validator.getInt("Weight (0-100)? ", "Please enter number " + (Contant.WEIGHT_MAX - weightedFinalScore - weightedMidtermScore),
                "Invalid!", Contant.WEIGHT_MAX - weightedFinalScore - weightedMidtermScore, Contant.WEIGHT_MAX - weightedFinalScore - weightedMidtermScore);
        inputHomeWork(weightedHomeworkScore);
    }

    /**
     * This method reports the overall grade for the course based on the
     * weighted scores of the exams and homework.
     */
    public void report() {
        double totalGrade = totalFinalScore + totalHomeworkScore + totalMidtermScore;
        double leastGrade;
        if (totalGrade >= 0.85) {
            leastGrade = 3;
        } else if (totalGrade >= 0.75) {
            leastGrade = 2;
        } else if (totalGrade >= 0.6) {
            leastGrade = 0.7;
        } else {
            leastGrade = 0;
        }
        System.out.printf("\nOverall percentage = %.1f\n", totalGrade);
        System.out.println("Your grade will be at least: " + leastGrade);
    }
}
