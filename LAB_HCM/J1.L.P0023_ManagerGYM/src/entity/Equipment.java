/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author win
 */
public class Equipment implements Comparable<Equipment>,Serializable{
    private String equipmentID;
    private String name;
    private String type;
    private int quantity;
    private String condition;

    public Equipment(String equipmentID, String name, String type, int quantity, String condition) {
        this.equipmentID = equipmentID;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.condition = condition;
    }

    public Equipment() {
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Equipment{" + equipmentID + ", " + name + ", " + type + ", " +
                quantity + ", " + condition + '}';
    }

    @Override
    public int compareTo(Equipment o) {
        return name.compareTo(o.name);
    }
    
}
