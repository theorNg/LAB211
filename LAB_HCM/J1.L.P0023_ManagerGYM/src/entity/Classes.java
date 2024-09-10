/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import bo.ManagerEquipment;
import bo.ManagerMember;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author win
 */
public class Classes implements Serializable {

    private String classID;
    private String name;
    private String schedule;
    private String capacity;
    private ArrayList<Member> members;
    private ArrayList<Equipment> equipments;

    public Classes(String classID, String name, String schedule, String capacity,
            ArrayList<Member> members, ArrayList<Equipment> equipments) {
        this.classID = classID;
        this.name = name;
        this.schedule = schedule;
        this.capacity = capacity;
        this.members = members;
        this.equipments = equipments;
    }

    public Classes() {
        members = new ArrayList<>();
        equipments = new ArrayList<>();
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(ArrayList<Equipment> equipments) {
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        ManagerMember managerMember = new ManagerMember();
        ManagerEquipment managerEquipment = new ManagerEquipment();
        managerMember.setList(members);
        managerEquipment.setList(equipments);
        String str = "============================================================================\n";
        str += String.format("Class ID: %s\n"
                + "Class Name: %s\n"
                + "Schedule: %s\n"
                + "Capacity: %s\n", classID, name, schedule, capacity);
        if (managerMember.toString() != null) {
            str += "--------------Members in class--------------------\n";
            str += managerMember.toString();
        }
        if (managerEquipment.toString() != null) {
            str += "--------------Equipments in class-----------------\n";
            str += managerEquipment.toString();
        }
        str += "============================================================================\n";
        return str;
    }

    public boolean checkDuplicateMember(String IDMember) {
        for (Member member : members) {
            if (member.getId().equalsIgnoreCase(IDMember)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateEquipment(String IDEquipment) {
        for (Equipment equip : equipments) {
            if (equip.getEquipmentID().equalsIgnoreCase(IDEquipment)) {
                return true;
            }
        }
        return false;
    }
}
