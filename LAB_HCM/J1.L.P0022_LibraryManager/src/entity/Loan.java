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
public class Loan implements Serializable, Comparable<Loan> {

    private String transactionID;
    private String bookID;
    private String userID;
    private Date borrowDate;
    private Date returnDate;
    
    public Loan(String transactionID, String bookID,
            String userID, Date borrowDate, Date returnDate) {
        this.transactionID = transactionID;
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    
    public Loan() {
    }
    
    public String getTransactionID() {
        return transactionID;
    }
    
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    
    public String getBookID() {
        return bookID;
    }
    
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public Date getBorrowDate() {
        return borrowDate;
    }
    
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    
    public Date getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        return "Loan{" + transactionID + ", " + bookID + ", " + userID + ", "
                + formatDate.format(borrowDate) + ", " + formatDate.format(returnDate) + '}';
    }
    
    @Override
    public int compareTo(Loan o) {
        return transactionID.compareTo(o.transactionID);
    }
    
}
