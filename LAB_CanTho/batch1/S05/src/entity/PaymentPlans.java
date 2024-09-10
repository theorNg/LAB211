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
public class PaymentPlans {

    private double principal;
    private double annualInterestRate;
    private double monthlyPayment;

    public PaymentPlans(double principal, double annualInterestRate, double monthlyPayment) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.monthlyPayment = monthlyPayment;
    }

    public PaymentPlans() {
    }

    public void printPaymentPlan() {
        double monthlyRate = annualInterestRate / 100 / 12;
        int month = 1;
        double balance = principal;
        boolean isFeasible = true;

        System.out.println("Month\tPayment\t\tAmount Owed");
        while (balance > 0) {
            double interest = balance * monthlyRate; // Calculate monthly interest
            balance += interest; // Add interest to balance
            if (monthlyPayment <= interest && balance > monthlyPayment) { // Check if monthly payment is sufficient
                System.out.println("Error: The monthly payment is too small to ever pay off the loan.");
                isFeasible = false;
                break;
            }
            double payment = Math.min(monthlyPayment, balance); // Determine payment amount
            balance -= payment; // Subtract payment from balance
            System.out.printf("%d\t$%.2f\t\t$%.2f\n", month, payment, balance); // Print current month's payment and balance
            month++;
        }
        if (isFeasible) {
            System.out.println("Total expenditure: $" + (month - 1) * monthlyPayment);
        }
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void input() {
         principal = Validator.getDouble("What is the value left on the mortgage? ",
                "Just be >0 ", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
         annualInterestRate = Validator.getDouble("What is the annual interest rate of the the loan, in percent? ",
                "Must be >0 ", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
         monthlyPayment = Validator.getDouble("What is the monthly payment? ",
                "Must be >0 ", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
    }
}
