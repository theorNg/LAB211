/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Product;

/**
 *
 * @author win
 */
public interface ProductDAO {
    boolean addProduct(Product product);
    Product getProductByID(String ID);
    List<Product> getAllProduct();
    void setList(List<Product> list);
    List<Product> searchProductsByName(String name);
    void updateProduct(Product product,String newName,String newBrandID,
            String newCategoryID,int newModelYear,int newListPrice);
    Product deleteProduct(String id);
    void saveProductsToFile(List<Product> products,String fileName);
    List<Product> loadProductsFromFile(String fileName);
}
