/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

/**
 *
 * @author win
 */
public class NumberToWords {
    private final String[] UNITSMAP = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    private final String[] TENSMAP = {
        "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private int number;
    private String word; 

    public NumberToWords(int number) {
        this.number = number;
        this.word = convertToWords();
    }


    private String convertToWords() {
        if (number == 0) {
            return "zero";
        }

        if (number < 0) {
            return "minus " + new NumberToWords(Math.abs(number)).getWord();
        }

        String words = "";
        int localNumber = number; 

        if (localNumber / 1000000 > 0) {
            words += new NumberToWords(localNumber / 1000000).getWord() + " million ";
            localNumber %= 1000000;
        }

        if (localNumber / 1000 > 0) {
            words += new NumberToWords(localNumber / 1000).getWord() + " thousand ";
            localNumber %= 1000;
        }

        if (localNumber / 100 > 0) {
            words += new NumberToWords(localNumber / 100).getWord() + " hundred ";
            localNumber %= 100;
        }

        if (localNumber > 0) {
            if (!words.isEmpty()) {
                words += "and ";
            }

            if (localNumber < 20) {
                words += UNITSMAP[localNumber];
            } else {
                words += TENSMAP[localNumber / 10];
                if (localNumber % 10 > 0) {
                    words += "-" + UNITSMAP[localNumber % 10];
                }
            }
        }

        return words.trim();
    }

    public String getWord() {
        return word;
    }
}
