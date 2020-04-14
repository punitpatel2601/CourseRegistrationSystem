package Server.ServerModel.Registration;

import java.util.ArrayList;

/**
 * Provides data fields and methods to create a Java data-type, representing a
 * Course
 * 
 * @author A. Mohar, T. Pritchard, P. Patel
 * @version 1.0
 * @since April 13, 2020
 */
public class Course {
    /**
     * name of course
     */
    private String courseName;

    /**
     * course id or number associated with it ie ENGG 200
     */
    private int courseNum;

    /**
     * the prerequisites required to take before this course
     */
    private ArrayList<Course> preReq;

    /**
     * course is offered in different sessions
     */
    private ArrayList<CourseOffering> offeringList;

    /**
     * constructs a course object and assings it preReqs and sessions
     * 
     * @param courseName
     * @param courseNum
     */
    public Course(String courseName, int courseNum) {
        this.setCourseName(courseName);
        this.setCourseNum(courseNum);
        preReq = new ArrayList<Course>();
        offeringList = new ArrayList<CourseOffering>();
    }

    /**
     * creates an offering for the course
     * 
     * @param offering
     */
    public void addOffering(CourseOffering offering) {
        if (offering != null && offering.getTheCourse() == null) {
            offering.setTheCourse(this);
            if (!offering.getTheCourse().getCourseName().equals(courseName)) {
                System.err.println("Error! This section belongs to another course!");
                return;
            }
            offeringList.add(offering);
        }
    }

    /**
     * getter function
     * 
     * @return course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * setter function, sets course name
     * 
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * getter function
     * 
     * @return course number
     */
    public int getCourseNum() {
        return courseNum;
    }

    /**
     * sets course number
     * 
     * @param courseNum
     */
    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    /**
     * checks the number of students registered in course
     * 
     * @return number of students
     */
    public int checkStudentNumber() {
        int student = 0;
        for (int i = 0; i < offeringList.size(); i++) {
            student += offeringList.get(i).numberOfStudentsOffering();
        }
        return student;
    }

    /**
     * checks if course can run as a course requires 9 students to run
     */
    public void conditionSNumThisCourse() {
        int studentNum = checkStudentNumber();
        if (studentNum < 8) {
            System.out.println("Total number of student in " + courseName + "   " + courseNum
                    + " is smaller than 8 , this course cannot be run ");
        }
    }

    @Override
    public String toString() {
        return (getCourseName() + " " + getCourseNum() + "(" + offeringList.size() + " Sections)#");
    }

    /**
     * gets course offering at specified index
     * 
     * @param i
     * @return offering at specified index
     */
    public CourseOffering getCourseOfferingAt(int i) {
        if (i < 0 || i >= offeringList.size()) {
            return null;
        } else {
            return offeringList.get(i);
        }
    }

    /**
     * adds a prerequisite to the course
     * 
     * @param preReq
     */
    public void addPreReq(Course preReq) {
        this.preReq.add(preReq);
    }

    /*
     * public static void main(String[] args) { Course c = new Course("ENSF", 1);
     * System.out.println(c.getCourseName()); System.out.println(c.getCourseNum());
     * }
     */
}
