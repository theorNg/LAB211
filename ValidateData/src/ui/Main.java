/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        // age >= 18 and <=50
        int age = Validator.getInt("Enter age: ", "Age must be >= 18 and <=50. Try again!!!",
                "Please enter integer number", 18, 50);
        System.out.println("Age: " + age);
        double salary = Validator.getDouble("Enter salary: ", "Please enter >0",
                "Please enter real number", Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.println("Salary: " + salary);
        String code = Validator.getString("Enter code: ", "Please enter format SExxxxxx ( x is digit) ", "SE\\d{6}");
        System.out.println("Code: "+code);
        //Nhap ngay sinh cua ban dinh dang ngay/thang/nam
        Calendar calendar = Calendar.getInstance();
        calendar.set(01, 01, 2000);
        Date date = Validator.getDate("Enter date: ", "Plese enter Date not exceed current Date!!",
                "Date format must be dd/MM/yyyy", "dd/MM/yyyy", calendar.getTime(), new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(date));
        
        
    }

}
