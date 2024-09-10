/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/**
 *
 * @author win
 */
public class CategoryDAOImpl implements CategoryDAO {

    private List<Category> categories;
    private static final String CATEGORY_FILE = "Category.txt";

    public CategoryDAOImpl() {
        categories = new ArrayList<>();
        loadCategoriesFromFile(CATEGORY_FILE);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        for (Category category : categories) {
            if (category.getCategoryId().equalsIgnoreCase(categoryId)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public List<Category> loadCategoriesFromFile(String fileName) {
        categories.clear(); // Xóa dữ liệu cũ trước khi tải dữ liệu mới
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String categoryId = parts[0];
                    String categoryName = parts[1];

                    Category category = new Category(categoryId, categoryName);
                    categories.add(category);
                }
            }
            System.out.println("Categories have been successfully loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading categories from file: " + e.getMessage());
        }
        return categories;
    }

}
