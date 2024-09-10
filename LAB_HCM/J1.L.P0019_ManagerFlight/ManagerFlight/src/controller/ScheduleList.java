/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import model.Flight;
import model.Reservation;
import model.Schedule;

/**
 *
 * @author win
 */
public class ScheduleList implements Serializable {

    ArrayList<Schedule> listSchedule = new ArrayList<>();

    public void addSchedule(ArrayList<Flight> list) {
        String code;
        boolean isExist;
        Flight newFlight = null;
        while (true) {
            isExist = false;
            code = Validator.getString("Enter Flight number:",
                    "Must be follow as: Fxyzt ", "F\\d{4}");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getNumber().equalsIgnoreCase(code)) {
                    newFlight=list.get(i);
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                break;
            } else {
                System.out.println("The flight code invalid!");
            }
        }

        isExist = false;
        for (int i = 0; i < listSchedule.size(); i++) {
            if (listSchedule.get(i).getForFlight().getNumber().equalsIgnoreCase(code)) {
                //if exited schedule-> add schedule
                listSchedule.get(i).input(listSchedule,newFlight);
                isExist = true;
            }
        }
        //If not exits schedule -> create new schedule
        if (!isExist) {
            Schedule newSchedule = new Schedule(newFlight);
            newSchedule.input(listSchedule,newFlight);
            listSchedule.add(newSchedule);
        }
    }
    public void deleSchedule(ArrayList<Flight> list) {
        String code;
        boolean isExist;
        while (true) {
            isExist = false;
            code = Validator.getString("Enter Flight number:",
                    "Must be follow as: Fxyzt ", "F\\d{4}");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getNumber().equalsIgnoreCase(code)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                break;
            } else {
                System.out.println("The flight code invalid!");
            }
        }
        for (int i = 0; i < listSchedule.size(); i++) {
            if (listSchedule.get(i).getForFlight().getNumber().equalsIgnoreCase(code)) {
                listSchedule.get(i).delete();
            }
        }
    }
    
    public void clear(){
        listSchedule.clear();
    }
    public void showALLSchedule() {
        if(listSchedule.isEmpty()){
            System.err.println("This list is empty");
            return;
        }
        System.out.println("------------Schedule List--------------------");
        for (int i = 0; i < listSchedule.size(); i++) {
            System.out.println("Flight code: " + listSchedule.get(i).getForFlight().getNumber());
            if (!listSchedule.get(i).getPilots().isEmpty()) {
                System.out.println("****Pilots****");
                for (int j = 0; j < listSchedule.get(i).getPilots().size(); j++) {
                    System.out.println(listSchedule.get(i).getPilots().get(j).getName()+" - "+listSchedule.get(i).getPilots().get(j).getId());
                }
            }
            if (!listSchedule.get(i).getFlightAttendants().isEmpty()) {
                System.out.println("****Flight Attendants****");
                for (int j = 0; j < listSchedule.get(i).getFlightAttendants().size(); j++) {
                    System.out.println(listSchedule.get(i).getFlightAttendants().get(j).getName()+" - "+listSchedule.get(i).getFlightAttendants().get(j).getId());
                }
            }
            if (!listSchedule.get(i).getGroundStaffs().isEmpty()) {
                System.out.println("****Ground Staffs****");
                for (int j = 0; j < listSchedule.get(i).getGroundStaffs().size(); j++) {
                    System.out.println(listSchedule.get(i).getGroundStaffs().get(j).getName()+" - "+listSchedule.get(i).getGroundStaffs().get(j).getId());
                }
            }
            System.out.println("------------------------");
        }
    }

    private Schedule getSchedule(Flight flight) {
        for (int i = 0; i < listSchedule.size(); i++) {
            if (listSchedule.get(i).getForFlight().equals(flight)) {
                return listSchedule.get(i);
            }
        }
        return null;
    }

    public void saveToFile(String file) {
        try {
            ArrayList<Flight> listFlight;
            ArrayList<Reservation> listReservation;
            FileInputStream fosx = new FileInputStream(file);
            ObjectInputStream oosx = new ObjectInputStream(fosx);
            listFlight = (ArrayList<Flight>) oosx.readObject();
            listReservation = (ArrayList<Reservation>) oosx.readObject();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listFlight);
            oos.writeObject(listReservation);
            oos.writeObject(listSchedule);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.err.println("Error saving data to file: " + file);
        }
    }

    public void importFromFile(String file) {
        try {
            System.out.println("Loading file " + file + " to flight schedule...");
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            // Đọc danh sách từ tệp
            oos.readObject();
            oos.readObject();
            listSchedule = (ArrayList<Schedule>) oos.readObject();
            oos.close();
            fos.close();
        } catch (Exception i) {
            System.out.println(file + " empty to schedule");
            return;
        }
    }
}
