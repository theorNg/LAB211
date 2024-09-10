/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.ManagerSalaryHistory;
import bo.ManagerWorker;
import bo.WorkerInputer;
import entity.SalaryHistory;
import entity.SalaryStatus;
import entity.Worker;
import java.util.Date;
import utils.Validator;

/**
 *
 * @author win
 */
public class Controller {

    private ManagerSalaryHistory histories;
    private ManagerWorker workers;
    private WorkerInputer inputer;

    public Controller() {
        histories = new ManagerSalaryHistory();
        workers = new ManagerWorker();
    }

    public Worker addWorker() throws Exception {
        inputer = new WorkerInputer();
        Worker worker = inputer.input();
        if (workers.add(worker)) {
            return worker;
        }
        throw new Exception("Can not add worker!");
    }

    public SalaryHistory upSalary() throws Exception {
        String code = Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase();
        double amount = Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!",
                Double.MIN_VALUE, Double.MAX_VALUE);
        Worker worker = workers.changeSalary(SalaryStatus.UP, code, amount);
        SalaryHistory history = new SalaryHistory(worker, worker.getSalary(), SalaryStatus.UP, new Date());
        if (histories.addSalaryHistory(history)) {
            return history;
        }
        throw new Exception("Can not up salary!");
    }

    public SalaryHistory downSalary() throws Exception {
        String code = Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase();
        double amount = Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!",
                Double.MIN_VALUE, Double.MAX_VALUE);
        Worker worker = workers.changeSalary(SalaryStatus.DOWN, code, amount);
        SalaryHistory history = new SalaryHistory(worker, worker.getSalary(), SalaryStatus.DOWN, new Date());
        if (histories.addSalaryHistory(history)) {
            return history;
        }
        throw new Exception("Can not down salary!");
    }

    public void showHistory() throws Exception {
        String result = histories.toString();
        if (result == null) {
            throw new Exception("History Salary is empty!!");
        } else {
            System.out.println(result);
        }
    }

    public void saveWorkersToFile() {
        try {
            workers.saveFile("worker.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveHistoryToFile() {
        try {
            histories.saveFile("history.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFile() {
        try {
            workers.readFile("worker.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            histories.readFile("history.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
