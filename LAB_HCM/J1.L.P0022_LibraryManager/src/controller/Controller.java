/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.BookInputer;
import bo.LoanInputer;
import bo.ManagerBook;
import bo.ManagerLoan;
import bo.ManagerUser;
import bo.UserInputer;
import entity.Book;
import entity.Loan;
import entity.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import utils.Validator;

/**
 *
 * @author win
 */
public class Controller {

    private ManagerBook managerBook;
    private BookInputer bookInputer;
    private ManagerUser managerUser;
    private UserInputer userInputer;
    private ManagerLoan managerLoan;
    private LoanInputer inputerLoan;

    public Controller() {
        managerBook = new ManagerBook();
        managerUser = new ManagerUser();
        managerLoan = new ManagerLoan();
    }

    public Book addBook() throws Exception {
        bookInputer = new BookInputer();
        Book book = bookInputer.input();
        managerBook.add(book);
        return book;
    }

    public Book updateBook() throws Exception {
        String ID = Validator.getString("Enter ID Book Update (Bxxxxx): ",
                "Please enter format Bxxxxx( x is digit)", "[Bb]\\d{5}").toUpperCase();
        if (!managerBook.getList().containsKey(ID)) {
            throw new Exception("Can not found ID ! Update fail!");
        }
        System.out.println("Before Update: ");
        System.out.println(managerBook.getList().get(ID));
        String newTitle = Validator.getStringUpdate("Enter NEW Title Book"
                + "(Press blank(Enter) now to keep the old information):");
        String newAuthor = Validator.getStringUpdate("Enter NEW Author "
                + "(Press blank(Enter) now to keep the old information):");
        int newYearPublish = Validator.getIntUpdate("Enter NEW Year Publish "
                + "(Press blank(Enter) now to keep the old information):", "Just be 1970->2024",
                "Invalid!", 1970, 2024);
        String newPublisher = Validator.getStringUpdate("Enter NEW Publisher "
                + "(Press blank(Enter) now to keep the old information):");
        String newISBN = Validator.getStringUpdateRegex("Enter NEW ISBN "
                + "(Press blank(Enter) now to keep the old information):",
                "Just be 10 digit !", "[\\d]{10}");
        String activeBook = Validator.getStringUpdateRegex("Enter NEW activeBook(true/false) "
                + "(Press blank(Enter) now to keep the old information):",
                "Just true or false!", "^(true|false)$");
        if (managerBook.update(ID, newTitle, newAuthor, newYearPublish,
                newPublisher, newISBN, activeBook)) {
            return managerBook.getList().get(ID);
        } else {
            throw new Exception("Can not update!");
        }
    }

    public Book deteleBook() throws Exception {
        String ID = Validator.getString("Enter ID Book Delele (Bxxxxx): ",
                "Please enter format Bxxxxx( x is digit)", "[Bb]\\d{5}").toUpperCase();
        if (!managerBook.getList().containsKey(ID)) {
            throw new Exception("Can not found ID ! Delete fail!");
        }
        System.out.println(managerBook.getList().get(ID));
        String choose = Validator.getString("Do you want to delete this book ?(Y or N): ", "Just Y or N !", "[YNyn]");
        if (choose.equalsIgnoreCase("Y")) {
            if (managerBook.delete(ID)) {
                return managerBook.getList().get(ID);
            } else {
                throw new Exception("Delete fail!");
            }
        } else {
            throw new Exception("Delete fail!");
        }
    }

    public void showListBook() throws Exception {
        String result = managerBook.toString();
        if (result == null) {
            throw new Exception("This list is empty!");
        } else {
            System.out.println(result);
        }
    }

