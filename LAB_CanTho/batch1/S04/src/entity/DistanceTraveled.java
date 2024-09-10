/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import utils.Validator;

public class DistanceTraveled {

    private double radius; 
    private int revolutions; 

    public DistanceTraveled() {
    }

    public DistanceTraveled(double radius, int revolutions) {
        this.radius = radius;
        this.revolutions = revolutions;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getRevolutions() {
        return revolutions;
    }

    public void setRevolutions(int revolutions) {
        this.revolutions = revolutions;
    }

    public double calculateDistance() {
        double circumference = 2 * Math.PI * radius; 
        return (circumference * revolutions) / 63360.0;
    }
    public void input(){
         radius = Validator.getDouble("What is the radius of your tires, in inches? ",
                            "Must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
         revolutions = Validator.getInt("How many revolutions did your car's tires make? ",
                            "Must be > 0", "Invalid!", 1, Integer.MAX_VALUE);
    }
}
