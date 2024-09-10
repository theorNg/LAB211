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
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import utils.Validator;

public class Dictionary {

    private static final String INDEX_FILE_PREFIX = "_index.dat";
    private static final String MEANING_FILE_PREFIX = "_meaning.dat";
    private Map<String, String> dictionary;

    public Dictionary() {
        dictionary = new HashMap<>();
    }

    private String getIndexFileName(char c) {
        return c + INDEX_FILE_PREFIX;
    }

    private String getMeaningFileName(char c) {
        return c + MEANING_FILE_PREFIX;
    }

    private void loadDictionary(char c) {
        dictionary.clear();
        String indexFileName = getIndexFileName(c);
        String meaningFileName = getMeaningFileName(c);

        try {
            BufferedReader indexReader = new BufferedReader(new FileReader(indexFileName));
            BufferedReader meaningReader = new BufferedReader(new FileReader(meaningFileName));
            String word;
            while ((word = indexReader.readLine()) != null) {
                String meaning = meaningReader.readLine();
                if (meaning != null) {
                    dictionary.put(word, meaning);
                }
            }
        } catch (IOException e) {
            // If files do not exist, create new ones
            try {
                new File(indexFileName).createNewFile();
                new File(meaningFileName).createNewFile();
            } catch (IOException ex) {
                System.out.println("An error occurred while creating dictionary files.");
            }
        }
    }

    private void saveDictionary(char c) {
        String indexFileName = getIndexFileName(c);
        String meaningFileName = getMeaningFileName(c);

        try (BufferedWriter indexWriter = new BufferedWriter(new FileWriter(indexFileName));
                BufferedWriter meaningWriter = new BufferedWriter(new FileWriter(meaningFileName))) {
            for (String word : dictionary.keySet()) {
                indexWriter.write(word);
                indexWriter.newLine();
                meaningWriter.write(dictionary.get(word));
                meaningWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the dictionary.");
        }
    }

    public void createWord() {
        String word = Validator.getString("Enter a new word: ", "Just be alphabetic!", "[A-Za-z\\s]+").toLowerCase().trim();
        String meaning = Validator.getString("Meaning: ", "Can not empty!", "^(?!\\s*$).+");
        char firstChar = word.toLowerCase().charAt(0);
        loadDictionary(firstChar);
        if (!dictionary.containsKey(word)) {
            dictionary.put(word, meaning);
            saveDictionary(firstChar);
        } else {
            System.out.println("This word is existed ! Can not create");
        }
    }

    public void updateWord() {
        String word = Validator.getString("Enter a word to update: ", "Just be alphabetic!", "[A-Za-z\\s]+").toLowerCase().trim();
        String meaning = Validator.getString("Meaning: ", "Can not empty!", "^(?!\\s*$).+");
        char firstChar = word.toLowerCase().charAt(0);
        loadDictionary(firstChar);
        if (dictionary.containsKey(word)) {
            dictionary.put(word, meaning);
            saveDictionary(firstChar);
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public String lookupWord() {
        String word = Validator.getString("Enter a word to look up: ", "Can not empty!", "^(?!\\s*$).+").toLowerCase().trim();
        char firstChar = word.toLowerCase().charAt(0);
        loadDictionary(firstChar);
        return dictionary.getOrDefault(word, "Word not found in the dictionary.");
    }
}
