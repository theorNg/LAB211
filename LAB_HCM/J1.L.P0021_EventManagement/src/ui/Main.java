/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String choose;
        while (true) {
            int choice = Validator.getInt("==========Management Event==========\n"
                    + "1. Create a new event.\n"
                    + "2. Check if an event exists.\n"
                    + "3. Search for event information by location.\n"
                    + "4. Update event\n"
                    + "5. Save events to a file.\n"
                    + "6. Print the list of events from the file.\n"
                    + "7. Others - Quit.\n"
                    + "Enter your choice : ", "Just be 1->7", "Invalid!", 1, 7);
            switch (choice) {
                case 1:
                    while (true) {
                        control.createEvent();
                        choose = Validator.getString("Do you want to continue(Y or N): ",
                                "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        control.checkExistenceEvent();
                        choose = Validator.getString("Do you want to continue(Y or N): ",
                                "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        control.searchEventInfor();
                        choose = Validator.getString("Do you want to continue(Y or N): ",
                                "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                case 4:
                    int choice2;
                    do {
                        choice2 = Validator.getInt("==========Update event==========\n"
                                + "4.1. Update event details.\n"
                                + "4.2. Delete event.\n"
                                + "4.3. Return main menu\n"
                                + "Enter your choice : ", "Just be 1->3", "Invalid!", 1, 3);
                        switch (choice2) {
                            case 1:
                                while (true) {
                                    if (control.update()) {
                                        System.out.println("Update success!");
                                    } else {
                                        System.out.println("Update fail!");
                                    }
                                    choose = Validator.getString("Do you want to continue(Y or N): ",
                                            "Just Y or N", "[YNyn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                while (true) {
                                    if (control.delete()) {
                                        System.out.println("Delete success!");
                                    } else {
                                        System.out.println("Delete fail!");
                                    }
                                    choose = Validator.getString("Do you want to continue(Y or N): ",
                                            "Just Y or N", "[YNyn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                break;
                        }
                    } while (choice2 != 3);
                    break;
                case 5:
                    if (control.saveFile()) {
                        System.out.println("Save file success!");
                    } else {
                        System.out.println("Save file fail!");
                    }
                    choose = Validator.getString("Do you want to return main menu(Y) or exit program(N): ",
                            "Just Y or N", "[YNyn]");
                    if (choose.equalsIgnoreCase("N")) {
                        System.exit(0);
                    }
                    break;
                case 6:
                    if (control.readfile()) {
                        System.out.println("Read file success!");
                        control.showInforList();
                    } else {
                        System.out.println("Read file fail!");
                    }
                    choose = Validator.getString("Do you want to return main menu(Y) or exit program(N): ",
                            "Just Y or N", "[YNyn]");
                    if (choose.equalsIgnoreCase("N")) {
                        System.exit(0);
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }

}
