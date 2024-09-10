/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.LetterCounter;
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
        String str = Validator.getString("Enter a string: ",  "Can not empty!", "^(?!\\s*$).+");
        LetterCounter counter = new LetterCounter();
        counter.countLetters(str);
        counter.printLetterCounts();
    }
    
}
