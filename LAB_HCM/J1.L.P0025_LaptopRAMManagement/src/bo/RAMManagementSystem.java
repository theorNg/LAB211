/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.RAMItem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author win
 */
public class RAMManagementSystem {

    private List<RAMItem> list;
    private String lastID;

    public RAMManagementSystem() {
        list = new ArrayList<>();
        lastID = "";
    }

    public List<RAMItem> getList() {
        return list;
    }

    public void setList(List<RAMItem> list) {
        this.list = list;
    }

    public boolean addItem(RAMItem item) {
        if (list.isEmpty()) {
            lastID = "RAM" + item.getType() + "_1";
        } else {
            String lastIDProduct = list.get(list.size() - 1).getCode();
            int number = Integer.parseInt(lastIDProduct.split("_")[1]);
            number++;
            lastID = "RAM" + item.getType() + "_" + number;
        }
        item.setCode(lastID);
        return list.add(item);
    }

    public List<RAMItem> searchByType(String type) {
        List<RAMItem> result = new ArrayList<>();
        for (RAMItem ramItem : list) {
            if (ramItem.getType().toLowerCase().contains(type.toLowerCase())) {
                result.add(ramItem);
            }
        }
        return result;
    }

    public List<RAMItem> searchByBus(String bus) {
        List<RAMItem> result = new ArrayList<>();
        for (RAMItem ramItem : list) {
            if (ramItem.getBus().toLowerCase().contains(bus.toLowerCase())) {
                result.add(ramItem);
            }
        }
        return result;
    }

    public List<RAMItem> searchByBrand(String brand) {
        List<RAMItem> result = new ArrayList<>();
        for (RAMItem ramItem : list) {
            if (ramItem.getBrand().toLowerCase().contains(brand.toLowerCase())) {
                result.add(ramItem);
            }
        }
        return result;
    }

    public RAMItem getItemByCode(String code) {
        for (RAMItem ramItem : list) {
            if (ramItem.getCode().equalsIgnoreCase(code)) {
                return ramItem;
            }
        }
        return null;
    }

    public boolean updateItem(String code, String newType, String newBus, String newBrand, int newQuantity) {
        RAMItem item = getItemByCode(code);
        if (item == null) {
            return false;
        } else {
            if (!newType.isEmpty()) {
                item.setType(newType);
            }
            if (!newBus.isEmpty()) {
                item.setBus(newBus);
            }
            if (!newBrand.isEmpty()) {
                item.setBrand(newBrand);
            }
            if (newQuantity > 0) {
                item.setQuantity(newQuantity);
            }
        }
        return true;
    }

    public RAMItem deleteItem(String code) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equalsIgnoreCase(code)) {
                list.get(i).setActive(false);
                return list.get(i);
            }
        }
        return null;
    }

    public boolean isEmpty() {
        for (RAMItem ramItem : list) {
            if (ramItem.isActive() == true) {
                return false;
            }
        }
        return true;
    }

    public void sort() {
        Collections.sort(list);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return null;
        }
        String str = String.format("|%15s|%10s|%10s|%10s|%10s|%25s|%10s|\n", "Code", "Type", "Bus",
                "Brand", "Quantity", "Production_month_year", "active");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        for (RAMItem ramItem : list) {
            if (ramItem.isActive()) {
                str += String.format("|%15s|%10s|%10s|%10s|%10d|%25s|%10b|\n", ramItem.getCode(), ramItem.getType(), ramItem.getBus(),
                        ramItem.getBrand(), ramItem.getQuantity(),
                        formatDate.format(ramItem.getProduction_month_year()), ramItem.isActive());
            }
        }
        return str;
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list ram item is empty!");
        }
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        try (FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw)) {
            for (RAMItem item : list) {
                String itemData = String.format("%s,%s,%s,%s,%d,%s,%b",
                        item.getCode(), item.getType(), item.getBus(), item.getBrand(),
                        item.getQuantity(), formatDate.format(item.getProduction_month_year()), item.isActive()
                );
                bw.write(itemData);
                bw.newLine(); // Thêm dòng mới sau mỗi bản ghi
            }
        } catch (IOException e) {
            throw new Exception("Error to save " + file);
        }
    }

    public void readFile(String file) throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        try (FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Tách dữ liệu bằng dấu phẩy
                if (parts.length == 7) { // Đảm bảo đủ dữ liệu
                    String code = parts[0].trim();
                    String type = parts[1].trim();
                    String bus = parts[2].trim();
                    String brand = parts[3].trim();
                    int quantity = Integer.parseInt(parts[4].trim());
                    Date production_month_year = formatDate.parse(parts[5].trim());
                    boolean active = Boolean.parseBoolean(parts[6]);
                     RAMItem item = new RAMItem(code, type, bus, brand, quantity, production_month_year, active);
                    list.add(item); // Thêm sách vào danh sách
                }
            }
        } catch (IOException e) {
            throw new Exception("Error reading from " + file);
        }
    }

}
