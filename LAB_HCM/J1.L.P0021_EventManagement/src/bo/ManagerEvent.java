/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Event;
import entity.Status;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author win
 */
public class ManagerEvent {

    ArrayList<Event> list;
    int lastID;

    public ManagerEvent() {
        list = new ArrayList<>();
        lastID = 0;
    }

    public ArrayList<Event> getList() {
        return list;
    }

    public void setList(ArrayList<Event> list) {
        this.list = list;
    }

    public boolean add(Event event) {
        event.setID(++lastID);
        return list.add(event);
    }

    public boolean isExistEvent(int ID) {
        for (Event event : list) {
            if (event.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Event> getListByLocation(String key) {
        ArrayList<Event> search = new ArrayList<>();
        for (Event event : list) {
            if (event.getLocation().toLowerCase().contains(key.toLowerCase())) {
                search.add(event);
            }
        }
        return search;
    }

    public Event getEventByID(int ID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == ID) {
                return list.get(i);
            }
        }
        return null;
    }

    public boolean update(int ID, String newName, Date newDate,
            String newLocation, int  newNumberOfAttend, Status  newStatus) {
        if (!isExistEvent(ID)) {
            return false;
        }
        Event oldEvent = getEventByID(ID);
        if (!newName.trim().isEmpty()) {
            oldEvent.setName(newName);
        }
        if (newDate!=null) {
           oldEvent.setDate(newDate);
        }
        if(!newLocation.trim().isEmpty()){
            oldEvent.setLocation(newLocation);
        }
        if(newNumberOfAttend>0){
            oldEvent.setNumberOfAttedees(newNumberOfAttend);
        }
        oldEvent.setStatus(newStatus);
        return true;
    }

    public boolean delete(int ID) {
        if (!isExistEvent(ID)) {
            return false;
        }
       return list.remove(getEventByID(ID));
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list event is empty!");
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < list.size(); i++) {
                oos.writeObject(list.get(i));
            }
            oos.close();
            fos.close();
        } catch (Exception e) {
            throw new Exception("Error to save " + file);
        }
    }

    public void readFile(String file) throws Exception {
        list.clear();
        lastID=0;
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    Event event = (Event) oos.readObject();
                    add(event);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("File " + file + " is empty!");
        }
    }

    public void sort() {
        Collections.sort(list);
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = String.format("|%10s|%15s|%15s|%15s|%20s|%15s|\n",
                "ID", "NAME", "DATE", "LOCATION", "NUMBER OF ATTEDEESS", "STATUS");
        for (Event event : list) {
            str += String.format("|%10d|%15s|%15s|%15s|%20s|%15s|\n", event.getID(), event.getName(),
                   dateFormat.format(event.getDate()), event.getLocation(), event.getNumberOfAttedees(), event.getStatus().getValue());
        }
        return str;
    }

}
