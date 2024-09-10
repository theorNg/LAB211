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
public class FuelEconomy {

    private double speed;
    private int minutes;
    private double gasoline;

    public FuelEconomy() {
    }

    public FuelEconomy(double speed, int minutes, double gasoline) {
        this.speed = speed;
        this.minutes = minutes;
        this.gasoline = gasoline;
    }

    public double calculateFuelEconomy() {
        double distance = speed * (minutes / 60.0);
        return distance / gasoline;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public double getGasoline() {
        return gasoline;
    }

    public void setGasoline(double gasoline) {
        this.gasoline = gasoline;
    }

    public void input() {
        minutes = Validator.getInt("How many minutes did you drive? ",
                "Must be > 0", "Invalid!", 1, Integer.MAX_VALUE);
        speed = Validator.getDouble("What was the average speed of the car during that time? ",
                "Must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        gasoline = Validator.getDouble("How many gallons of gas did your car use? ",
                "Must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
    }
}
