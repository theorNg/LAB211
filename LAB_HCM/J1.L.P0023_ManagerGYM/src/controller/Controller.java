/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.ClassInputer;
import bo.EquipmentInputer;
import bo.ManagerClasses;
import bo.ManagerEquipment;
import bo.ManagerMember;
import bo.MemberInputer;
import entity.Classes;
import entity.Equipment;
import entity.Member;
import entity.Slot;
import entity.Weekdays;
import java.util.ArrayList;
import utils.Validator;

/**
 *
 * @author win
 */
public class Controller {

    private ManagerMember managerMember;
    private MemberInputer inputerMember;
    private ManagerEquipment managerEquipment;
    private EquipmentInputer inputerEquipment;
    private ManagerClasses managerClass;
    private ClassInputer inputerClass;

    public Controller() {
        managerMember = new ManagerMember();
        managerEquipment = new ManagerEquipment();
        managerClass = new ManagerClasses();
    }

    public Member createMember() throws Exception {
        inputerMember = new MemberInputer();
        Member newMember = inputerMember.input();
        if (managerMember.add(newMember)) {
            return newMember;
        }
        throw new Exception("Can not create member");
    }

    public void showListMember() {
        ArrayList<Member> listCopy = new ArrayList<>(managerMember.getList());
        ManagerMember managerlistCopy = new ManagerMember();
        managerlistCopy.setList(listCopy);
        managerlistCopy.sort();
        String result = managerlistCopy.toString();
        if (result == null) {
            System.out.println("The List is empty");
        } else {
            System.out.println(result);
        }
    }

    public void viewUpdateMember() {
        Member oldMember;
        while (true) {
            String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
            ArrayList<Member> listSearch = managerMember.getListMemberByID(keyID);
            ManagerMember manager = new ManagerMember();
            manager.setList(listSearch);
            String result = manager.toString();
            if (result == null) {
                System.out.println("Can not found ID. Update fail");
            } else {
                System.out.println(result);
            }
            if (listSearch.size() == 1) {
                oldMember = listSearch.get(0);
                break;
            }
        }
        System.out.println("Before update: ");
        System.out.println(oldMember);
        inputerMember = new MemberInputer();
        Member newMember = inputerMember.input();
        if (managerMember.update(oldMember, newMember)) {
            System.out.println("After Update: ");
            System.out.println(newMember);
        } else {
            System.out.println("Update fail!");
        }
    }

    public void deleteMember() {
        Member oldMember;
        while (true) {
            String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
            ArrayList<Member> listSearch = managerMember.getListMemberByID(keyID);
            ManagerMember manager = new ManagerMember();
            manager.setList(listSearch);
            String result = manager.toString();
            if (result == null) {
                System.out.println("Can not found ID. Delete fail");
            } else {
                System.out.println(result);
            }
            if (listSearch.size() == 1) {
                oldMember = listSearch.get(0);
                break;
            }
        }
        String choose = Validator.getString("Do you want to delete(Y or N)? ", "Just Y or N", "[YNyn]");
        if (choose.equalsIgnoreCase("N")) {
            System.out.println("Delete fail");
            return;
        }
        if (managerMember.remove(oldMember.getId())) {
            System.out.println("Delete success: ");
            System.out.println(oldMember);
        } else {
            System.out.println("Delete fail");
        }
    }

