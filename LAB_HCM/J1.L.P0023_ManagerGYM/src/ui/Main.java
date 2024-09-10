/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import entity.Classes;
import entity.Equipment;
import entity.Member;
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
        control.readfileMember();
        control.readfileEquipment();
        control.readfileClasses();
        while (true) {
            int choice = Validator.getInt("===========GYM MANAGEMENT===========\n"
                    + "1. Member Management\n"
                    + "2. Equipment Management\n"
                    + "3. Class Management\n"
                    + "4. Exit\n"
                    + "Enter your choice: ", "Just 1 -> 4",
                    "Invalid!", 1, 4);
            switch (choice) {
                case 1:
                    int choice1;
                    do {
                        choice1 = Validator.getInt("===========MEMBER MANAGEMENT===========\n"
                                + "1. Create a new member\n"
                                + "2. Sort and display existing member information\n"
                                + "3. View and update existing member information\n"
                                + "4. Delete a member\n"
                                + "5. Return main menu\n"
                                + "Enter your choice: ", "Just 1 -> 5",
                                "Invalid!", 1, 5);
                        switch (choice1) {
                            case 1:
                                try {
                                    Member newMember = control.createMember();
                                    System.out.println(newMember);
                                    control.saveFileMember();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 2:
                                control.showListMember();
                                break;
                            case 3:
                                control.viewUpdateMember();
                                control.saveFileMember();
                                break;
                            case 4:
                                control.deleteMember();
                                control.saveFileMember();
                                break;
                        }
                    } while (choice1 < 5);
                    break;
                case 2:
                    int choice2;
                    do {
                        choice2 = Validator.getInt("===========EQUIPMENT MANAGEMENT===========\n"
                                + "1. Add new equipment\n"
                                + "2. Sort and display equipment\n"
                                + "3. Update and manage equipment\n"
                                + "4. Remove equipment\n"
                                + "5. Return main menu\n"
                                + "Enter your choice: ", "Just 1 -> 5",
                                "Invalid!", 1, 5);
                        switch (choice2) {
                            case 1:
                                try {
                                    Equipment newEquipment = control.createEquipment();
                                    System.out.println(newEquipment);
                                    control.saveFileEquipment();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 2:
                                control.showListEquipment();
                                break;
                            case 3:
                                control.viewUpdateEquipment();
                                control.saveFileEquipment();
                                break;
                            case 4:
                                control.deleteEquipment();
                                control.saveFileEquipment();
                                break;
                        }
                    } while (choice2 < 5);
                    break;

                case 3:
                    int choice3;
                    do {
                        choice3 = Validator.getInt("===========CLASSES MANAGEMENT===========\n"
                                + "1. Add new classes\n"
                                + "2. Update and manage class\n"
                                + "3. Remove a class\n"
                                + "4. Return main menu\n"
                                + "Enter your choice: ", "Just 1 -> 4",
                                "Invalid!", 1, 4);
                        switch (choice3) {
                            case 1:
                                try {
                                    Classes newClass = control.createClasses();
                                    System.out.println(newClass);
                                    control.saveFileClasses();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                control.saveFileClasses();
                                break;
                            case 2:
                                int choice4;
                                do {
                                    choice4 = Validator.getInt("===========Update and manager class===========\n"
                                            + "1. update schedule\n"
                                            + "2. change members\n"
                                            + "3. adjust equipment\n"
                                            + "4. Return class menu\n"
                                            + "Enter your choice: ", "Just 1 -> 4",
                                            "Invalid!", 1, 4);
                                    switch (choice4) {
                                        case 1:
                                            control.updateSchedule();
                                            break;
                                        case 2:
                                            try {
                                                control.changeMembers();
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 3:
                                            try {
                                                control.adjustEquipment();
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                    }
                                } while (choice4 < 4);
                                control.saveFileClasses();
                                break;
                            case 3:
                                control.deleteClass();
                                control.saveFileClasses();
                                break;
                        }
                    } while (choice3 < 4);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

}
