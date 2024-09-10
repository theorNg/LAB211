/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Course;
import entity.Student;
import utils.Validator;

/**
 *
 * @author win
 */
public class StudentInputer {

    private Student student;

    public StudentInputer() {
        student = new Student();
    }

    public void inputID() {
        student.setId(Validator.getString("Enter id: ", "Invalid!", "[Ss]\\d+"));
    }

    public void inputStudentName() {
        student.setStudentName(Validator.getString("Enter name student: ", "Invalid!", "[A-Za-z\\s]+"));
    }

    public void inputSemester() {
        student.setSemester(Validator.getString("Enter Semester: ", "Invalid!", "[A-Za-z\\d]+"));
    }

    public void inputCourseName() {
        int choice = Validator.getInt("Only three courses:\n"
                + "1-Java\n"
                + "2-.Net\n"
                + "3-C/C++\n"
                + "Enter your choice:",
                "Please enter number 1->3", "Invalid", 1, 3);
        student.setCourseName(Course.getCourse(choice));
    }

    public Student getStudent() {
        return student;
    }
    
}
