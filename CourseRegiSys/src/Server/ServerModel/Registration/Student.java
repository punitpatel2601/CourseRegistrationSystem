package Server.ServerModel.Registration;

import java.util.ArrayList;

public class Student {
    private String studentName;
    private int studentId;
    private ArrayList<Registration> studentRegList;

    public Student(String studentName, int studentId) {
        this.setStudentName(studentName);
        this.setStudentId(studentId);
        studentRegList = new ArrayList<Registration>();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        String st = "Student Name: " + getStudentName() + "\n" + "Student Id: " + getStudentId() + "\n\n";
        return st;
    }

    public void addRegistration(Registration registration) {
        studentRegList.add(registration);
    }
    
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

    public ArrayList<Registration> getStudentRegList() {
        return studentRegList;
    }

    public boolean maxCourse() {
        if (this.studentRegList.size() >= 6)
            return false;
        return true;
    }

    public void removeMaxCourse() {
        studentRegList.remove(studentRegList.size() - 1);
    }
}
