/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Brand;

/**
 *
 * @author win
 */
public interface BrandDAO {

    Brand getBrandById(String brandId);

    List<Brand> loadBrandsFromFile(String fileName);
}
