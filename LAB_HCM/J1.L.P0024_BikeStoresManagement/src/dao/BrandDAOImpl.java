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
import model.Brand;

/**
 *
 * @author win
 */
public class BrandDAOImpl implements BrandDAO{
    private List<Brand> brands;
    private static final String BRAND_FILE = "Brand.txt";
            
    public BrandDAOImpl() {
        brands = new ArrayList<>();
        loadBrandsFromFile(BRAND_FILE);
    }
    @Override
    public Brand getBrandById(String brandId) {
        for (Brand brand : brands) {
            if(brand.getBrandId().equalsIgnoreCase(brandId)){
                return brand;
            }
        }
        return null;
    }

    @Override
    public List<Brand> loadBrandsFromFile(String fileName) {
        brands.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine())!=null) {                
                String [] parts = line.split(",");
                if(parts.length ==3){
                    String brandID = parts[0].trim();
                    String brandName = parts[1].trim();
                    String origin = parts[2].trim();
                    Brand brand = new Brand(brandID, brandName, origin);
                    brands.add(brand);
                }
            }
            System.out.println("Brands have been successfully loaded from "+fileName);
        }catch(IOException e){
            System.out.println("Error loading from file: "+fileName);
        }
        return brands;
    }
    
}
