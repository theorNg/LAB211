/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Task;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author win
 */
public class ManagerTask {
    private ArrayList<Task> list;
    private int lastID;

    public ManagerTask() {
        list= new ArrayList<>();
        lastID=0;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }
    
    private boolean isDuplicate(int taskTypeID, String requirementName, Date date, 
            double planFrom, double planTo, String assign,
            String reviewer){
        for (Task task : list) {
            if(task.getTaskTypeID()==taskTypeID&&
                    task.getRequirementName().equalsIgnoreCase(requirementName)&&
                    task.getDate().compareTo(date)==0&&
                    task.getPlanFrom()==planFrom&&
                    task.getPlanTo()==planTo&&
                    task.getAssign().equalsIgnoreCase(assign)&&
                    task.getReviewer().equalsIgnoreCase(reviewer)){
                return true;
            }
        }
        return false;
    }
    public int add(int taskTypeID, String requirementName, Date date, 
            double planFrom, double planTo, String assign,
            String reviewer) throws Exception{
        if(isDuplicate(taskTypeID, requirementName, date, planFrom, planTo, assign, reviewer)){
            throw new Exception("This task is existed!");
        }
        Task newTask= new Task(++lastID, taskTypeID, requirementName, date, planFrom, planTo, assign, reviewer);
        if(list.add(newTask)){
            return newTask.getId();
        }
        throw new Exception("Can not add!");
    }
    
    private int getIndexByID(int id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    
    public Task deleteTaskByID(int id) throws Exception{
        int index = getIndexByID(id);
        if(index==-1){
            throw new Exception("Task ID does not exist!");
        }
        return list.remove(index);
    }

    @Override
    public String toString() {
        if(list.isEmpty()){
            return null;
        }
        String str = String.format("%-5s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID","Name","Task Type","Date",
                "Time","Assignee","Reviewer");
        for (Task task : list) {
            str+=task.toString();
        }
        return str;
    }
    
    
}
