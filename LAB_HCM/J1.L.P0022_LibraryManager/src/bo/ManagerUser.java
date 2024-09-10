/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Book;
import entity.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author win
 */
public class ManagerUser {

    Map<String, User> list;

    public ManagerUser() {
        list = new HashMap<>();
    }

    public Map<String, User> getList() {
        return list;
    }

    public void setList(Map<String, User> list) {
        this.list = list;
    }

    public boolean isExistUser(String ID) {
        for (User user : list.values()) {
            if (user.getUserID().equals(ID) && user.isActive_user()== true) {
                return true;
            }
        }
        return false;
    }

    public void add(User user) throws Exception {
        if (list.containsKey(user.getUserID())) {
            throw new Exception("ID User can not duplicate! Add fail");
        }
        list.put(user.getUserID(), user);
    }

    public boolean update(String userID, String newName, Date newDateOfBirth,
            String newPhone, String newEmail, String newActive_user) {
        if (!list.containsKey(userID)) {
            return false;
        }
        User user = list.get(userID);
        if (!newName.isEmpty()) {
            user.setName(newName);
        }
        if (newDateOfBirth != null) {
            user.setDateOfBirth(newDateOfBirth);
        }
        if (!newPhone.isEmpty()) {
            user.setPhone(newPhone);
        }
        if (!newEmail.isEmpty()) {
            user.setEmail(newEmail);
        }
        if (!newActive_user.isEmpty()) {
            if (newActive_user.equals("true")) {
                user.setActive_user(true);
            }
            if (newActive_user.equals("fasle")) {
                user.setActive_user(false);
            }
        }
        return true;
    }

    public boolean delete(String userID) {
        if (!list.containsKey(userID)) {
            return false;
        }
        list.get(userID).setActive_user(false);
        return true;
    }

    public List<User> sort() {
        List<User> sortedUsers = new ArrayList<>(list.values());
        Collections.sort(sortedUsers);
        return sortedUsers;
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        List<User> sortedUsers = sort();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String title = String.format("|%10s|%15s|%15s|%15s|%15s|\n", "User ID",
                "User Name", "Date of Birth", "Phone", "Email");
        for (User user : sortedUsers) {
            if (user.isActive_user()) {
                title += String.format("|%10s|%15s|%15s|%15s|%15s|\n",
                        user.getUserID(), user.getName(), formatDate.format(user.getDateOfBirth()),
                        user.getPhone(), user.getEmail());
            }
        }
        return title;
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list user is empty!");
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (User user : list.values()) {
                oos.writeObject(user);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new Exception("Error to save " + file);
        }
    }

    public void readFile(String file) throws Exception {
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    User user = (User) oos.readObject();
                    add(user);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("File " + file + " is empty!");
        }
    }

}
