/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import entity.RAMItem;
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
        control.readFileRamItem();
        while (true) {
            int choice = Validator.getInt("============Laptop RAM Management===============\n"
                    + "1. Add an Item\n"
                    + "2. Search SubMenu\n"
                    + "3. Update Item Information\n"
                    + "4. Delete Item\n"
                    + "5. Show All Items\n"
                    + "6. Store Data to Files\n"
                    + "7. Quit Menu\n"
                    + "Enter your choice: ", "Must be 1->7", "Must be Enter digit", 1, 7);
            switch (choice) {
                case 1:
                    try {
                        RAMItem item = control.addRamItem();
                        System.out.println("Add success: " + item);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    while (true) {
                        int mode = Validator.getInt("=========Search SubMenu===========\n"
                                + "1. Search by type\n"
                                + "2. Search by bus\n"
                                + "3. Search by brand\n"
                                + "4. Return main menu\n"
                                + "Enter your choice: ",
                                "Must be 1->4", "Invalid", 1, 4);
                        if (mode == 4) {
                            break;
                        } else {
                            control.search(mode);
                        }
                    }
                    break;
                case 3:
                    try {
                        RAMItem item = control.updateItem();
                        System.out.println("Update Success: " + item);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        RAMItem item = control.deleteItem();
                        System.out.println("Delete Success: " + item);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    control.showAllItem();
                    break;
                case 6:
                    control.saveFileRamItem();
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }

}
