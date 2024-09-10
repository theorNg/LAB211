/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bo.ManagerPerSon;

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
        System.out.println("=====Management Person programer===== ");
        ManagerPerSon manager = new ManagerPerSon();
        manager.input();
        manager.sortBySalary();
        manager.displayListPerson();   
    }
}
