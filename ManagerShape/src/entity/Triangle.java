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
public class Triangle extends Shape {

    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle() {
    }

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }

    @Override
    public double getPerimeter() {
        return (sideA + sideB + sideC);
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public void printResult() {
        System.out.println("-----Triangle-----");
        System.out.println("Side A: " + sideA);
        System.out.println("Side B: " + sideB);
        System.out.println("Side C: " + sideC);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }

    @Override
    public void input() {
        while (true) {
            sideA = Validator.getDouble("Please input side A of Triangle: ",
                    "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
            sideB = Validator.getDouble("Please input side B of Triangle: ",
                    "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
            sideC = Validator.getDouble("Please input side C of Triangle: ",
                    "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
            if(sideA+sideB>sideC&&sideA+sideC>sideB&&sideB+sideC>sideA){
                break;
            }else{
                System.out.println("Sum of two side must be more than side remaining! Try again");
            }
        }
    }

}
