/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Game;
import java.util.Scanner;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean keepPlaying;
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        do {
            game.playGame();

            System.out.println("Bạn có muốn tiếp tục chơi không?");
            String response = scanner.nextLine();
            keepPlaying = response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes");
        } while (keepPlaying);
        game.printReport();
    }
}
