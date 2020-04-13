package Server.ServerModel.Registration;

import java.util.ArrayList;

public class CourseOffering {
    private int secNum;
    private int secCap;
    private Course theCourse;
    private ArrayList<Student> studentList;
    private ArrayList<Registration> offeringRegList;

    public CourseOffering(int sectionNumber, int sectionCap) {
        setSecNum(sectionNumber);
        setSecCap(sectionCap);
        offeringRegList = new ArrayList<Registration>();
        studentList = new ArrayList<Student>();
    }

    public void setSecCap(int sectionCap) {
        secCap = sectionCap;
    }

    public void setSecNum(int sectionNumber) {
        secNum = sectionNumber;
    }

    public int getSecNum() {
        return secNum;
    }

    public int getSecCap() {
        return secCap;
    }

    public Course getTheCourse() {
        return theCourse;
    }

    public void setTheCourse(Course course) {
        theCourse = course;
    }

    @Override
    public String toString() {
        String st = "#";
        st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "#";
        st += "Section Num: " + getSecNum() + ", section cap: " + getSecCap() + "#";
        // We also want to print the names of all students in the section
        return st;
    }

    public void addRegistration(Registration registration, Student t) {
        offeringRegList.add(registration);
        studentList.add(t);
    }

    public int numberOfStudentsOffering() {
        return studentList.size();
    }

    public void removeMaxOffering() {
        offeringRegList.remove(offeringRegList.size() - 1);
        studentList.remove(studentList.size() - 1);
    }

}
