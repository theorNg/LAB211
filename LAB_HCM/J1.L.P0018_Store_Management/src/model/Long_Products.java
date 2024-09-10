/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import constant.Constant;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Long_Products extends Products {

    private Date mdate;  // The manufacturing date
    private Date edate;  // The expiration date

    /**
     * Constructs a Long_Products object with specified ID, product name,
     * quantity, manufacturing date, and expiration date.
     *
     * @param id            The ID of the product.
     * @param productName   The name of the product.
     * @param mdate         The manufacturing date of the product.
     * @param edate         The expiration date of the product.
     * @param inventory_Number The inventory number
     */
    public Long_Products(String id, String productName,
            int inventory_Number,Date mdate, Date edate) {    
        super(id, productName, inventory_Number);
        this.mdate = mdate;
        this.edate = edate;
    }

    /**
     * Gets the manufacturing date of the product.
     *
     * @return The manufacturing date.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * Sets the manufacturing date of the product.
     *
     * @param mdate The manufacturing date to set.
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * Gets the expiration date of the product.
     *
     * @return The expiration date.
     */
    public Date getEdate() {
        return edate;
    }

    /**
     * Sets the expiration date of the product.
     *
     * @param edate The expiration date to set.
     */
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * Returns a string representation of the long-lasting product.
     *
     * @return A string representation of the product.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
        return String.format("|%-15s|%-15s|%-10d|%-15s|%-15s|", getId(), getProductName(),
                getInventory_Number(), dateFormat.format(mdate), dateFormat.format(edate));
    }
}
