/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Daily_Products extends Products {

    private String unit;  // The unit of measurement for the product
    private String size;  // The size of the product

    /**
     * Constructs a Daily_Products object with specified ID, product name,
     * quantity, unit, and size.
     *
     * @param id            The ID of the product.
     * @param productName   The name of the product.
     * @param unit          The unit of measurement for the product.
     * @param size          The size of the product.
     * @param inventory_Number The inventory number
     */
    public Daily_Products(String id, String productName, 
            int inventory_Number,String unit, String size) {    
        super(id, productName, inventory_Number);
        this.unit = unit;
        this.size = size;
    }

    /**
     * Gets the unit of measurement for the product.
     *
     * @return The unit of measurement.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit of measurement for the product.
     *
     * @param unit The unit of measurement to set.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets the size of the product.
     *
     * @return The size of the product.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the product.
     *
     * @param size The size to set for the product.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Returns a string representation of the daily product.
     *
     * @return A string representation of the product.
     */
    @Override
    public String toString() {
        return String.format("|%-15s|%-15s|%-10d|%-7s|%-7s|", getId(), getProductName(),
                getInventory_Number(), getUnit(), getSize());
    }
}
