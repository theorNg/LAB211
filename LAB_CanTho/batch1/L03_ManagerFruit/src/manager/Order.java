/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import infor.Fruit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import validate.Validator;

/**
 *
 * @author win
 */
public class Order {

    Hashtable<String, ArrayList<Fruit>> orders = new Hashtable<>();
    
        public void shopping(ListFruit listFruit) {
        ArrayList<Fruit> listOrder = new ArrayList<>();
        while (true) {
            listFruit.displayListFruit();
            if (listFruit.numberInOfStock() == 0) {
                break;
            }
            int item = Validator.getInt("Enter item(Enter 0 to exit!): ", "Error range!",
                    "Invalid!", 0, listFruit.numberInOfStock());
            if (item == 0) {
                System.out.println("See you again!");
                return;
            }
            Fruit fruit = listFruit.getFruit(item);
            System.out.println("You selected:" + fruit.getFruitName());
            int quantity = Validator.getInt("Enter quantity:", "Please enter number >0 and <=" + fruit.getQuantity(),
                    "Invalid!", 1, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            //Kiem tra xem cai danh sach mua da chua do minh muon mua hay khong,
            //Neu co roi thi tang so luong , chua co thi them vao listorder
            Fruit fruitInOrder = listFruit.checkFruitInOrder(listOrder, fruit.getFruitId());
            if (fruitInOrder != null) {
                fruitInOrder.setQuantity(fruitInOrder.getQuantity() + quantity);
            } else {
                listOrder.add(new Fruit(fruit.getFruitId(), fruit.getFruitName(),
                        fruit.getPrice(), quantity, fruit.getOrigin()));
            }
            System.out.println("Add success !");
            if (listFruit.numberInOfStock() == 0) {
                System.out.println("Out of stock!");
                break;
            }
            System.out.println("----------CONTINUE ADD-----------");
            String choose = Validator.getString("You want to continue (Y/N): ",
                    "Just Y or N", "[YNyn]");
            if (choose.equalsIgnoreCase("N")) {
                System.out.println("Thank you!");
                break;
            }
        }
        if (listOrder.isEmpty()) {
            System.out.println("No orders");
        } else {
            displayListOrder(listOrder);
            String name = inputCustomerName();
            orders.put(name, listOrder);
        }
    }

    public String inputCustomerName() {
        String name = Validator.getString("Enter name:", "Invalid!", "[A-Za-z\\s]+");
        int count = 0;
        for (String name_key : orders.keySet()) {
            String real_name = name_key.split("#")[0];
            if (name.equals(real_name)) {
                count++;
            }
        }
        return name + "#" + count;
    }

    public void displayListOrder(ArrayList<Fruit> listOrder) {
        double total = 0;

        System.out.println(""
                + "+-----+--------------------+----------+-----------+------------+\n"
                + "+No.  |     Product        |  Quantity| Price     |  Amount    |\n"
                + "+-----+--------------------+----------+-----------+------------+");
        for (int i = 0; i < listOrder.size(); i++) {
            System.out.printf("|%5d|    %-16s|  %8d| %10s|  %10s|\n", (i + 1), listOrder.get(i).getFruitName(),
                    listOrder.get(i).getQuantity(), "$"+String.format("%.1f", listOrder.get(i).getPrice()),
                    "$"+String.format("%.1f", listOrder.get(i).getPrice() * listOrder.get(i).getQuantity()));
            total += listOrder.get(i).getPrice() * listOrder.get(i).getQuantity();
        }
        System.out.println("+-----+--------------------+----------+-----------+------------+");
        System.out.printf("|\t\t\t\t\tTotal     |%12s|\n" ,"$"+String.format("%.1f", total));
        System.out.println("+-----+--------------------+----------+-----------+------------+");
    }

    public void viewOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders");
            return;
        }
        for (String name : orders.keySet()) {
            System.out.println("Customer: " + name.split("#")[0]);
            ArrayList<Fruit> listOrder = orders.get(name);
            displayListOrder(listOrder);
        }

    }
}
