/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Member;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author win
 */
public class ManagerMember {

    private ArrayList<Member> list;
    private String lastID;

    public ManagerMember() {
        list = new ArrayList<>();
    }

    public ArrayList<Member> getList() {
        return list;
    }

    public void setList(ArrayList<Member> list) {
        this.list = list;
    }

    public boolean add(Member member) {
        if (list.isEmpty()) {
            lastID = "GY00001";
        } else {
            String lastIDMember = list.get(list.size() - 1).getId();
            int number = Integer.parseInt(lastIDMember.substring(2));
            lastID = String.format("GY%05d", number + 1);
        }
        member.setId(lastID);
        return list.add(member);
    }

    public void sort() {
        Collections.sort(list);
    }

    public Member getMember(String ID) {
        for (Member member : list) {
            if (member.getId().equalsIgnoreCase(ID)) {
                return member;
            }
        }
        return null;
    }

    public ArrayList<Member> getListMemberByID(String keyID) {
        ArrayList<Member> listSearch = new ArrayList<>();
         for (Member member : list) {
            if (member.getId().toLowerCase().contains(keyID.toLowerCase())) {
                listSearch.add(member);
            }
        }
        return listSearch;
    }

    private int getIndexMember(String ID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    public boolean update(Member oldMember, Member newMember) {
        int index = getIndexMember(oldMember.getId());
        if (index == -1) {
            return false;
        }
        newMember.setId(oldMember.getId());
        list.set(index, newMember);
        return true;
    }

    public boolean remove(String ID) {
        int index = getIndexMember(ID);
        if (index == -1) {
            return false;
        }
        list.remove(index);
        return true;
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        String str = String.format("|%10s|%15s|%15s|%15s|%10s|\n", "ID", "Name", "Address", "Phone number", "Type");
        for (int i = 0; i < list.size(); i++) {
            str += String.format("|%10s|%15s|%15s|%15s|%10s|\n", list.get(i).getId(),
                    list.get(i).getName(), list.get(i).getAddress(),
                    list.get(i).getPhone(), list.get(i).getType());
        }
        return str;
    }

    public void saveFile(String file) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("This list member is empty!");
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
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    Member member = (Member) oos.readObject();
                    list.add(member);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("File " + file + " is empty!");
        }
    }

}
