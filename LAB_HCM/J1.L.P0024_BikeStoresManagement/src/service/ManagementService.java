/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.BrandDAO;
import dao.BrandDAOImpl;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import java.util.Comparator;
import java.util.List;
import model.Brand;
import model.Category;
import model.Product;
import utils.Validator;

/**
 *
 * @author win
 */
public class ManagementService {

    private ProductDAO productDAO;
    private BrandDAO brandDAO;
    private CategoryDAO categoryDAO;
    private static final String PRODUCT_FILE = "Product.txt";

    public ManagementService() {
        productDAO = new ProductDAOImpl();
        brandDAO = new BrandDAOImpl();
        categoryDAO = new CategoryDAOImpl();
    }

    public void addProduct() {
        String name = Validator.getString("Enter name Product: ", "Can not empty!", "^(?!\\s*$).+");
        String brandId;
        do {
            brandId = Validator.getString("Enter Brand ID: ", "Must be Bxxx(x is digit)", "^B\\d{3}$");
            Brand brand = brandDAO.getBrandById(brandId);
            if (brand == null) {
                System.out.println("Can not found brand ! Try again");
            } else {
                System.out.println(brand);
                break;
            }
        } while (true);

        String categoryId;
        do {
            categoryId = Validator.getString("Enter Category ID: ", "Must be Cxxx(x is digit)", "^C\\d{3}$");
            Category category = categoryDAO.getCategoryById(categoryId);
            if (category == null) {
                System.out.println("Can not found category ! Try again");
            } else {
                System.out.println(category);
                break;
            }
        } while (true);
        int modelYear = Validator.getInt("Enter model year: ", "Must be 2000->2024",
                "Invalid!", 2000, 2024);
        int listPrice = Validator.getInt("Enter list price: ", "Must be >0", "Invalid!",
                1, Integer.MAX_VALUE);
        Product product = new Product(null, name, brandId, categoryId, modelYear, listPrice);
        if (productDAO.addProduct(product)) {
            System.out.println("Add success: " + product);
        } else {
            System.out.println("Add Fail!");
        }
    }

    public void searchProductByName() {
        String name = Validator.getString("Enter name Product: ", "Can not empty!", "^(?!\\s*$).+");
        List<Product> result = productDAO.searchProductsByName(name);
        if (result.isEmpty()) {
            System.out.println("Have no any Product");
        } else {
            result.sort(Comparator.comparingInt(Product::getModelYear));
            System.out.println("Product list sorted by model year accending:");
            for (Product product : result) {
                System.out.println(product);
            }
        }
    }

    public void updateProduct() {
        String id = Validator.getString("Enter ID Product: ", "Must be Pxxx(x is digit)", "^P\\d{3}$");
        Product product = productDAO.getProductByID(id);
        if (product == null) {
            System.out.println("Product does not exist");
        } else {
            System.out.println("Before update: " + product);
            String newName = Validator.getStringUpdate("Enter new Name: ");
            String newBrandId;
            do {
                newBrandId = Validator.getStringUpdateRegex("Enter Brand ID: ",
                        "Must be Bxxx(x is digit)", "^B\\d{3}$");
                //Neu thong tin nay trong nghia la nguoi ta muon giu lai thong tin cu
                if (newBrandId.isEmpty()) {
                    break;
                }
                Brand brand = brandDAO.getBrandById(newBrandId);
                if (brand == null) {
                    System.out.println("Can not found brand ! Try again");
                } else {
                    System.out.println(brand);
                    break;
                }
            } while (true);

            String newCategoryId;
            do {
                newCategoryId = Validator.getStringUpdateRegex("Enter Category ID: ",
                        "Must be Cxxx(x is digit)", "^C\\d{3}$");
                if (newCategoryId.isEmpty()) {
                    break;
                }
                Category category = categoryDAO.getCategoryById(newCategoryId);
                if (category == null) {
                    System.out.println("Can not found category ! Try again");
                } else {
                    System.out.println(category);
                    break;
                }
            } while (true);
            int newModelYear = Validator.getIntUpdate("Enter model year: ", "Must be 2000->2024",
                    "Invalid!", 2000, 2024);
            int newListPrice = Validator.getIntUpdate("Enter list price: ", "Must be >0", "Invalid!",
                    1, Integer.MAX_VALUE);
            productDAO.updateProduct(product, newName, newBrandId,
                    newCategoryId, newModelYear, newListPrice);
            System.out.println("After Update: " + product);
        }
    }

    public void deleteProduct() {
        String id = Validator.getString("Enter ID Product: ", "Must be Pxxx(x is digit)", "^P\\d{3}$");
        Product product = productDAO.deleteProduct(id);
        if (product == null) {
            System.out.println("Product does not exist");
        } else {
            System.out.println("Delete success: " + product);
        }
    }

    public void printFromFile() {
        List<Product> result = productDAO.loadProductsFromFile(PRODUCT_FILE);
        result.sort(Comparator.comparingInt(Product::getListPrice).reversed().
                thenComparing(Comparator.comparing(Product::getName)));
        for (Product product : result) {
            System.out.printf("%s,%s,%s,%s,%d,%d\n", product.getId(), product.getName(),
                    brandDAO.getBrandById(product.getBrandId()).getBrandName(),
                    categoryDAO.getCategoryById(product.getCategoryId()).getCategoryName(),
                    product.getModelYear(), product.getListPrice());
        }
    }

    public void run() {
        productDAO.setList(productDAO.loadProductsFromFile(PRODUCT_FILE));
        while (true) {
            int choice = Validator.getInt("=============MANAGEMENT BIKE===========\n"
                    + "1. Add a product.\n"
                    + "2. Search product by product name.\n"
                    + "3. Update product.\n"
                    + "4. Delete product.\n"
                    + "5. Save products to file.\n"
                    + "6. Print list products from the file.\n"
                    + "7. Quit.\n"
                    + "Enter your choice: ",
                    "Must be 1->7", "Invalid!", 1, 7);
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    searchProductByName();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    productDAO.saveProductsToFile(productDAO.getAllProduct(), PRODUCT_FILE);
                    break;
                case 6:
                    printFromFile();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
