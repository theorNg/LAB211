/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author win
 */
public class SalaryHistory implements Comparable<SalaryHistory>,Serializable{
    private Worker worker;
    private double salaryUpdate;
    private SalaryStatus status;
    private Date date;

    public SalaryHistory() {
    }

    public SalaryHistory(Worker worker, double salaryUpdate, SalaryStatus status, Date date) {
        this.worker = worker;
        this.salaryUpdate = salaryUpdate;
        this.status = status;
        this.date = date;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public double getSalaryUpdate() {
        return salaryUpdate;
    }

    public void setSalaryUpdate(double salaryUpdate) {
        this.salaryUpdate = salaryUpdate;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public void setStatus(SalaryStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%7s%10s%10d%10.0f%10s%15s\n",worker.getId(),worker.getName(),
                worker.getAge(),salaryUpdate,getStatus(),dateFormat.format(date));
    }

    @Override
    public int compareTo(SalaryHistory o) {
        return worker.getId().compareTo(o.getWorker().getId());
    }
    
    
}
