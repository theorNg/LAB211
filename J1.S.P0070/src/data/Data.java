/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Account;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win
 */
public class Data {

    public static List<Account> listAccount = new ArrayList<Account>() {
        {
            add(new Account("1029817261", "tuan26062002"));
            add(new Account("1039817261", "minh12345677"));
            add(new Account("1049817261", "chung3245677"));
            add(new Account("1059817261", "dhdi129YIUQWIE"));
        }
    };
}
