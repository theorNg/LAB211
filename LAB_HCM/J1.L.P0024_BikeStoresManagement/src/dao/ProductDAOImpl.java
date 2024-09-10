/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author win
 */
public class ProductDAOImpl implements ProductDAO {

    private List<Product> list;
    private String lastID;

    public ProductDAOImpl() {
        list = new ArrayList<>();
        lastID = "";
    }

    @Override
    public boolean addProduct(Product product) {
        if (list.isEmpty()) {
            lastID = "P001";
        } else {
            String lastIDProduct = list.get(list.size() - 1).getId();
            int number = Integer.parseInt(lastIDProduct.substring(1));
            lastID = String.format("P%03d", number + 1);
        }
        product.setId(lastID);
        return list.add(product);
    }

    @Override
    public Product getProductByID(String ID) {
        for (Product product : list) {
            if (product.getId().equalsIgnoreCase(ID)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return list;
    }

    @Override
    public void setList(List<Product> list) {
        this.list = list;
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : list) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public void updateProduct(Product product, String newName,
            String newBrandID, String newCategoryID,
            int newModelYear, int newListPrice) {
        if (!newName.isEmpty()) {
            product.setName(newName);
        }
        if (!newBrandID.isEmpty()) {
            product.setBrandId(newBrandID);
        }
        if (!newCategoryID.isEmpty()) {
            product.setCategoryId(newCategoryID);
        }
        if (newModelYear >= 2000 && newModelYear <= 2024) {
            product.setModelYear(newModelYear);
        }
        if (newListPrice > 0) {
            product.setListPrice(newListPrice);
        }
    }

    @Override
    public Product deleteProduct(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return list.remove(i);
            }
        }
        return null;
    }

    @Override
    public void saveProductsToFile(List<Product> products, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + ","
                        + product.getBrandId() + ","
                        + product.getCategoryId() + "," + product.getModelYear()
                        + "," + product.getListPrice());
                writer.newLine();
            }
            System.out.println("Products have been successfully saved to "+fileName);
        } catch (Exception e) {
            System.out.println("Error saving products to file: "+e.getMessage());
        }
    }

    @Override
    public List<Product> loadProductsFromFile(String fileName) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String id = parts[0];
                    String name = parts[1];
                    String brandId = parts[2];
                    String categoryId = parts[3];
                    int modelYear = Integer.parseInt(parts[4]);
                    int listPrice = Integer.parseInt(parts[5]);

                    Product product = new Product(id, name, brandId, categoryId, modelYear, listPrice);
                    products.add(product);
                }
            }
            System.out.println("Products have been successfully loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading products from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing product data: " + e.getMessage());
        }
        return products;
    }

}
