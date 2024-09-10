/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Country;
import entity.EastAsiaCountries;
import utils.Validator;

/**
 *
 * @author win
 */
public class EastAsiaCountriesInputer {

    private EastAsiaCountries country;

    public EastAsiaCountriesInputer() {
        country = new Country();
    }

    public EastAsiaCountries input() {
        ((Country) country).setCountryCode(Validator.getString("Enter code of country: ",
                "Invalid!", "[A-Za-z]+").toUpperCase());
        ((Country) country).setCountryName(Validator.getString("Enter name of country: ",
                "Invalid!", "[A-Za-z\\s]+"));
        ((Country) country).setTotalArea(Validator.getFloat("Enter total Area: ",
                "Just be >0", "Invalid!", Float.MIN_VALUE, Float.MAX_VALUE));
        country.setCountryTerrain(Validator.getString("Enter terrain of country: ",
                "Invalid!", "[A-Za-z\\s]+"));
        return country;
    }
}
