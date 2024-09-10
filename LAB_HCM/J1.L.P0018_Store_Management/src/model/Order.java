/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ADMIN
 */
public class Order implements Serializable {

    private String orderCode;  // The order code
    private HashMap<String, Integer> listProduct = new HashMap<>();  // The list of products in the order
    private String type;  // The type of the order
    private Date OrderDate;  // The date of the order
    public static int count = 1;  // Static count for generating order codes

    /**
     * Constructs an Order object with specified order code, type, and order
     * date.
     *
     * @param orderCode The order code.
     * @param           type The type of the order.
     * @param           OrderDate The date of the order.
     */
    public Order(String orderCode, String type, Date OrderDate) {
        if (count > 9999999) {
            System.out.println("MAX ORDER");
            return;
        }
        this.orderCode = orderCode;
        this.type = type;
        this.OrderDate = OrderDate;
        count++;
    }


    /**
     * Gets the order code.
     *
     * @return The order code.
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * Sets the order code.
     *
     * @param orderCode The order code to set.
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * Gets the list of products in the order.
     *
     * @return The list of products in the order.
     */
    public HashMap<String, Integer> getListProduct() {
        return listProduct;
    }

    /**
     * Sets the list of products in the order.
     *
     * @param listProduct The list of products to set.
     */
    public void setListProduct(HashMap<String, Integer> listProduct) {
        this.listProduct = listProduct;
    }

    /**
     * Gets the type of the order.
     *
     * @return The type of the order.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the order.
     *
     * @param type The type to set for the order.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the date of the order.
     *
     * @return The date of the order.
     */
    public Date getOrderDate() {
        return OrderDate;
    }

    /**
     * Sets the date of the order.
     *
     * @param OrderDate The date to set for the order.
     */
    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }
}
