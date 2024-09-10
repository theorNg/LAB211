/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Loan;
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
public class ManagerLoan {

    Map<String, Loan> list;
    private String lastID;

    public ManagerLoan() {
        list = new HashMap<>();
    }

    public Map<String, Loan> getList() {
        return list;
    }

    public void setList(Map<String, Loan> list) {
        this.list = list;
    }

    public void add(Loan loan) throws Exception {
        if (list.isEmpty()) {
            lastID = "L00001";
        } else {
            lastID = getLastIDFromMap();
            lastID = generateNextID(lastID);
        }
        loan.setTransactionID(lastID);
        list.put(lastID, loan);
    }

    private String getLastIDFromMap() {
        List<String> keys = new ArrayList<>(list.keySet());
        Collections.sort(keys);
        return keys.get(keys.size() - 1);
    }

    private String generateNextID(String currentID) {
        String prefix = "L";
        int num = Integer.parseInt(currentID.substring(1));
        num++;
        return String.format("%s%05d", prefix, num);
    }

    public boolean update(Loan oldLoan, Loan newLoan) {
        if (!list.containsKey(oldLoan.getTransactionID())) {
            return false;
        }
        oldLoan.setBookID(newLoan.getBookID());
        oldLoan.setUserID(newLoan.getUserID());
        oldLoan.setBorrowDate(newLoan.getBorrowDate());
        oldLoan.setReturnDate(newLoan.getReturnDate());
        return true;
    }

    public List<Loan> sort() {
        List<Loan> sortedLoans = new ArrayList<>(list.values());
        Collections.sort(sortedLoans);
        return sortedLoans;
    }

    public String getStringTable(ManagerBook managerBook, ManagerUser managerUser) {
        if (list.isEmpty()) {
            return null;
        }
        List<Loan> sortedLoans = sort();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String title = String.format("|%15s|%15s|%15s|%15s|%15s|\n", "Transaction ID",
                "Book Name", "User Name", "Borrow Date", "Return Date");
        for (Loan loan : sortedLoans) {
            if (!managerBook.getList().get(loan.getBookID()).isActive_book()) {
                title += String.format("|%15s|%15s|%15s|%15s|%15s|\n",
                        loan.getTransactionID(),
                        managerBook.getList().get(loan.getBookID()).getBookTitle(),
                        managerUser.getList().get(loan.getUserID()).getName(),
                        formatDate.format(loan.getBorrowDate()),
                        formatDate.format(loan.getReturnDate()));
            }
        }
        return title;
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list loan is empty!");
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Loan loan : list.values()) {
                oos.writeObject(loan);
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
                    Loan loan = (Loan) oos.readObject();
                    add(loan);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("File " + file + " is empty!");
        }
    }
}
