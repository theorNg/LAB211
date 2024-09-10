/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.EastAsiaCountriesInputer;
import bo.ManageEastAsiaCountries;
import entity.Country;
import entity.EastAsiaCountries;
import java.util.ArrayList;
import utils.Validator;

/**
 *
 * @author win
 */
public class Controller {

    private ManageEastAsiaCountries manager;
    private EastAsiaCountriesInputer inputer;

    public Controller() {
        manager = new ManageEastAsiaCountries();
    }

    public ManageEastAsiaCountries getManager() {
        return manager;
    }

    public void setManager(ManageEastAsiaCountries manager) {
        this.manager = manager;
    }

    public EastAsiaCountries add() throws Exception {
        inputer = new EastAsiaCountriesInputer();
        EastAsiaCountries country = inputer.input();
        if (manager.addCountryInformation(country)) {
            return country;
        }
        throw new Exception("Can not add!");
    }

    public void displayRecentlyEnteredInformation() throws Exception {
        EastAsiaCountries country = manager.getRecentlyEnteredInformation();
        ArrayList<EastAsiaCountries> result= new ArrayList<>();
        result.add(country);
        showInformationList(result);
    }

    public void displayInformationByName() throws Exception {
        String name = Validator.getString("Enter name of country: ",
                "Invalid!", "[A-Za-z\\s]+");
        ArrayList<EastAsiaCountries> result = manager.searchInformationByName(name);
        showInformationList(result);
    }

    public void displayInformationByAscendingOrder() {
        ArrayList<EastAsiaCountries> result = manager.sortInformationByAscendingOrder();
        showInformationList(result);
    }

    public void showInformationList(ArrayList<EastAsiaCountries> result) {
        System.out.printf("%-15s%-15s%-15s%-15s\n", "ID", "Name", "Total Area", "Terrain");
        for (EastAsiaCountries eastAsiaCountries : result) {
            ((Country) eastAsiaCountries).display();
        }
    }
}
