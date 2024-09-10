/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import constant.Constant;
import controller.Validator;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author win
 */
public class Schedule implements Serializable {

    private ArrayList<Staff> pilots = new ArrayList<>();
    private ArrayList<Staff> flightAttendants = new ArrayList<>();
    private ArrayList<Staff> groundStaffs = new ArrayList<>();
    private Flight forFlight;
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);

    public Schedule(Flight forFlight) {
        this.forFlight = forFlight;
    }

    public ArrayList<Staff> getPilots() {
        return pilots;
    }

    public void setPilots(ArrayList<Staff> pilots) {
        this.pilots = pilots;
    }

    public ArrayList<Staff> getFlightAttendants() {
        return flightAttendants;
    }

    public void setFlightAttendants(ArrayList<Staff> flightAttendants) {
        this.flightAttendants = flightAttendants;
    }

    public ArrayList<Staff> getGroundStaffs() {
        return groundStaffs;
    }

    public void setGroundStaffs(ArrayList<Staff> groundStaffs) {
        this.groundStaffs = groundStaffs;
    }

    public Flight getForFlight() {
        return forFlight;
    }

    public void setForFlight(Flight forFlight) {
        this.forFlight = forFlight;
    }

    public Staff getSchedule(String code) {
        for (int i = 0; i < pilots.size(); i++) {
            if (pilots.get(i).getId().equalsIgnoreCase(code)) {
                return pilots.get(i);
            }
        }
        for (int i = 0; i < flightAttendants.size(); i++) {
            if (flightAttendants.get(i).getId().equalsIgnoreCase(code)) {
                return flightAttendants.get(i);
            }
        }
        for (int i = 0; i < groundStaffs.size(); i++) {
            if (groundStaffs.get(i).getId().equalsIgnoreCase(code)) {
                return groundStaffs.get(i);
            }
        }
        return null;
    }

    public void input(ArrayList<Schedule> listSchedule, Flight flight) {
        int choice;
        String id;
        while (true) {
            System.out.println("-----------Add Schedule----------");
            while (true) {
                id = Validator.getString("Enter ID Staff:", " ID can not be blank",
                        "^(?!\\s*$).+");
                for (int i = 0; i < listSchedule.size(); i++) {
                    if (!listSchedule.get(i).forFlight.getNumber().equals(flight.getNumber())) {
                        if (dateFormat.format(listSchedule.get(i).getForFlight().getDepartureTime()).
                                equals(dateFormat.format(flight.getDepartureTime()))) {
                            if (listSchedule.get(i).getSchedule(id) != null) {
                                System.out.println("This ID Staff is existed in  Flight " + listSchedule.get(i).forFlight.getNumber() + " in same Departure Time ");
                                System.out.println("Add fail!");
                                return;
                            }
                        }
                    }

                }
                if (getSchedule(id) == null) {
                    break;
                } else {
                    System.err.println("This ID Staff is existed in this Flight");
                }
            }
            String name = Validator.getString("Enter name:", " Name can not be blank",
                    "^(?!\\s*$).+");
            System.out.println("1.pilots");
            System.out.println("2.flight Attendants");
            System.out.println("3.ground Staffs");
            System.out.println("4.Exit");
            choice = Validator.getInt("Enter your choice:", "Just be 1->4", "Invalid!", 1, 4);
            Staff staff;
            switch (choice) {
                case 1:
                    staff = new Staff(id, name, Constant.PILOT);
                    pilots.add(staff);
                    System.out.println("******Add pilot success***********");
                    break;
                case 2:
                    staff = new Staff(id, name, Constant.FLIGHT_ATTENDANT);
                    flightAttendants.add(staff);
                    System.out.println("**********Add flight attendant success*********");
                    break;
                case 3:
                    staff = new Staff(id, name, Constant.GROUND_STAFF);
                    groundStaffs.add(staff);
                    System.out.println("**********Add ground staff success*************");

                    break;
                case 4:
                    return;
            }
            String choose = Validator.getString("Do you continue?", "Just y or n", "[ynYN]");
            if (choose.equalsIgnoreCase("N")) {
                return;
            }
        }
    }

    public void delete() {
        String choice;
        String code = Validator.getString("Enter ID Staff:", " ID can not be blank",
                "^(?!\\s*$).+");
        for (int i = 0; i < pilots.size(); i++) {
            if (pilots.get(i).getId().equalsIgnoreCase(code)) {
                choice = Validator.getString("Do you want delete?(Y OR N)", "Just y or n", "[ynYN]");
                if (choice.equalsIgnoreCase("Y")) {
                    pilots.remove(i);
                    System.err.println("Delete sucess!");
                } else {
                    System.err.println("Delete fail!");
                }
                return;
            }
        }
        for (int i = 0; i < flightAttendants.size(); i++) {
            if (flightAttendants.get(i).getId().equalsIgnoreCase(code)) {
                choice = Validator.getString("Do you want delete?", "Just y or n", "[ynYN]");
                if (choice.equalsIgnoreCase("Y")) {
                    flightAttendants.remove(i);
                    System.err.println("Delete sucess!");
                } else {
                    System.err.println("Delete fail!");
                }
                return;
            }
        }
        for (int i = 0; i < groundStaffs.size(); i++) {
            if (groundStaffs.get(i).getId().equalsIgnoreCase(code)) {
                choice = Validator.getString("Do you want delete?(Y or N)", "Just y or n", "[ynYN]");
                if (choice.equalsIgnoreCase("Y")) {
                    groundStaffs.remove(i);
                    System.err.println("Delete sucess!");
                } else {
                    System.err.println("Delete fail!");
                }
                return;
            }
        }
        System.err.println("Not found ID Staff");
    }
}
