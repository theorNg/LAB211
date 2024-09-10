/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Equipment;
import utils.Validator;

/**
 *
 * @author win
 */
public class EquipmentInputer {

    private Equipment equipment;

    public EquipmentInputer() {
        equipment = new Equipment();
    }

    public Equipment input() {
        equipment.setName(Validator.getString("Enter name: ", "Can not empty!", "^(?!\\s*$).+"));
        equipment.setType(Validator.getString("Enter type: ", "Can not empty!", "^(?!\\s*$).+"));
        equipment.setQuantity(Validator.getInt("Enter quantity: ", "Must be > 0 ", 
                "Invalid!", 1, Integer.MAX_VALUE));
        equipment.setCondition(Validator.getString("Enter condition: ","Can not empty!", "^(?!\\s*$).+"));
        return equipment;
    }
}
