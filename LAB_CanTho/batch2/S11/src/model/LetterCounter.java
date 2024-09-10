/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author win
 */
public class LetterCounter {
    private int[] letterCounts;

    public LetterCounter() {
        letterCounts= new int[26];
    }
    
    public void countLetters(String input){
        for (int i = 0; i < letterCounts.length; i++) {
            letterCounts[i]=0;
        }
        for (char c : input.toCharArray()) {
            if(Character.isLetter(c)){
                c = Character.toLowerCase(c);
                letterCounts[c-'a']++;
            }
        }
    }
    
    public void printLetterCounts(){
        for (int i = 0; i < letterCounts.length; i++) {
            if(letterCounts[i]>0){
                System.out.println((char)('a'+i)+": "+letterCounts[i]);
            }
        }
    }
    
}
