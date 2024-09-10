/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import constant.Constant;
import controller.Bussiness_Products;
import controller.Validator;

/**
 *
 * @author ADMIN
 */
public class StoreManagementApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bussiness_Products product = new Bussiness_Products();
        product.readFile(Constant.FILE1,Constant.FILE2);
        while (true) {
            System.out.println("======Store Management at Convenience Store=====");
            System.out.println("1. Manage products");
            System.out.println("2. Manage Warehouse");
            System.out.println("3. Report");
            System.out.println("4. Store data to files");
            System.out.println("5. Close the application");
            System.out.println("===========================");
            int choice = Validator.getInt("Your choice: ", "Can't not found !"
                    + "Please enter (1-5]",
                    "Invalid!Enter digit (1-5)", 1, 5);
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("");
                        System.out.println("======Manage products======");
                        System.out.println("1.1 Add Products");
                        System.out.println("1.2 Update product information.");
                        System.out.println("1.3 Delete product.");
                        System.out.println("1.4 Show all product.");
                        System.out.println("1.5 Return Menu");
                        System.out.println("===========================");
                        int choice1 = Validator.getInt("Your choice: ",
                                "Can't not found !"
                                + "Please enter (1-5)",
                                "Invalid!Enter digit (1-5)", 1, 5);
                        switch (choice1) {
                            case 1:
                                product.addProduct();
                                break;
                            case 2:
                                product.updateProduct();
                                break;
                            case 3:
                                product.deleteProduct();
                                break;
                            case 4:
                                product.showProduct();
                                break;
                            case 5:
                                break;
                        }
                        if(choice1==5){
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("======Warehouse management======");
                        System.out.println("1. Create an import receipt");
                        System.out.println("2. Create an export receipt.");
                        System.out.println("3. Return Menu");
                        System.out.println("===========================");
                        int choice2 = Validator.getInt("Your choice: ", "Can't not found !"
                                + "Please enter (1-3)",
                                "Invalid!Enter digit (1-3)", 1, 3);
                        switch (choice2) {
                            case 1:
                                product.import_Receipt();
                                break;
                            case 2:
                                product.export_Receipt();
                                break;
                            case 3:
                                break;
                        }
                        break;
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("");
                        System.out.println("======Report products======");
                        System.out.println("3.1. Products that have expired");
                        System.out.println("3.2. The products that the store is selling");
                        System.out.println("3.3. Products that are running out "
                                + "of stock (sorted in ascending order)");
                        System.out.println("3.4. Import/export receipt of a product");
                        System.out.println("3.5. Return Menu");
                        System.out.println("===========================");
                        int choice3 = Validator.getInt("Your choice: ",
                                "Can't not found !"
                                + "Please enter (1-5)",
                                "Invalid!Enter digit (1-5)", 1, 5);
                        switch (choice3) {
                            case 1:
                                product.expriredProducts();
                                break;
                            case 2:
                                product.sellingProduct();
                                break;
                            case 3:
                                product.runningOutStock();
                                break;
                            case 4:
                                product.ImportExport_Product();
                                break;
                            case 5:
                                break;
                        }
                        break;
                    }
                    break;
                case 4:
                    System.out.println("4. Store data to files.");
                    product.saveFile(Constant.FILE1,Constant.FILE2);
                    System.err.println("Save hava done!");
                    break;
                case 5:
                    return;
            }
        }
    }
}
