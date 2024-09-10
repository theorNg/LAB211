/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author win
 */
public enum Weekdays {
    Mon("Monday"), Tue("Tuesday"), Wed("Wednesday"),
    Thu("Thursday"), Fri("Friday"), Sat("Saturday"), Sun("Sunday");
    private String weekday;

    private Weekdays(String weekday) {
        this.weekday = weekday;
    }

    public static Weekdays getWeekdayByChoice(int choice) {
        switch (choice) {
            case 1:
                return Mon;
            case 2:
                return Tue;
            case 3:
                return Wed;
            case 4:
                return Thu;
            case 5:
                return Fri;
            case 6:
                return Sat;
            case 7:
                return Sun;
            default:
                throw new AssertionError();
        }
        
    }

    public String value() {
        return weekday;
    }
}