    public void saveFileMember() {
        try {
            managerMember.saveFile("members.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readfileMember() {
        try {
            managerMember.readFile("members.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Equipment createEquipment() throws Exception {
        inputerEquipment = new EquipmentInputer();
        Equipment newEquipment = inputerEquipment.input();
        if (managerEquipment.add(newEquipment)) {
            return newEquipment;
        }
        throw new Exception("Can not create member");
    }

    public void showListEquipment() {
        ArrayList<Equipment> listCopy = new ArrayList<>(managerEquipment.getList());
        ManagerEquipment managerlistCopy = new ManagerEquipment();
        managerlistCopy.setList(listCopy);
        managerlistCopy.sort();
        String result = managerlistCopy.toString();
        if (result == null) {
            System.out.println("The List is empty");
        } else {
            System.out.println(result);
        }
    }

    public void viewUpdateEquipment() {
        Equipment oldEquipment;
        while (true) {
            String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
            ArrayList<Equipment> listSearch = managerEquipment.getListEquipmentByID(keyID);
            ManagerEquipment manager = new ManagerEquipment();
            manager.setList(listSearch);
            String result = manager.toString();
            if (result == null) {
                System.out.println("Can not found ID. Update fail");
            } else {
                System.out.println(result);
            }
            if (listSearch.size() == 1) {
                oldEquipment = listSearch.get(0);
                break;
            }
        }
        System.out.println("Before update: ");
        System.out.println(oldEquipment);
        inputerEquipment = new EquipmentInputer();
        Equipment newEquipment = inputerEquipment.input();
        if (managerEquipment.update(oldEquipment, newEquipment)) {
            System.out.println("After Update: ");
            System.out.println(newEquipment);
        } else {
            System.out.println("Update fail!");
        }
    }

    public void deleteEquipment() {
        Equipment oldEquipment;
        while (true) {
            String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
            ArrayList<Equipment> listSearch = managerEquipment.getListEquipmentByID(keyID);
            ManagerEquipment manager = new ManagerEquipment();
            manager.setList(listSearch);
            String result = manager.toString();
            if (result == null) {
                System.out.println("Can not found ID. Delete fail");
            } else {
                System.out.println(result);
            }
            if (listSearch.size() == 1) {
                oldEquipment = listSearch.get(0);
                break;
            }
        }
        String choose = Validator.getString("Do you want to delete(Y or N)? ", "Just Y or N", "[YNyn]");
        if (choose.equalsIgnoreCase("N")) {
            System.out.println("Delete fail");
            return;
        }
        if (managerEquipment.remove(oldEquipment.getEquipmentID())) {
            System.out.println("Delete success: ");
            System.out.println(oldEquipment);
        } else {
            System.out.println("Delete fail");
        }
    }

    public void saveFileEquipment() {
        try {
            managerEquipment.writeToFile("equipment.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readfileEquipment() {
        try {
            managerEquipment.readFromFile("equipment.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Classes createClasses() throws Exception {
        inputerClass = new ClassInputer();
        Classes newClass = inputerClass.input(managerMember, managerEquipment);
        if (managerClass.add(newClass)) {
            return newClass;
        }
        throw new Exception("Can not create member");
    }

    public void updateSchedule() {
        String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
        Classes oldClass = managerClass.getClass(keyID);
        if (oldClass == null) {
            System.out.println("Can not found id!!");
            return;
        }
        System.out.println("Before update: ");
        System.out.println(oldClass);
        System.out.println("=============Select schedule===========");
        int choice = Validator.getInt("1. Monday\n"
                + "2. Tuesday\n"
                + "3. Wednesday\n"
                + "4. Thursday\n"
                + "5. Friday\n"
                + "6. Saturday\n"
                + "7. Sunday\n"
                + "Enter your choice: ", "Just be 1->7", "Invalid!", 1, 7);
        String schedule = Weekdays.getWeekdayByChoice(choice).value();
        choice = Validator.getInt("1. SLOT1(7h30-9h50)\n"
                + "2. SLOT2(10h-12h20)\n"
                + "3. SLOT3(12h50-15h10)\n"
                + "4. SLOT4(15h20-17h40)\n"
                + "5. SLOT5(19h30-21h50)\n"
                + "Enter your choice: ", "Just be 1->5", "Invalid!", 1, 5);
        schedule += ":" + Slot.getSlotByChoice(choice).getTime();
        oldClass.setSchedule(schedule);
        System.out.println("After update: ");
        System.out.println(oldClass);
    }

    public void changeMembers() throws Exception {
        String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
        Classes oldClass = managerClass.getClass(keyID);
        if (oldClass == null) {
            System.out.println("Can not found id!!");
            return;
        }
        System.out.println("Before update: ");
        System.out.println(oldClass);
        int choice = Validator.getInt("1. Add member in this class\n"
                + "2. Delete member in this class\n"
                + "Enter your choice: ", "Just 1->2", "Invalid!", 1, 2);
        ArrayList<Member> listMember = oldClass.getMembers();
        switch (choice) {
            case 1:
                String tableMember = managerMember.toString();
                if (tableMember == null) {
                    throw new Exception("List Member is empty! Can not add member!");
                } else {
                    System.out.println(tableMember);
                }
                while (true) {
                    Member member;
                    while (true) {
                        String ID = Validator.getString("Enter ID Member: ", "Can not empty!", "^(?!\\s*$).+");
                        member = managerMember.getMember(ID);
                        if (member == null) {
                            System.out.println("Can not found ID! Try again");
                        } else {
                            if (oldClass.checkDuplicateMember(ID)) {
                                System.out.println("This ID is exited!!!");
                            } else {
                                break;
                            }
                        }
                    }
                    listMember.add(member);
                    String choose = Validator.getString("Do you continue add member? ",
                            "Just be Y or N ", "[YNyn]");
                    if (choose.equalsIgnoreCase("N")) {
                        break;
                    }
                }
                break;
            case 2:
                ManagerMember manager = new ManagerMember();
                manager.setList(listMember);
                tableMember = manager.toString();
                if (tableMember == null) {
                    throw new Exception("List Member is empty! Can not delete member!");
                } else {
                    System.out.println(tableMember);
                }
                while (true) {
                    Member member;
                    while (true) {
                        String ID = Validator.getString("Enter ID Member: ", "Can not empty!", "^(?!\\s*$).+");
                        member = manager.getMember(ID);
                        if (member == null) {
                            System.out.println("Can not found ID! Try again");
                        } else {
                            break;
                        }
                    }
                    listMember.remove(member);
                    String choose = Validator.getString("Do you continue remove member? ",
                            "Just be Y or N ", "[YNyn]");
                    if (choose.equalsIgnoreCase("N")) {
                        break;
                    }
                }
                break;
        }
        System.out.println("After update: ");
        System.out.println(oldClass);
    }

    public void adjustEquipment() throws Exception {
        String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
        Classes oldClass = managerClass.getClass(keyID);
        if (oldClass == null) {
            System.out.println("Can not found id!!");
            return;
        }
        System.out.println("Before update: ");
        System.out.println(oldClass);
        int choice = Validator.getInt("1. Add equipment in this class\n"
                + "2. Delete equipment in this class\n"
                + "Enter your choice: ", "Just 1->2", "Invalid!", 1, 2);
        ArrayList<Equipment> listEquipment = oldClass.getEquipments();
        switch (choice) {
            case 1:
                String tableEquipment = managerEquipment.toString();
                if (tableEquipment == null) {
                    throw new Exception("List Equipment is empty! Can not add Equipment!");
                } else {
                    System.out.println(tableEquipment);
                }
                while (true) {
                    Equipment equipment;
                    while (true) {
                        String ID = Validator.getString("Enter ID Equipment: ", "Can not empty!", "^(?!\\s*$).+");
                        equipment = managerEquipment.getEquipment(ID);
                        if (equipment == null) {
                            System.out.println("Can not found ID! Try again");
                        } else {
                            if (oldClass.checkDuplicateEquipment(ID)) {
                                System.out.println("This ID is exited!!!");
                            } else {
                                break;
                            }
                        }
                    }
                    listEquipment.add(equipment);
                    String choose = Validator.getString("Do you continue add equipment? ",
                            "Just be Y or N ", "[YNyn]");
                    if (choose.equalsIgnoreCase("N")) {
                        break;
                    }
                }
                break;
            case 2:
                ManagerEquipment manager = new ManagerEquipment();
                manager.setList(listEquipment);
                tableEquipment = manager.toString();
                if (tableEquipment == null) {
                    throw new Exception("List Equipment is empty! Can not delete equipment!");
                } else {
                    System.out.println(tableEquipment);
                }
                while (true) {
                    Equipment equipment;
                    while (true) {
                        String ID = Validator.getString("Enter ID Equipment: ", "Can not empty!", "^(?!\\s*$).+");
                        equipment = manager.getEquipment(ID);
                        if (equipment == null) {
                            System.out.println("Can not found ID! Try again");
                        } else {
                            break;
                        }
                    }
                    listEquipment.remove(equipment);
                    String choose = Validator.getString("Do you continue remove equipment? ",
                            "Just be Y or N ", "[YNyn]");
                    if (choose.equalsIgnoreCase("N")) {
                        break;
                    }
                }
                break;
        }
        System.out.println("After update: ");
        System.out.println(oldClass);
    }

    public void deleteClass() {
        String keyID = Validator.getString("Enter ID: ", "Can not empty!", "^(?!\\s*$).+");
        Classes oldClass = managerClass.getClass(keyID);
        if (oldClass == null) {
            System.out.println("Can not found id!!");
            return;
        }
        String choose = Validator.getString("Do you want to delete(Y or N)? ", "Just Y or N", "[YNyn]");
        if (choose.equalsIgnoreCase("N")) {
            System.out.println("Delete fail");
            return;
        }
        if (managerClass.remove(oldClass.getClassID())) {
            System.out.println("Delete success: ");
            System.out.println(oldClass);
        } else {
            System.out.println("Delete fail");
        }
    }

    public void saveFileClasses() {
        try {
            managerClass.saveFile("classes.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readfileClasses() {
        try {
            managerClass.readFile("classes.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
