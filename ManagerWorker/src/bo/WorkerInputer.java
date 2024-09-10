/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Worker;
import utils.Validator;

/**
 *
 * @author win
 */
public class WorkerInputer {

    private Worker worker;

    public WorkerInputer() {
        worker = new Worker();
    }

    public Worker input() {
        worker.setId(Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase());
        worker.setName(Validator.getString("Enter Name: ", "Invalid!", "[A-Za-z\\s]+"));
        worker.setAge(Validator.getInt("Enter age: ", "age >= 18 and <=50 !", "Invalid!", 18, 50));
        worker.setSalary(Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE));
        worker.setWorkLocation(Validator.getString("Enter work location: ", "Invalid!", "[A-Za-z0-9\\s]+"));
        return worker;
    }
}
