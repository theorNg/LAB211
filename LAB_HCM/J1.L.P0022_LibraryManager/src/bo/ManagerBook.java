/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Book;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author win
 */
public class ManagerBook {

    Map<String, Book> list;

    public ManagerBook() {
        list = new HashMap<>();
    }

    public Map<String, Book> getList() {
        return list;
    }

    public void setList(Map<String, Book> list) {
        this.list = list;
    }

    public boolean isDuplicateISBN(String ISBN) {
        for (Book book : list.values()) {
            if (book.getISBN().equals(ISBN)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistBook(String ID) {
        for (Book book : list.values()) {
            if (book.getBookID().equals(ID)&&book.isActive_book()==true) {
                return true;
            }
        }
        return false;
    }

    public void add(Book book) throws Exception {
        if (list.containsKey(book.getBookID())) {
            throw new Exception("ID Book can not duplicate! Add fail");
        }
        if (isDuplicateISBN(book.getISBN())) {
            throw new Exception("ISBN can not duplicate! Add fail");
        }
        list.put(book.getBookID(), book);
    }

    public boolean update(String idBook, String newBookTitle, String newAuthor, int newPublicationYear,
            String newPublisher, String newISBN, String newActive_book) {
        if (!list.containsKey(idBook)) {
            return false;
        }
        Book book = list.get(idBook);
        if (!newBookTitle.isEmpty()) {
            book.setBookTitle(newBookTitle);
        }
        if (!newAuthor.isEmpty()) {
            book.setAuthor(newAuthor);
        }
        if (newPublicationYear >= 1970 && newPublicationYear <= 2024) {
            book.setPublicationYear(newPublicationYear);
        }
        if (!newPublisher.isEmpty()) {
            book.setPublisher(newPublisher);
        }
        if (!newISBN.isEmpty()) {
            if (isDuplicateISBN(newISBN)) {
                System.out.println("This ISBN is Duplicate so not change ISBN!");
            } else {
                book.setISBN(newISBN);
            }
        }
        if (!newActive_book.isEmpty()) {
            if (newActive_book.equals("true")) {
                book.setActive_book(true);
            }
            if (newActive_book.equals("fasle")) {
                book.setActive_book(false);
            }
        }
        return true;
    }

    public boolean delete(String bookID) {
        if (!list.containsKey(bookID)) {
            return false;
        }
        list.get(bookID).setActive_book(false);
        return true;
    }

    public List<Book> sort() {
        List<Book> sortedBooks = new ArrayList<>(list.values());
        Collections.sort(sortedBooks);
        return sortedBooks;
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        List<Book> sortedBooks = sort();
        String title = String.format("|%10s|%15s|%15s|%15s|%15s|%15s|\n", "Book ID",
                "Book Title", "Author", "PublicationYear", "Publisher", "ISBN");
        for (Book book : sortedBooks) {
            if (book.isActive_book()) {
                title += String.format("|%10s|%15s|%15s|%15d|%15s|%15s|\n",
                        book.getBookID(), book.getBookTitle(), book.getAuthor(),
                        book.getPublicationYear(), book.getPublisher(), book.getISBN());
            }
        }
        return title;
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list book is empty!");
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Book book : list.values()) {
                oos.writeObject(book);
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
                    Book event = (Book) oos.readObject();
                    add(event);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("File " + file + " is empty!");
        }
    }

}
