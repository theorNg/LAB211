/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import utils.Validator;

/**
 *
 * @author win
 */
public class Person {
    private String name;
    private String address;
    private double salary;

    public Person() {
    }

    public Person(String name, String address, double salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public void input(){
        System.out.println("Input Information of Person ");
        name = Validator.getString("Please input name:", "Invalid!", "[A-Za-z\\s]+");
        address=Validator.getString("Please input address:", "Invalid!", "[A-Za-z\\d\\s]+");
        salary=Validator.getDouble("Please enter salary:", "Salary is greater than zero ",
                "You must input digit.", Double.MIN_VALUE, Double.MAX_VALUE);
    }
    public void display(){
        System.out.println("Information of Person you have entered: ");
        System.out.println("Name: "+name);
        System.out.println("Address: "+address);
        System.out.println("Salary: "+salary);
    }
    
}
