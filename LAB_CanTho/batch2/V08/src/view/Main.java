/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.StudentArray;
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
        System.out.println("==============Student Management==============");
        StudentArray array = new StudentArray();
        while (true) {            
            int choice = Validator.getInt("**************************************\n"
                    + " 1. Add a student.\n"
                    + " 2. Remove a student.\n"
                    + " 3. Search a student.\n"
                    + " 4. Print list students in an ascending folder.\n"
                    + " 5. Exit the program\n"
                    + "**************************************\n"
                    + "Enter your choice: ", "Just be 1->5", "Invalid!", 1, 5); 
            switch(choice){
                case 1:
                    array.addStudent();
                    break;
                case 2:
                    array.removeStudent();
                    break;
                case 3:
                    array.searchStudent();
                    break;
                case 4:
                    array.printStudents();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
    
}
