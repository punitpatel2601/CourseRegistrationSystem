package Server.ServerModel.Registration;

import java.util.ArrayList;

/**
 * Provides data fields and methods to construct a Student object
 * @author A. Mohar, T. Pritchard, P. Patel
 * @version 1.0
 * @since April 13, 2020
 */
public class Student {
    /**
     * Student's name
     */
    private String studentName;

    /**
     * Student id
     */
    private int studentId;

    /**
     * list of registrations student has i.e. courses
     */
    private ArrayList<Registration> studentRegList;

    /**
     * Constructs a Student object with specified name and id
     * Initializes registrations list
     * @param studentName
     * @param studentId
     */
    public Student(String studentName, int studentId) {
        this.setStudentName(studentName);
        this.setStudentId(studentId);
        studentRegList = new ArrayList<Registration>();
    }

    /**
     * gets Student's name
     * @return
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * sets Student name
     * @param studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * gets Student's Id
     * @return
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * sets Student Id
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        String st = "Student Name: " + getStudentName() + "\n" + "Student Id: " + getStudentId() + "\n\n";
        return st;
    }

    /**
     * adds specified Registration object into Student's registration list
     * @param registration
     */
    public void addRegistration(Registration registration) {
        studentRegList.add(registration);
    }
    
    /**
     * Removes specified course from Student's registered courses
     * @param courseName
     * @return True if removal was successful, false otherwise
     */
    public boolean removeRegistration(String courseName) {
    	int count = 0;
    	boolean removed = false;
    	for (Registration r : studentRegList) {
    		if (r.getTheOffering().getTheCourse().getCourseName().contentEquals(courseName)) {
    			studentRegList.remove(count);
    			removed = true;
    			break;
    		}
    	}
    	return removed;
    }

    /**
     * gets list of courses student is in
     * @return
     */
    public ArrayList<Registration> getStudentRegList() {
        return studentRegList;
    }

    /**
     * Restricts each Student to be able to take a maximum of 6 courses
     */
    public boolean maxCourse() {
        if (this.studentRegList.size() >= 6)
            return false;
        return true;
    }

    /**
     * Removes the last course in registration list
     */
    public void removeMaxCourse() {
        studentRegList.remove(studentRegList.size() - 1);
    }
}
