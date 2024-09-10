/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.EventInputer;
import bo.ManagerEvent;
import entity.Event;
import entity.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import utils.Validator;

/**
 *
 * @author win
 */
public class Controller {

    private ManagerEvent manager;
    private EventInputer inputer;

    public Controller() {
        manager = new ManagerEvent();
    }

    public void createEvent() {
        inputer = new EventInputer();
        Event event = inputer.input();
        if (manager.add(event)) {
            System.out.println("Add success: ");
            System.out.println(event);
        } else {
            System.out.println("Can not add");
        }
    }

    public void checkExistenceEvent() {
        int ID = Validator.getInt("Enter ID: ", "Just be >0", "Please enter integer!",
                1, Integer.MAX_VALUE);
        if (manager.isExistEvent(ID)) {
            System.out.println("Exist Event");
        } else {
            System.out.println("No Event Found!");
        }
    }

    public void searchEventInfor() {
        String keyLocation = Validator.getString("Enter location: ",
                "Location can not empty!!!", "^(?!\\s*$).+");
        ArrayList<Event> listSearch = manager.getListByLocation(keyLocation);
        if (listSearch.isEmpty()) {
            System.out.println("No Event Found");
        } else {
            ManagerEvent result = new ManagerEvent();
            result.setList(listSearch);
            System.out.println(result.toString());
        }
    }

    public boolean update() {
        int ID = Validator.getInt("Enter ID: ", "Just be >0", "Please enter integer!",
                1, Integer.MAX_VALUE);
        Event oldEvent = manager.getEventByID(ID);
        if (oldEvent == null) {
            System.out.println("Event does not exist");
            return false;
        } else {
            System.out.println("Before update: ");
            System.out.println(oldEvent);
        }
        String newName = Validator.getString(
                "Enter NEW Name(Press blank(Enter) now to keep the old information): ",
                "Please enter event name is at least five "
                + "characters long and does not contain spaces", "^([^\\s]{5,})?$");
        Scanner sc = new Scanner(System.in);
        Date newDate = null;
        while (true) {
            System.out.printf("Enter new date (Press blank(Enter) now to keep the old information): ");
            String newDateString = sc.nextLine().trim();
            if (!newDateString.trim().isEmpty()) {
                newDate = Validator.getDateByString(newDateString, "yyyy-MM-dd");
                if (newDate == null) {
                    System.out.println("Date must be format yyyy-MM-dd! Try again!");
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        System.out.printf("Enter new location(Press blank(Enter) now to keep the old information): ");
        String newLocation = sc.nextLine().trim();
        String newNumberOfAttedeesString = Validator.getString(
                "Enter NEW Number Of Ettedees(Press blank(Enter) now to keep the old information):",
                "Please enter price and must be >0",
                "^([1-9][0-9]*)?");
        int newNumberOfAttedees = 0;
        if (!newNumberOfAttedeesString.trim().isEmpty()) {
            newNumberOfAttedees = Integer.parseInt(newNumberOfAttedeesString);
        }
        int choice = Validator.getInt("Only two status:\n"
                + "1. Available\n"
                + "2. Not Available\n"
                + "Enter you choice: ", "Just 1->2", "Invalid!", 1, 2);
        Status newStatus = Status.getStatus(choice);
        boolean isSuccess = manager.update(ID, newName, newDate, newLocation,
                newNumberOfAttedees, newStatus);
        System.out.println("After update: ");
        System.out.println(oldEvent);
        return isSuccess;
    }

    public boolean delete() {
        int ID = Validator.getInt("Enter ID: ", "Just be >0", "Please enter integer!",
                1, Integer.MAX_VALUE);
        Event removeEvent = manager.getEventByID(ID);
        if (removeEvent == null) {
            System.out.println("Event does not exist");
            return false;
        } else {
            System.out.println("Infor event you want delete: ");
            System.out.println(removeEvent);
            String choose = Validator.getString("Do you want to delete(Y or N): ",
                    "Just Y or N", "[YNyn]");
            if (choose.equalsIgnoreCase("Y")) {
                return manager.delete(ID);
            } else {
                return false;
            }
        }
    }

    public boolean saveFile() {
        try {
            manager.saveFile("events.dat");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean readfile() {
        try {
            manager.readFile("events.dat");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void showInforList() {
        ArrayList<Event> listCopy = new ArrayList<>(manager.getList());
        ManagerEvent managerlistCopy = new ManagerEvent();
        managerlistCopy.setList(listCopy);
        managerlistCopy.sort();
        String result = managerlistCopy.toString();
        if (result == null) {
            System.out.println("The List is empty");
        } else {
            System.out.println(result);
        }
    }
}
