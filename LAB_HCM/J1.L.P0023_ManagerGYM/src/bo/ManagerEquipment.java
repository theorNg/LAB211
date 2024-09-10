/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Equipment;
import entity.Member;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author win
 */
public class ManagerEquipment {

    private ArrayList<Equipment> list;
    private String lastID;

    public ManagerEquipment() {
        list = new ArrayList<>();
    }

    public ArrayList<Equipment> getList() {
        return list;
    }

    public void setList(ArrayList<Equipment> list) {
        this.list = list;
    }

    public boolean add(Equipment equipment) {
        if (list.isEmpty()) {
            lastID = "EQU00001";
        } else {
            String lastIDMember = list.get(list.size() - 1).getEquipmentID();
            int number = Integer.parseInt(lastIDMember.substring(3));
            lastID = String.format("EQU%05d", number + 1);
        }
        equipment.setEquipmentID(lastID);
        return list.add(equipment);
    }

    public void sort() {
        Collections.sort(list);
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        String str = String.format("|%10s|%15s|%15s|%10s|%10s|\n", "ID", "Name", "Type", "Quantity", "Condition");
        for (int i = 0; i < list.size(); i++) {
            str += String.format("|%10s|%15s|%15s|%10d|%10s|\n", list.get(i).getEquipmentID(),
                    list.get(i).getName(), list.get(i).getType(),
                    list.get(i).getQuantity(), list.get(i).getCondition());
        }
        return str;
    }

    public ArrayList<Equipment> getListEquipmentByID(String keyID) {
        ArrayList<Equipment> listSearch = new ArrayList<>();
        for (Equipment member : list) {
            if (member.getEquipmentID().toLowerCase().contains(keyID.toLowerCase())) {
                listSearch.add(member);
            }
        }
        return listSearch;
    }

    private int getIndexEquipment(String ID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEquipmentID().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    public boolean update(Equipment oldEquipment, Equipment newEquipment) {
        int index = getIndexEquipment(oldEquipment.getEquipmentID());
        if (index == -1) {
            return false;
        }
        newEquipment.setEquipmentID(oldEquipment.getEquipmentID());
        list.set(index, newEquipment);
        return true;
    }

    public Equipment getEquipment(String ID) {
        for (Equipment equipment : list) {
            if (equipment.getEquipmentID().equalsIgnoreCase(ID)) {
                return equipment;
            }
        }
        return null;
    }

    public boolean remove(String ID) {
        Equipment equip = getEquipment(ID);
        if (equip == null) {
            return false;
        }
        int quantityAfterUpdate = equip.getQuantity() - 1;
        if (quantityAfterUpdate == 0) {
            list.remove(equip);
        } else {
            equip.setQuantity(quantityAfterUpdate);
        }
        return true;
    }

    public void writeToFile(String file) throws Exception {
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            for (Equipment eq : list) {
                out.println(eq.getEquipmentID() + "|" + eq.getName() + "|"
                        + eq.getType() + "|" + eq.getQuantity() + "|" + eq.getCondition());
            }
        } catch (IOException e) {
            throw new Exception("Error save file " + file);
        }
    }

    public void readFromFile(String file) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length == 5) {
                    Equipment eq = new Equipment(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), fields[4]);
                    list.add(eq);
                }
            }
        } catch (IOException e) {
            throw new Exception("File " + file + " is empty!");
        }
    }
}
