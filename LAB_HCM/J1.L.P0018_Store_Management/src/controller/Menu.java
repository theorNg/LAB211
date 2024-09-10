/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author ADMIN
 */
public interface Menu {

    /**
     * Adds a product to the inventory.
     */
    public void addProduct();

    /**
     * Updates details of an existing product.
     */
    public void updateProduct();

    /**
     * Deletes a product from the inventory.
     */
    public void deleteProduct();

    /**
     * Displays the list of products.
     */
    public void showProduct();

    /**
     * Imports a receipt.
     */
    public void import_Receipt();

    /**
     * Exports a receipt.
     */
    public void export_Receipt();

    /**
     * Displays expired products.
     */
    public void expriredProducts();

    /**
     * Sells a product.
     */
    public void sellingProduct();

    /**
     * Displays products that are running out of stock.
     */
    public void runningOutStock();

    /**
     * Handles importing and exporting products.
     */
    public void ImportExport_Product();

    /**
     * Reads data from files.
     *
     * @param file The file to read data from.
     * @param file2 The second file if needed.
     */
    public void readFile(String file, String file2);

    /**
     * Saves data to files.
     *
     * @param file The file to save data to.
     * @param file2 The second file if needed.
     */
    public void saveFile(String file, String file2);
}
