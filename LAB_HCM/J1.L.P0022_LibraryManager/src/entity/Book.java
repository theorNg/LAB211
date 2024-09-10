/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author win
 */
public class Book implements Serializable,Comparable<Book>{
    private String bookID;
    private String bookTitle;
    private String author;
    private int publicationYear;
    private String publisher;
    private String ISBN;
    private boolean active_book;

    public Book() {
    }

    public Book(String bookID, String bookTitle, String author, int publicationYear, String publisher, String ISBN, boolean active_book) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.active_book = active_book;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isActive_book() {
        return active_book;
    }

    public void setActive_book(boolean active_book) {
        this.active_book = active_book;
    }

    @Override
    public String toString() {
        return "Book{" + bookID + ", " + bookTitle + ", " + author + ", " +
                publicationYear + ", " + publisher + ", " + ISBN + ", " + active_book + '}';
    }

    @Override
    public int compareTo(Book o) {
        return bookID.compareTo(o.bookID);
    }
    
}
