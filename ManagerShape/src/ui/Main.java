/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Circle;
import entity.Rectangle;
import entity.Shape;
import entity.Triangle;

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
        System.out.println("=====Calculator Shape Program=====");
        Shape shapes[]={new Rectangle(),new Circle(),new Triangle()};
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].input();
        }
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].printResult();
        }
    }
    
}
