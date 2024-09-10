/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Loan;
import java.util.Date;
import utils.Validator;

/**
 *
 * @author win
 */
public class LoanInputer {

    private Loan loan;

    public LoanInputer() {
        loan = new Loan();
    }

    public Loan input(ManagerBook managerBook, ManagerUser managerUser) throws Exception {
        //Nhap sach
        String str = managerBook.toString();
        if (str == null) {
            throw new Exception("List book is empty!!Please input book to libary");
        } else {
            System.out.println(str);
        }
        String bookID = Validator.getString("Enter ID Book (Bxxxxx): ",
                "Please enter format Bxxxxx( x is digit)", "[Bb]\\d{5}").toUpperCase();
        if (!managerBook.isExistBook(bookID)) {
            throw new Exception("This book ID is not exist in library!");
        } else {
            loan.setBookID(bookID);
        }

        //Nhap user
        str = managerUser.toString();
        if (str == null) {
            throw new Exception("List user is empty!!Please input user to libary");
        } else {
            System.out.println(str);
        }
        String userID = Validator.getString("Enter ID User (Uxxxxx): ",
                "Please enter format Uxxxxx( x is digit)", "[Uu]\\d{5}").toUpperCase();
        if (!managerUser.isExistUser(userID)) {
            throw new Exception("This user ID is not exist in library!");
        } else {
            loan.setUserID(userID);
        }
        while (true) {
            while (true) {
                loan.setBorrowDate(Validator.getDate("Enter borrow date:",
                        "Please enter (dd/MM/yyyy)", "dd/MM/yyyy"));
                if (loan.getBorrowDate().compareTo(new Date()) > 0) {
                    System.out.println("Borrow date must be <= current date");
                } else {
                    break;
                }
            }
            loan.setReturnDate(Validator.getDate("Enter return date:",
                    "Please enter (dd/MM/yyyy)", "dd/MM/yyyy"));
            if (loan.getBorrowDate().compareTo(loan.getReturnDate()) >= 0) {
                System.out.println("Return date must be > borrow date");
            } else {
                break;
            }
        }
        return loan;
    }
}
