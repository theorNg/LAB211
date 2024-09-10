/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import manager.ListFruit;
import manager.Order;
import validate.Validator;

/**
 *
 * @author LeSyThanhLong
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ListFruit fruits = new ListFruit();
        Order orders = new Order();
        fruits.generateFruit();
        while (true) {
            System.out.println("1. Create Fruit");
            System.out.println("2. View orders");
            System.out.println("3. Shopping (for buyer)");
            System.out.println("4. Exit");
            int choice = Validator.getInt("Enter choice:", "Please enter 1->4",
                    "Invalid!", 1, 4);
            switch (choice) {
                case 1:
                    fruits.createFruit();
                    break;
                case 2:
                    orders.viewOrder();
                    break;
                case 3:
                    orders.shopping(fruits);
                    break;
                case 4:
                    return;
            }
        }
    }

}
