/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constant.Constant;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import model.Daily_Products;
import model.Long_Products;
import model.Order;
import model.Products;

/**
 *
 * @author ADMIN
 */
public class Bussiness_Products implements Menu {

    SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
    List<Products> productList = new ArrayList();
    List<Order> orderList = new ArrayList();

    /**
     * Checks if a product with the given ID exists in the product list.
     *
     * @param id The ID to check.
     * @return The product if found, null otherwise.
     */
    public Products checkProduct(String id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equalsIgnoreCase(id)) {
                return productList.get(i);
            }
        }
        return null;
    }

    /**
     * Displays a formatted list of products.
     *
     * @param list The list of products to display.
     */
    public void showListProduct(List<Products> list) {
        if (list.isEmpty()) {
            System.err.println("The List is empty");
            return;
        }
        List<Products> dailyList = new ArrayList();
        List<Products> longList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Daily_Products) {
                dailyList.add(list.get(i));
            }
            if (list.get(i) instanceof Long_Products) {
                longList.add(list.get(i));
            }
        }
        if (!dailyList.isEmpty()) {
            System.out.println("                   DAILY PRODUCTS                           ");
            System.out.println("------------------------------------------------------------");
            System.out.printf("|%-15s|%-15s|%-10s|%-7s|%-7s|\n", "Product Code",
                    "Product Name", "Quantity", "Unit", "Size");
            System.out.println("------------------------------------------------------------");
            for (int i = 0; i < dailyList.size(); i++) {
                System.out.println(((Daily_Products) dailyList.
                        get(i)).toString());
            }
        }
        if (!longList.isEmpty()) {
            System.out.println("");
            System.out.println("                   LONG PRODUCTS                           ");
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("|%-15s|%-15s|%-10s|%-15s|%-15s|\n",
                    "Product Code", "Product Name", "Quantity",
                    "Production Date", "Expiration Date");
            System.out.println("----------------------------------------------------------------------------");
            for (int i = 0; i < longList.size(); i++) {
                System.out.println(((Long_Products) longList.get(i)).toString());
            }
        }
        System.out.println("");
    }

    /**
     * Allows the user to add a new product, either a daily product or a
     * long-lasting product. The user can continue adding multiple products of
     * the chosen type.
     */
    @Override
    public void addProduct() {
        while (true) {
            System.out.println("1. Adđ Daily Product");
            System.out.println("2. Add Long Products");
            System.out.println("3. Exit");
            int choice = Validator.getInt("Enter choice:",
                    "Invalid!Choose 1 to 3", "Enter a digit 1 to 3", 1, 3);
            String choose;
            switch (choice) {
                case 1:
                    while (true) {
                        String id = Validator.getString("Enter product Id:", "Wrong fomat Id! ", "^(?!\\s*$).+");
                        if (checkProduct(id) != null) {
                            System.err.println("ID products is areadly input !");
                            continue;
                        }
                        String productName = Validator.getString("Enter product name:", "Invalid!", "^(?!\\s*$).+");
                        int quantity = 0;
                        String unit = Validator.getString("Enter Unit:", "Invalid", "^(?!\\s*$).+");
                        String size = Validator.getString("Enter Size:", "Invalid", "^(?!\\s*$).+");
                        Products dailyProduct = new Daily_Products(id, productName, quantity, unit, size);
                        productList.add(dailyProduct);
                        System.out.println("Add success ! Continue input Products");
                        System.out.println("----------CONTINUE ADD-----------");
                        choose = Validator.getString("You want to continue (Y/N): ", "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        String id = Validator.getString("Enter product Id:", "Wrong fomat Id! ", "^(?!\\s*$).+");
                        if (checkProduct(id) != null) {
                            System.err.println("ID products is areadly input !");
                            continue;
                        }
                        String productName = Validator.getString("Enter product name:", "Invalid!", "^(?!\\s*$).+");
                        int quantity = 0;
                        //DATE MAX CAN BE INPUT
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2100, Calendar.JANUARY, 01);
                        Date maxDate = calendar.getTime();
                        //DATE MIN CAN BE INPUT
                        calendar.set(2000, Calendar.JANUARY, 01);
                        Date minDate = calendar.getTime();
                        //CURRENT DATE
                        Date currentDate = new Date();
                        Date mdate, edate;
                        while (true) {
                            mdate = Validator.getDate("Enter manufactoring date: ",
                                    "mdate must after (" + dateFormat.format(minDate) + ") and not be exceed current date ("
                                    + dateFormat.format(currentDate) + ")", "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy",
                                    minDate, currentDate);
                            if (mdate != null) {
                                break;
                            } else {
                                System.out.println("Not Empty!");
                            }
                        }
                        while (true) {
                            edate = Validator.getDate("Enter expired date: ",
                                    "edate must after mdate (" + dateFormat.format(mdate) + ") and not be exceed ("
                                    + dateFormat.format(maxDate) + ")", "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy",
                                    mdate, maxDate);
                            if (edate != null) {
                                break;
                            } else {
                                System.out.println("Not Empy!");
                            }
                        }
                        Products longProduct = new Long_Products(id, productName, quantity, mdate, edate);
                        productList.add(longProduct);
                        System.out.println("Add success ! Continue input Products");
                        System.out.println("----------CONTINUE ADD-----------");
                        choose = Validator.getString("You want to continue (Y/N): ", "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                case 3:
                    return;
            }
        }
    }

    /**
     * User requires enter the productCode. If product code does not exist, the
     * notification "Product does not exist" message is shown. Otherwise, user
     * can input update information of product to update that product. If new
     * typed information is blank, then no information is changed.
     */
    @Override
    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        if (productList.isEmpty()) {
            System.err.println("You have not enter Information of Products");
            return;
        } else {
            String id = Validator.getString("Enter Id to update:", "ID products is unavailable!", "^(?!\\s*$).+");
            Products productUpdate = checkProduct(id);
            if (productUpdate == null) {
                System.err.println("Product does not exist");
                return;
            } else {
                List<Products> oldProduct = new ArrayList<>();
                oldProduct.add(checkProduct(id));
                showListProduct(oldProduct);
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getId().equalsIgnoreCase(productUpdate.getId())
                            && productUpdate instanceof Daily_Products) {
                        System.out.print("Enter NEW product name(Press blank(Enter) now to keep the old information):");
                        String newName = scanner.nextLine().trim();
                        String quanlityString = Validator.getString(
                                "Enter NEW quanlity(Press blank(Enter) now to keep the old information):",
                                "Please enter interger and must be >0",
                                "^([1-9][0-9]*)?");
                        System.out.print("Enter NEW unit(Press blank(Enter) now to keep the old information):");
                        String newUnit = scanner.nextLine().trim();
                        System.out.print("Enter NEW size(Press blank(Enter) now to keep the old information):");
                        String sizeString = scanner.nextLine().trim();
                        if (!newName.isEmpty()) {
                            productList.get(i).setProductName(newName);
                        }
                        if (!quanlityString.isEmpty()) {
                            productList.get(i).setInventory_Number(Integer.parseInt(quanlityString));
                        }
                        if (!newUnit.isEmpty()) {
                            ((Daily_Products) productList.get(i)).setUnit(newUnit);
                        }
                        if (!sizeString.isEmpty()) {
                            ((Daily_Products) productList.get(i)).setSize(sizeString);
                        }
                        System.out.println("Update Products success !");
                        break;
                    } else if (productList.get(i).getId().
                            equalsIgnoreCase(productUpdate.getId())
                            && productUpdate instanceof Long_Products) {
                        System.out.print("Enter NEW product name(Press blank(Enter) now to keep the old information):");
                        String newName = scanner.nextLine().trim();
                        String quanlityString = Validator.getString(
                                "Enter NEW quanlity(Press blank(Enter) now to keep the old information):",
                                "Please enter interger and must be >0",
                                "^([1-9][0-9]*)?");
                        Date newMdate, newEdate;
                        //DATE MAX CAN BE INPUT
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2100, Calendar.JANUARY, 01);
                        Date maxDate = calendar.getTime();
                        //DATE MIN CAN BE INPUT
                        calendar.set(2000, Calendar.JANUARY, 01);
                        Date minDate = calendar.getTime();
                        //CURRENT DATE
                        Date currentDate = new Date();
                        newMdate = Validator.getDate(
                                "Enter NEW manufactoring date(Press blank(Enter) now to keep the old information):",
                                "mdate must after (" + dateFormat.format(minDate) + ") and not be exceed current date ("
                                + dateFormat.format(currentDate) + ")",
                                "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy",
                                minDate, currentDate);
                        try {
                            newEdate = Validator.getDate(
                                    "Enter NEW expired date(Press blank(Enter) now to keep the old information):",
                                    "edate must after mdate (" + dateFormat.format(newMdate) + ") and not be exceed ("
                                    + dateFormat.format(maxDate) + ")",
                                    "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy",
                                    newMdate, maxDate);
                        } catch (NullPointerException e) {
                            newEdate = Validator.getDate(
                                    "Enter NEW expired date(Press blank(Enter) now to keep the old information):",
                                    "edate must after mdate ("
                                    + dateFormat.format(((Long_Products) productList.get(i)).getMdate())
                                    + ") and not be exceed (" + dateFormat.format(maxDate) + ")",
                                    "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy",
                                    ((Long_Products) productList.get(i)).getMdate(), maxDate);
                        }
                        if (!newName.isEmpty()) {
                            productList.get(i).setProductName(newName);
                        }
                        if (!quanlityString.isEmpty()) {
                            productList.get(i).setInventory_Number(Integer.parseInt(quanlityString));
                        }
                        if (newMdate != null) {
                            ((Long_Products) productList.get(i)).setMdate(newMdate);
                        }
                        if (newEdate != null) {
                            ((Long_Products) productList.get(i)).setEdate(newEdate);
                        }
                        System.out.println("Update Products success !");
                        break;
                    }
                }
            }
        }
    }

    /**
     * This method used to delete product by id
     */
    @Override
    public void deleteProduct() {
        if (productList.isEmpty()) {
            System.err.println("You have not enter Information of Products");
            return;
        } else {
            String id = Validator.getString("Enter Id to update:", "ID products is unavailable!", "^(?!\\s*$).+");
            if (checkProduct(id) == null) {
                System.err.println("Product does not exist");
                return;
            } else {
                List<Products> oldProduct = new ArrayList<>();
                oldProduct.add(checkProduct(id));
                showListProduct(oldProduct);
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getId().equalsIgnoreCase(id)) {
                        /*Note: the application should only remove the 
                        product from the store's list when the 
                        import / export information for 
                        this product has not been generated*/
                        for (int j = 0; j < orderList.size(); j++) {
                            if (orderList.get(j).getListProduct().
                                    containsKey(id.toLowerCase())) {
                                System.err.println("Can't remove!Because this id exited import/export receipt");
                                return;
                            }
                        }
                        String choice = Validator.getString("Are you sure delete?(Y or N)", "Just Y or N", "[YNyn]");
                        if (choice.equalsIgnoreCase("N")) {
                            System.err.println("DELETE FAIL!");
                            return;
                        }
                        productList.remove(i);
                        System.out.println("Product have been removed !");
                        break;
                    }
                }
            }
        }
    }

    /**
     * Displays the list of products.
     */
    @Override
    public void showProduct() {
        showListProduct(productList);
    }

    /**
     * Reads product and order data from specified files and populates the
     * product and order lists.
     *
     * @param file The path of the file containing product data.
     * @param file2 The path of the file containing order data.
     */
    public void readFile(String file, String file2) {
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    // Đọc một đối tượng và ép kiểu thành Person
                    Products product = (Products) oos.readObject();
                    productList.add(product);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception i) {
            System.out.println("Error to read File:" + file);
        }
        try {
            FileInputStream fos = new FileInputStream(file2);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    // Đọc một đối tượng và ép kiểu thành Order
                    Order x = (Order) oos.readObject();
                    Order order = new Order(x.getOrderCode(), x.getType(),
                            x.getOrderDate());
                    order.setListProduct(x.getListProduct());
                    orderList.add(order);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception i) {
            System.out.println("Error to read File:" + file2);
        }
    }
    
    /**
     * This product will reset all quantity product to 0
     */
    public void resetDailyProduct(){
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i) instanceof Daily_Products){
                productList.get(i).setInventory_Number(0);
            }
        }
    }
    
    /**
     * Saves product and order data to the specified files.
     *
     * @param file The path of the file to save product data.
     * @param file2 The path of the file to save order data.
     */
    @Override
    public void saveFile(String file, String file2) {
        System.out.println("Daily product can only be sold during the day");
        String choice = Validator.getString(
                "Do you want to save quantity daily product?", "Jusr y or n", "[ynYN]");
        if(choice.equalsIgnoreCase("N")){
            resetDailyProduct();
            System.out.println("RESET QUANTITY FOR ALL DAILLY PRODUCT TO 0");
        }
        // Save product data
        if (productList.isEmpty()) {
            System.err.println("Empty product list");
            return;
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (int i = 0; i < productList.size(); i++) {
                    oos.writeObject(productList.get(i));
                }
                oos.close();
                fos.close();
            } catch (IOException e) {
                System.err.println("Error saving product data to file: " + file);
            }
        }

        // Save order data
        if (orderList.isEmpty()) {
            System.err.println("Empty order list");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < orderList.size(); i++) {
                oos.writeObject(orderList.get(i));
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving order data to file: " + file2);

        }
    }

    /**
     * This method used to create import receipt
     */
    @Override
    public void import_Receipt() {
        String choose;
        String orderCode = String.format("%07d", Order.count);
        Date orderDate = new Date();
        Order importReceipt = new Order(orderCode, Constant.IMPORT, orderDate);
        HashMap<String, Integer> list = new HashMap<>();
        do {
            System.out.println("2.1. Import daily Product");
            System.out.println("2.2. Import Long Products");
            System.out.println("2.3. Exit");
            int choice = Validator.getInt("Enter choice:",
                    "Invalid!Choose 1 to 3", "Enter a digit 1 to 3", 1, 3);
            switch (choice) {
                case 1:
                    while (true) {
                        String id = Validator.getString("Enter product Id:",
                                "Wrong fomat Id! ", "^(?!\\s*$).+");
                        Integer value = 0;
                        if (checkProduct(id) instanceof Long_Products) {
                            System.err.println("this id product is exited Long Product");
                            break;
                        }
                        if (checkProduct(id) == null) {
                            String productName = Validator.getString("Enter product name:",
                                    "Invalid!", "^(?!\\s*$).+");
                            int quantity = Validator.getInt("Enter quantity import:",
                                    "Invalid", "Invalid", 0, Integer.MAX_VALUE);
                            String unit = Validator.getString("Enter Unit:",
                                    "Invalid", "^(?!\\s*$).+");
                            String size = Validator.getString("Enter Size:",
                                    "Invalid", "^(?!\\s*$).+");
                            Products dailyProduct = new Daily_Products(id, productName, quantity, unit, size);
                            productList.add(dailyProduct);
                            value = quantity;
                        } else {
                            System.err.println("----THIS PRODUCT EXITED IN WAREHOUSE----");
                            List<Products> oldProduct = new ArrayList<>();
                            oldProduct.add(checkProduct(id));
                            showListProduct(oldProduct);
                            int quantity = Validator.getInt("Enter quantity import:",
                                    "Invalid", "Invalid", 0, Integer.MAX_VALUE);
                            for (int i = 0; i < productList.size(); i++) {
                                if (productList.get(i).getId().equalsIgnoreCase(id)) {
                                    int newInventory = productList.get(i).
                                            getInventory_Number() + quantity;
                                    productList.get(i).
                                            setInventory_Number(newInventory);
                                }
                            }
                            value = quantity;
                        }
                        // check id is exited in list hashmap 
                        if (list.containsKey(id.toLowerCase())) {
                            int existingValue = list.get(id.toLowerCase());
                            int newValue = existingValue + value;
                            list.put(id.toLowerCase(), newValue);
                        } else {
                            list.put(id.toLowerCase(), value);
                        }
                        System.out.println("Add success ! Continue input Products");
                        choose = Validator.getString("You want to continue (Y/N): ",
                                "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    System.out.println("");
                    break;
                case 2:
                    while (true) {
                        String id = Validator.getString("Enter product Id:",
                                "Wrong fomat Id! ", "^(?!\\s*$).+");
                        Integer value = 0;
                        if (checkProduct(id) instanceof Daily_Products) {
                            System.err.println("this id product is exited Daily Product");
                            break;
                        }
                        if (checkProduct(id) == null) {
                            String productName = Validator.getString("Enter product name:",
                                    "Invalid!", "^(?!\\s*$).+");
                            int quantity = Validator.getInt("Enter quantity import:",
                                    "Invalid", "Invalid", 0, Integer.MAX_VALUE);
                            //DATE MAX CAN BE INPUT
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(2100, Calendar.JANUARY, 01);
                            Date maxDate = calendar.getTime();
                            //DATE MIN CAN BE INPUT
                            calendar.set(2000, Calendar.JANUARY, 01);
                            Date minDate = calendar.getTime();
                            //CURRENT DATE
                            Date currentDate = new Date();
                            Date mdate, edate;
                            while (true) {
                                mdate = Validator.getDate("Enter manufactoring date: ",
                                        "mdate must after (" + dateFormat.format(minDate)
                                        + ") and not be exceed current date ("
                                        + dateFormat.format(currentDate) + ")",
                                        "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy", minDate, currentDate);
                                if (mdate != null) {
                                    break;
                                } else {
                                    System.out.println("Not Empty!");
                                }
                            }
                            while (true) {
                                edate = Validator.getDate("Enter expired date: ",
                                        "edate must after mdate (" + dateFormat.format(mdate)
                                        + ") and not be exceed ("
                                        + dateFormat.format(maxDate) + ")",
                                        "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy", mdate, maxDate);
                                if (edate != null) {
                                    break;
                                } else {
                                    System.out.println("Not Empy!");
                                }
                            }
                            Products longProduct = new Long_Products(id, productName, quantity, mdate, edate);
                            productList.add(longProduct);
                            value = quantity;
                        } else {
                            System.err.println("----THIS PRODUCT EXITED IN WAREHOUSE----");
                            List<Products> oldProduct = new ArrayList<>();
                            oldProduct.add(checkProduct(id));
                            showListProduct(oldProduct);
                            int quantity = Validator.getInt("Enter quantity import:",
                                    "Invalid", "Invalid", 0, Integer.MAX_VALUE);
                            for (int i = 0; i < productList.size(); i++) {
                                if (productList.get(i).getId().
                                        equalsIgnoreCase(id)) {
                                    int newInventory = productList.get(i).
                                            getInventory_Number() + quantity;
                                    productList.get(i).
                                            setInventory_Number(newInventory);
                                }
                            }
                            value = quantity;
                        }
                        // check id is exited in list hashmap 
                        if (list.containsKey(id.toLowerCase())) {
                            int existingValue = list.get(id.toLowerCase());
                            int newValue = existingValue + value;
                            list.put(id.toLowerCase(), newValue);
                        } else {
                            list.put(id.toLowerCase(), value);
                        }
                        System.out.println("Add success ! Continue input Products");
                        choose = Validator.getString("You want to continue (Y/N): ",
                                "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    System.out.println("");
                    break;
                case 3:
                    importReceipt.setListProduct(list);
                    orderList.add(importReceipt);
                    System.err.println("CREATE SUCCESS IMPORT RECEIPT");
                    return;
            }
        } while (true);
    }

    /**
     * This method use to create Export Recept of Order list
     *
     */
    @Override
    public void export_Receipt() {
        String chooseYorN;
        HashMap<String, Integer> list = new HashMap<>();
        while (true) {
            String id = Validator.getString("Enter product Id:",
                    "Wrong fomat Id! ", "^(?!\\s*$).+");
            Integer value = 0;
            if (checkProduct(id) == null) {
                System.err.println("Not found Product! Create export_Receipt FAIL!");
                return;
            } else {
                int quantity = 0;
                List<Products> oldProduct = new ArrayList<>();
                oldProduct.add(checkProduct(id));
                showListProduct(oldProduct);
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getId().
                            equalsIgnoreCase(id)) {
                        quantity = Validator.getInt("Enter quantity export:",
                                "Please enter (0->" + productList.get(i).
                                        getInventory_Number() + ")", "Invalid", 0,
                                productList.get(i).getInventory_Number());
                        int newInventory = productList.get(i).
                                getInventory_Number() - quantity;
                        productList.get(i).
                                setInventory_Number(newInventory);
                    }
                }
                value = quantity;
            }
            // check id is exited in list hashmap 
            if (list.containsKey(id.toLowerCase())) {
                int existingValue = list.get(id.toLowerCase());
                int newValue = existingValue + value;
                list.put(id.toLowerCase(), newValue);
            } else {
                list.put(id.toLowerCase(), value);
            }
            System.out.println("Continue input Products");
            chooseYorN = Validator.getString("You want to continue (Y/N): ",
                    "Just Y or N", "[YNyn]");
            if (chooseYorN.equalsIgnoreCase("N")) {
                break;
            }
        }
        String orderCode = String.format("%07d", Order.count);
        Date orderDate = new Date();
        Order exportReceipt = new Order(orderCode, Constant.EXPORT, orderDate);
        exportReceipt.setListProduct(list);
        orderList.add(exportReceipt);
        System.err.println("CREATE SUCCESS EXPORT RECEIPT");
    }

    @Override
    /**
     * Identifies and displays the list of expired long-lasting products.
     * Expired products are those for which the expiration date has passed. Only
     * applicable for long-lasting products.
     */
    public void expriredProducts() {
        List<Products> listExprired = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) instanceof Long_Products) {
                if (((Long_Products) productList.get(i)).getEdate().
                        before(new Date())) {
                    listExprired.add(productList.get(i));
                }
            }
        }
        showListProduct(listExprired);
    }

    /**
     * Identifies and displays the list of products available for sale. For
     * long-lasting products, it considers those with a non-zero inventory and
     * expiration date not passed. For daily products, it considers those with a
     * non-zero inventory.
     */
    @Override
    public void sellingProduct() {
        List<Products> listSell = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) instanceof Long_Products
                    && productList.get(i).getInventory_Number() > 0) {
                if (!((Long_Products) productList.get(i)).getEdate().before(new Date())) {
                    listSell.add(productList.get(i));
                }
            } else if (productList.get(i) instanceof Daily_Products
                    && productList.get(i).getInventory_Number() > 0) {
                listSell.add(productList.get(i));
            }
        }

        // Display the list of products available for sale
        showListProduct(listSell);
    }

    /**
     * Identifies and displays the list of products that are running out of
     * stock (inventory less than 3).
     */
    @Override
    public void runningOutStock() {
        List<Products> listRunningOutStock = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getInventory_Number() < 3) {
                listRunningOutStock.add(productList.get(i));
            }
        }

        // Sort the list based on inventory number
        Collections.sort(listRunningOutStock);

        // Display the list of products that are running out of stock
        showListProduct(listRunningOutStock);
    }

    /**
     * Displays product details for a specific order based on the order code.
     *
     */
    @Override
    public void ImportExport_Product() {
        String orderCode = Validator.getString("Enter order code:",
                "Invalid!", "^(?!\\s*$).+");

        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderCode().equalsIgnoreCase(orderCode)) {
                System.out.println("ORDER CODE: " + orderList.get(i).getOrderCode());
                System.out.println("TYPE: " + orderList.get(i).getType());
                System.out.println("ORDER DATE: " + dateFormat.format(orderList.get(i).getOrderDate()));
                showListProduct(listProductInOrder(orderList.get(i).getListProduct()));
                return;
            }
        }
        System.err.println("NOT FOUND ORDER CODE!");
    }

    /**
     * Retrieves the list of products from the main product list based on the
     * products and quantities in the order.
     *
     * @param list A HashMap containing product IDs as keys and their
     * corresponding quantities as values.
     * @return A list of Products based on the products and quantities in the
     * order.
     */
    public List<Products> listProductInOrder(HashMap<String, Integer> list) {
        List<Products> listP = new ArrayList<>();
        List<Products> copyProductList = new ArrayList<>(productList);

        for (int i = 0; i < productList.size(); i++) {
            // Check if the product ID is present in the list hashmap
            if (list.containsKey(productList.get(i).getId().toLowerCase())) {
                int existingValue = list.get(productList.get(i).getId().toLowerCase());
                copyProductList.get(i).setInventory_Number(existingValue);
                listP.add(copyProductList.get(i));
            }
        }

        return listP;
    }

}
