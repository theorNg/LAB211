/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

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
public class ClassInputer {

    private Classes classes;

    public ClassInputer() {
        classes = new Classes();
    }

    public Classes input(ManagerMember managerMember, ManagerEquipment managerEquipment) throws Exception {
        String name = Validator.getString("Enter name: ", "Can not empty!", "^(?!\\s*$).+");
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
        String capacity = Validator.getString("Enter capacity: ", "Can not empty!", "^(?!\\s*$).+");
        String tableMember = managerMember.toString();
        if (tableMember == null) {
            throw new Exception("List Member is empty! Can not create class!");
        } else {
            System.out.println(tableMember);
        }
        ArrayList<Member> listMember = classes.getMembers();
        while (true) {
            Member member;
            while (true) {
                String ID = Validator.getString("Enter ID Member: ", "Can not empty!", "^(?!\\s*$).+");
                member = managerMember.getMember(ID);
                if (member == null) {
                    System.out.println("Can not found ID! Try again");
                } else {
                    if (classes.checkDuplicateMember(ID)) {
                        System.out.println("This ID is exited!!! Try again");
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
        String tableEquipment = managerEquipment.toString();
        if (tableEquipment == null) {
            throw new Exception("List Equipment is empty! Can not create class!");
        } else {
            System.out.println(tableEquipment);
        }
        ArrayList<Equipment> listEquipment = classes.getEquipments();
        while (true) {
            Equipment equipment;
            while (true) {
                String ID = Validator.getString("Enter ID Equipment: ", "Can not empty!", "^(?!\\s*$).+");
                equipment = managerEquipment.getEquipment(ID);
                if (equipment == null) {
                    System.out.println("Can not found ID! Try again");
                } else {
                    if (classes.checkDuplicateEquipment(ID)) {
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
        classes.setName(name);
        classes.setSchedule(schedule);
        classes.setCapacity(capacity);
        return classes;
    }
}
