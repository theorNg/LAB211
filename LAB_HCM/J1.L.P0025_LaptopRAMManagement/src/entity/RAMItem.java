/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author win
 */
public class RAMItem implements Comparable<RAMItem> {

    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private Date production_month_year;
    private boolean active;

    public RAMItem() {
    }

    public RAMItem(String code, String type, String bus, String brand, int quantity, Date production_month_year, boolean active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getProduction_month_year() {
        return production_month_year;
    }

    public void setProduction_month_year(Date production_month_year) {
        this.production_month_year = production_month_year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        return "RAMItem{" + code + ", " + type + ", "
                + bus + ", " + brand + ", " + quantity + ", "
                + formatDate.format(production_month_year) + ", " + active + '}';
    }

    @Override
    public int compareTo(RAMItem o) {
        if (type.compareTo(o.type) == 0) {
            if (bus.compareTo(o.bus) == 0) {
                return brand.compareTo(o.brand);
            } else {
                return bus.compareTo(o.bus);
            }
        } else {
            return type.compareTo(o.type);
        }
    }

}