    public void saveFileBook() {
        try {
            managerBook.saveFile("Books.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFileBook() {
        try {
            managerBook.readFile("Books.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User addUser() throws Exception {
        userInputer = new UserInputer();
        User user = userInputer.input();
        managerUser.add(user);
        return user;
    }

    public User updateUser() throws Exception {
        String ID = Validator.getString("Enter ID User Update (Uxxxxx): ",
                "Please enter format Uxxxxx( x is digit)", "[Uu]\\d{5}").toUpperCase();
        if (!managerUser.getList().containsKey(ID)) {
            throw new Exception("Can not found ID ! Update fail!");
        }
        System.out.println("Before Update: ");
        System.out.println(managerUser.getList().get(ID));
        String newName = Validator.getStringUpdate("Enter NEW Name"
                + "(Press blank(Enter) now to keep the old information):");
        Date newDateOfBirth = Validator.getDateUpdate("Enter NEW Date Of Birth"
                + "(Press blank(Enter) now to keep the old information):",
                "Please enter format (dd/MM/yyyy)", "dd/MM/yyyy");
        String newPhone = Validator.getStringUpdateRegex("Enter NEW Phone "
                + "(Press blank(Enter) now to keep the old information):",
                "Please enter 0xxxxxxxxx(x is digit)!", "^0\\d{9}$");
        String newEmail = Validator.getStringUpdateRegex("Enter NEW email "
                + "(Press blank(Enter) now to keep the old information):",
                "Invalid!", "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        String newActiveUser = Validator.getStringUpdateRegex("Enter NEW activeUser(true/false) "
                + "(Press blank(Enter) now to keep the old information):",
                "Just true or false!", "^(true|false)$");
        if (managerUser.update(ID, newName, newDateOfBirth, newPhone, newEmail, newActiveUser)) {
            return managerUser.getList().get(ID);
        } else {
            throw new Exception("Can not update!");
        }
    }

    public User deteleUser() throws Exception {
        String ID = Validator.getString("Enter ID User Delete (Uxxxxx): ",
                "Please enter format Uxxxxx( x is digit)", "[Uu]\\d{5}").toUpperCase();
        if (!managerUser.getList().containsKey(ID)) {
            throw new Exception("Can not found ID ! Delete fail!");
        }
        System.out.println(managerUser.getList().get(ID));
        String choose = Validator.getString("Do you want to delete this user ?(Y or N): ", "Just Y or N !", "[YNyn]");
        if (choose.equalsIgnoreCase("Y")) {
            if (managerUser.delete(ID)) {
                return managerUser.getList().get(ID);
            } else {
                throw new Exception("Delete fail!");
            }
        } else {
            throw new Exception("Delete fail!");
        }
    }

    public void showListUser() throws Exception {
        String result = managerUser.toString();
        if (result == null) {
            throw new Exception("This list is empty!");
        } else {
            System.out.println(result);
        }
    }

    public void saveFileUser() {
        try {
            managerUser.saveFile("Users.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFileUser() {
        try {
            managerUser.readFile("Users.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Loan addLoan() throws Exception {
        inputerLoan = new LoanInputer();
        Loan loan = inputerLoan.input(managerBook, managerUser);
        managerLoan.add(loan);
        managerBook.delete(loan.getBookID());
        return loan;
    }

    public Loan updateLoan() throws Exception {
        String ID = Validator.getString("Enter ID Loan (Lxxxxx): ",
                "Please enter format Lxxxxx( x is digit)", "[Ll]\\d{5}").toUpperCase();
        if (!managerLoan.getList().containsKey(ID)) {
            throw new Exception("Can not found ID ! Update fail!");
        }
        inputerLoan = new LoanInputer();
        Loan oldLoan = managerLoan.getList().get(ID);
        //Chuyen active book cua quyen sach truoc khi cap nhat ve true 
        managerBook.getList().get(oldLoan.getBookID()).setActive_book(true);
        Loan newLoan = inputerLoan.input(managerBook, managerUser);
        if (managerLoan.update(oldLoan, newLoan)) {
            managerBook.delete(newLoan.getBookID());
            return oldLoan;
        } else {
            throw new Exception("Can not update!");
        }
    }

    public void showListLoan() throws Exception {
        String result = managerLoan.getStringTable(managerBook, managerUser);
        if (result == null) {
            throw new Exception("This list is empty!");
        } else {
            System.out.println(result);
        }
    }

    public void saveFileLoan() {
        try {
            managerLoan.saveFile("Loans.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFileLoan() {
        try {
            managerLoan.readFile("Loans.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

// Report on Borrowed Books
    public void reportBorrowedBooks() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Borrowed Books Report:");
        System.out.printf("%-10s %-15s %-10s %-15s %-15s\n", "Book ID", "Title", "User ID", "User Name", "Borrow Date");
        for (Map.Entry<String, Loan> entry : managerLoan.getList().entrySet()) {
            Loan loan = entry.getValue();
            Book book = managerBook.getList().get(loan.getBookID());
            User user = managerUser.getList().get(loan.getUserID());
            if (!book.isActive_book()) {
                System.out.printf("%-10s %-15s %-10s %-15s %-15s\n",
                        book.getBookID(), book.getBookTitle(), user.getUserID(), user.getName(), sdf.format(loan.getBorrowDate()));
            }
        }
    }

    // Report on Overdue Books
    public void reportOverdueBooks() {
        System.out.println("Overdue Books Report:");
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-10s %-15s %-10s %-15s %-15s %-15s\n", "Book ID", "Title", "User ID", "User Name", "Borrow Date", "Return Date");
        for (Map.Entry<String, Loan> entry : managerLoan.getList().entrySet()) {
            Loan loan = entry.getValue();
            Book book = managerBook.getList().get(loan.getBookID());
            User user = managerUser.getList().get(loan.getUserID());
            if (!book.isActive_book() && loan.getReturnDate().before(currentDate)) {
                System.out.printf("%-10s %-15s %-10s %-15s %-15s %-15s\n",
                        book.getBookID(), book.getBookTitle(), user.getUserID(), user.getName(), sdf.format(loan.getBorrowDate()), sdf.format(loan.getReturnDate()));
            }
        }
    }

    // Report on Total Borrowing Activities
    public void reportBorrowingActivities() {
        Date startDate, endDate;
        while (true) {
            startDate = Validator.getDate("Enter start date:", "Please enter (dd/MM/yyyy)", "dd/MM/yyyy");
            endDate = Validator.getDate("Enter end date:", "Please enter (dd/MM/yyyy)", "dd/MM/yyyy");
            if (startDate.compareTo(endDate) >= 0) {
                System.out.println("Start date must be before end date.");
            } else {
                break;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Borrowing Activities Report from " + sdf.format(startDate) + " to " + sdf.format(endDate) + ":");
        System.out.printf("%-10s %-15s %-10s %-15s %-15s %-15s %-10s\n", "Book ID", "Title", "User ID",
                "User Name", "Borrow Date", "Return Date","Overdue");
        for (Map.Entry<String, Loan> entry : managerLoan.getList().entrySet()) {
            Loan loan = entry.getValue();
            Book book = managerBook.getList().get(loan.getBookID());
            User user = managerUser.getList().get(loan.getUserID());
            boolean overdue= !book.isActive_book() && loan.getReturnDate().before(new Date());
            if (loan.getBorrowDate().after(startDate) && loan.getBorrowDate().before(endDate)) {
                System.out.printf("%-10s %-15s %-10s %-15s %-15s %-15s %-10s\n",
                        book.getBookID(), book.getBookTitle(), user.getUserID(), user.getName(),
                        sdf.format(loan.getBorrowDate()), sdf.format(loan.getReturnDate()),overdue);
            }
        }
    }
}
