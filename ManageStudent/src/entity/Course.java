/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author win
 */
public enum Course {
    JAVA("JAVA"),
    DOT_NET(".NET"),
    C_CPP("C/C++");
    private String language;

    Course(String language) {
        this.language = language;
    }

    public static Course getCourse(int type) {
        switch (type) {
            case 1:
                return JAVA;
            case 2:
                return DOT_NET;
            case 3:
                return C_CPP;
            default:
                throw new AssertionError();

        }
    }

    public String getLanguage() {
        return language;
    }
}
