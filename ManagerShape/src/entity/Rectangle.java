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
public class Rectangle extends Shape{
    private double width;
    private double length;

    public Rectangle() {
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    @Override
    public double getPerimeter() {
        return 2*(width+length);
    }

    @Override
    public double getArea() {
        return width*length;
    }

    @Override
    public void printResult() {
        System.out.println("-----Rectangle-----");
        System.out.println("Width: "+width);
        System.out.println("Length: "+length);
        System.out.println("Area: "+getArea());
        System.out.println("Perimeter: "+getPerimeter());
    }

    @Override
    public void input() {
        width= Validator.getDouble("Please input side width of Rectangle: ",
                "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        length= Validator.getDouble("Please input side length of Rectangle: ",
                "Please enter number >= "+width, "Invalid!", width, Double.MAX_VALUE);
    }
    
}
