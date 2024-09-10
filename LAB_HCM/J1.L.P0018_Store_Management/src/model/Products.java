/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Products implements Serializable, Comparable<Products> {

    private static final long serialVersionUID = 1L;
    private String id;  // The ID of the product
    private String productName;  // The name of the product
    private int inventory_Number;  // The inventory number of the product

    /**
     * Constructs a Products object with specified ID, product name, and
     * inventory number.
     *
     * @param id                The ID of the product.
     * @param productName       The name of the product.
     * @param inventory_Number  The inventory Number
     */
    public Products(String id, String productName, int inventory_Number) {
        this.id = id;
        this.productName = productName;
        this.inventory_Number=inventory_Number;
    }
    
    /**
     * Gets the ID of the product.
     *
     * @return The ID of the product.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID to set for the product.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     *
     * @return The name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName The name to set for the product.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the inventory number of the product.
     *
     * @return The inventory number of the product.
     */
    public int getInventory_Number() {
        return inventory_Number;
    }

    /**
     * Sets the inventory number of the product.
     *
     * @param inventory_Number The inventory number to set for the product.
     */
    public void setInventory_Number(int inventory_Number) {
        this.inventory_Number = inventory_Number;
    }

    /**
     * Returns a string representation of the product.
     *
     * @return A string representation of the product.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Product Name: " + productName;
    }

    /**
     * Compares this product with another product based on inventory number.
     *
     * @param t The product to compare to.
     * @return -1 if this product has a smaller inventory number, 1 if this
     * product has a larger inventory number, 0 if both products have the same
     * inventory number.
     */
    @Override
    public int compareTo(Products t) {
        if (t.inventory_Number > this.inventory_Number) {
            return -1;
        } else if (t.inventory_Number < this.inventory_Number) {
            return 1;
        } else {
            return 0;
        }
    }
}
