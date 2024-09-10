/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Category;

/**
 *
 * @author win
 */
public interface CategoryDAO {
    Category getCategoryById(String id);
    List<Category> loadCategoriesFromFile(String fileName);
}
