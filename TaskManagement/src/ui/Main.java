/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import entity.Task;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller control = new Controller();
        while (true) {
            int choice = Validator.getInt("========= Task program =========\n"
                    + "1.	Add Task\n"
                    + "2.	Delete task\n"
                    + "3.	Display Task\n"
                    + "4.	exit\n"
                    + "Enter your choice: ", "Just 1-> 4", "Invalid!", 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("------------Add Task------------");
                    try {
                        int IDTask = control.add();
                        System.out.println("Add success task id: " + IDTask);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("------------Del Task------------");
                    try {
                        Task task= control.delete();
                        System.out.println("Delete success:");
                        System.out.println(task);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("------------Show Task------------");
                    try {
                        control.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

}
