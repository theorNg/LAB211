/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.SalaryStatus;
import entity.Worker;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win
 */
public class ManagerWorker {

    private List<Worker> list;

    public ManagerWorker() {
        this.list = new ArrayList<>();
    }

    public List<Worker> getList() {
        return list;
    }

    public void setList(List<Worker> list) {
        this.list = list;
    }

    private Worker getWorker(String id) {
        for (Worker workers : list) {
            if (workers.getId().equalsIgnoreCase(id)) {
                return workers;
            }
        }
        return null;
    }

    private boolean isExist(String id) {
        for (Worker workers : list) {
            if (workers.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Worker worker) throws Exception {
        if (isExist(worker.getId())) {
            throw new Exception("Worker with ID " + worker.getId() + " already exists.");
        }
        return list.add(worker);
    }

    public Worker changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is empty!Can not change salary");
        }
        if (!isExist(code)) {
            throw new Exception("Can not found code!");
        }
        if (amount <= 0) {
            throw new Exception("Amount of money must be > 0 ");
        }
        Worker worker = getWorker(code);
        switch (status) {
            case UP:
                worker.setSalary(worker.getSalary() + amount);
                break;
            case DOWN:
                if (worker.getSalary() - amount < 0) {
                    throw new Exception("Can not down " + amount);
                }
                worker.setSalary(worker.getSalary() - amount);
                break;
        }
        return worker;
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list worker is empty!");
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < list.size(); i++) {
                oos.writeObject(list.get(i));
            }
            oos.close();
            fos.close();
        } catch (Exception e) {
            throw new Exception("Error to save " + file);
        }
    }

    public void readFile(String file) throws Exception {
        try {
            FileInputStream fos= new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while(true){
                try {
                    Worker worker = (Worker)oos.readObject();
                    list.add(worker);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("File "+file +" is empty!");
        }
    }
}
