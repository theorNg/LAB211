/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Classes;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author win
 */
public class ManagerClasses {

    private ArrayList<Classes> list;
    private String lastID;

    public ManagerClasses() {
        list = new ArrayList<>();
    }

    public ArrayList<Classes> getList() {
        return list;
    }

    public void setList(ArrayList<Classes> list) {
        this.list = list;
    }

    public boolean add(Classes classes) {
        if (list.isEmpty()) {
            lastID = "CLA00001";
        } else {
            String lastIDMember = list.get(list.size() - 1).getClassID();
            int number = Integer.parseInt(lastIDMember.substring(3));
            lastID = String.format("CLA%05d", number + 1);
        }
        classes.setClassID(lastID);
        return list.add(classes);
    }

    public Classes getClass(String keyID) {
        for (Classes classes : list) {
            if (classes.getClassID().equalsIgnoreCase(keyID)) {
               return classes;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i).toString();
            str += "\n";
        }
        return str;
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list class is empty!");
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
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    Classes member = (Classes) oos.readObject();
                    list.add(member);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("File " + file + " is empty!");
        }
    }
    
    private int getIndexClass(String ID){
        for (int i = 0; i < list.size(); i++) {
           if(list.get(i).getClassID().equalsIgnoreCase(ID)){
               return i;
           }
        }
        return -1;
    }
    public boolean remove(String ID) {
        int index = getIndexClass(ID);
        if (index == -1) {
            return false;
        }
        list.remove(index);
        return true;
    }

}
