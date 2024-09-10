/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Task;
import utils.Validator;

/**
 *
 * @author win
 */
public class TaskInputer {

    private Task task;

    public TaskInputer() {
        task = new Task();
    }

    public Task input() {
        task.setRequirementName(Validator.getString("Requirement Name: ",
                "Invalid!", "[A-Za-z0-9\\s]+"));
        task.setTaskTypeID(Validator.getInt("1. Code\n"
                + "2. Test\n"
                + "3. Design\n"
                + "4. Review\n"
                + "Enter your choice: ", "Just be 1 -> 4",
                "Invalid!", 1, 4));
        task.setDate(Validator.getDate("Enter Date: ",
                "Invalid! Please enter format: dd-MM-yyyy", "dd-MM-yyyy"));
        while (true) {
            task.setPlanFrom(Validator.getDouble("From: ", "Just be 8 -> 17",
                    "Invalid!", 8, 17));
            String from = task.getPlanFrom() + "";
            if (from.split("\\.")[1].equals("0") || from.split("\\.")[1].equals("5")) {
                break;
            } else {
                System.out.println("Must be x.0 or x.5");
            }
        }
        while (true) {
            task.setPlanTo(Validator.getDouble("To: ",
                    "Just be " + (task.getPlanFrom() + 0.5) + "-> 17.5",
                    "Invalid!", (task.getPlanFrom() + 0.5), 17.5));
            String to = task.getPlanTo() + "";
            if (to.split("\\.")[1].equals("0") || to.split("\\.")[1].equals("5")) {
                break;
            } else {
                System.out.println("Must be x.0 or x.5");
            }
        }
        task.setAssign(Validator.getString("Assignee: ",
                "Invalid!", "[A-Za-z0-9\\s]+"));
        task.setReviewer(Validator.getString("Reviewer: ",
                "Invalid!", "[A-Za-z0-9\\s]+"));
        return task;
    }
}
