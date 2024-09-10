/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Country;
import entity.EastAsiaCountries;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author win
 */
public class ManageEastAsiaCountries {

    private ArrayList<EastAsiaCountries> list;

    public ManageEastAsiaCountries() {
        list = new ArrayList<>();
    }

    public List<EastAsiaCountries> getList() {
        return list;
    }

    public void setList(ArrayList<EastAsiaCountries> list) {
        this.list = list;
    }

    private boolean isExisted(String id) {
        for (EastAsiaCountries eastAsiaCountries : list) {
            if (((Country) eastAsiaCountries).getCountryCode().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean addCountryInformation(EastAsiaCountries country) throws Exception {
        String id = ((Country) country).getCountryCode();
        if (isExisted(id)) {
            throw new Exception("This id is duplicate!");
        }
        return list.add(country);
    }

    public EastAsiaCountries getRecentlyEnteredInformation() throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list country is empty!");
        }
        return list.get(list.size() - 1);
    }

    public ArrayList<EastAsiaCountries> searchInformationByName(String name) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list country is empty!");
        }
        ArrayList<EastAsiaCountries> result = new ArrayList<>();
        for (EastAsiaCountries eastAsiaCountries : list) {
            if (((Country) eastAsiaCountries).getCountryName().toLowerCase().
                    contains(name.toLowerCase())) {
                result.add(eastAsiaCountries);
            }
        }
        return result;
    }

    public ArrayList<EastAsiaCountries> sortInformationByAscendingOrder() {
        ArrayList<EastAsiaCountries> listSort = new ArrayList<>(list);
        Collections.sort(listSort);
        return listSort;
    }
}
