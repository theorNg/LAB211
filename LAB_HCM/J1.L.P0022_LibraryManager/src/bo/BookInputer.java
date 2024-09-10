/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Book;
import utils.Validator;

/**
 *
 * @author win
 */
public class BookInputer {
    private Book book;

    public BookInputer() {
        book = new Book();
    }
    
    public Book input(){
        book.setBookID(Validator.getString("Enter ID Book (Bxxxxx): ", 
                "Please enter format Bxxxxx( x is digit)", "[Bb]\\d{5}").toUpperCase());
        book.setBookTitle(Validator.getString("Enter title: ", "Can not empty!", "^(?!\\s*$).+"));
        book.setAuthor(Validator.getString("Enter author: ",  "Can not empty!", "^(?!\\s*$).+"));
        book.setPublicationYear(Validator.getInt("Enter publication year: ", "Just be 1970->2024", "Invalid!", 1970, 2024));
        book.setPublisher(Validator.getString("Enter publisher: ",  "Can not empty!", "^(?!\\s*$).+"));
        book.setISBN(Validator.getString("Enter ISBN: ",  "Just be 10 digit !", "[\\d]{10}"));
        book.setActive_book(true);
        return book;
    }
    
}
