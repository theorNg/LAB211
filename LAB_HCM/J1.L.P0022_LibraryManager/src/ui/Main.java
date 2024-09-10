/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import entity.Book;
import entity.Loan;
import entity.User;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        Controller control = new Controller();
        control.readFileBook();
        control.readFileUser();
        control.readFileLoan();
        while (true) {
            int choice = Validator.getInt("===========LIBRARY MANAGEMENT===========\n"
                    + "1. Books Management\n"
                    + "2. Users Management\n"
                    + "3. Loans Management\n"
                    + "4. Report\n"
                    + "5. Exit\n"
                    + "Enter your choice: ", "Just 1 -> 5",
                    "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    int choice1;
                    do {
                        choice1 = Validator.getInt("===========BOOK MANAGEMENT===========\n"
                                + "1. Add a book\n"
                                + "2. Update Book Information\n"
                                + "3. Delete a Book\n"
                                + "4. Show All Books\n"
                                + "5. Return main menu\n"
                                + "Enter your choice: ", "Just 1 -> 5",
                                "Invalid!", 1, 5);
                        switch (choice1) {
                            case 1:
                                while (true) {
                                    try {
                                        Book newBook = control.addBook();
                                        System.out.println(newBook);
                                        control.saveFileBook();
                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    String choose = Validator.getString("Do you want continue? (Y or N): ",
                                            "Just Y or N ", "[YyNn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                try {
                                    Book bookUpdate = control.updateBook();
                                    System.out.println("After update: ");
                                    System.out.println(bookUpdate);
                                    control.saveFileBook();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    Book bookDelete = control.deteleBook();
                                    System.out.println("After delete: ");
                                    System.out.println(bookDelete);
                                    control.saveFileBook();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 4:
                                try {
                                    control.showListBook();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    } while (choice1 < 5);
                    break;
                case 2:
                    int choice2;
                    do {
                        choice2 = Validator.getInt("===========USER MANAGEMENT===========\n"
                                + "1. Add a User\n"
                                + "2. Update User Information\n"
                                + "3. Delete a User\n"
                                + "4. Show All Users\n"
                                + "5. Return main menu\n"
                                + "Enter your choice: ", "Just 1 -> 5",
                                "Invalid!", 1, 5);
                        switch (choice2) {
                            case 1:
                                while (true) {
                                    try {
                                        User newUser = control.addUser();
                                        System.out.println(newUser);
                                        control.saveFileUser();
                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    String choose = Validator.getString("Do you want continue? (Y or N): ",
                                            "Just Y or N ", "[YyNn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                try {
                                    User userUpdate = control.updateUser();
                                    System.out.println("After update: ");
                                    System.out.println(userUpdate);
                                    control.saveFileUser();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    User userDelete = control.deteleUser();
                                    System.out.println("After delete: ");
                                    System.out.println(userDelete);
                                    control.saveFileUser();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 4:
                                try {
                                    control.showListUser();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    } while (choice2 < 5);
                    break;
                case 3:

                    int choice3;
                    do {
                        choice3 = Validator.getInt("===========LOANS MANAGEMENT===========\n"
                                + "1. Allow users to borrow books. \n"
                                + "2. Update borrowing information.\n"
                                + "3. Display all currently borrowed books.\n"
                                + "4. Return main menu\n"
                                + "Enter your choice: ", "Just 1 -> 4",
                                "Invalid!", 1, 4);
                        switch (choice3) {
                            case 1:
                                while (true) {
                                    try {
                                        Loan newLoan = control.addLoan();
                                        System.out.println(newLoan);
                                        control.saveFileLoan();
                                        control.saveFileBook();
                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    String choose = Validator.getString("Do you want continue? (Y or N): ",
                                            "Just Y or N ", "[YyNn]");
                                    if (choose.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                try {
                                    Loan loanUpdate = control.updateLoan();
                                    System.out.println("After update: ");
                                    System.out.println(loanUpdate);
                                    control.saveFileLoan();
                                    control.saveFileBook();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    control.showListLoan();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    } while (choice3 < 4);
                    break;
                case 4:

                    int choice4;
                    do {
                        choice4 = Validator.getInt("===========REPORT===========\n"
                                + "1. Report on Borrowed Books  \n"
                                + "2. Report on Overdue Books\n"
                                + "3. Report on Total Borrowing Activities\n"
                                + "4. Return main menu\n"
                                + "Enter your choice: ", "Just 1 -> 4",
                                "Invalid!", 1, 4);
                        switch (choice4) {
                            case 1:
                                control.reportBorrowedBooks();
                                break;
                            case 2:
                                control.reportOverdueBooks();
                                break;
                            case 3:
                                control.reportBorrowingActivities();
                                break;
                        }
                    } while (choice4 < 4);
                    break;
                case 5:
                    System.exit(0);
            }

        }
    }
}
