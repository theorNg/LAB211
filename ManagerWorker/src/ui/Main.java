/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import entity.SalaryHistory;
import entity.Worker;
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
        control.readFile();
        do {
            int choice = Validator.getInt("======== Worker Management =========\n"
                    + "1.	Add Worker\n"
                    + "2.	Up salary\n"
                    + "3.	Down salary\n"
                    + "4.	Display Information salary\n"
                    + "5.	Exit\n"
                    + "Enter your choice: ",
                    "Just 1 -> 5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        Worker worker = control.addWorker();
                        System.out.println("Add success: \n" + worker.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    control.saveWorkersToFile();
                    break;
                case 2:
                    try {
                        SalaryHistory history = control.upSalary();
                        System.out.println("Up success: \n" + history.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    control.saveHistoryToFile();
                    control.saveWorkersToFile();
                    break;
                case 3:
                    try {
                        SalaryHistory history = control.downSalary();
                        System.out.println("Add success: \n" + history.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    control.saveHistoryToFile();
                    control.saveWorkersToFile();
                    break;
                case 4:
                    try {
                        control.showHistory();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        } while (true);
    }

}
