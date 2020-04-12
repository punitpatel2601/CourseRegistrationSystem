package Server.ServerModel.Registration;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private int courseNum;
    private ArrayList<Course> preReq;
    private ArrayList<CourseOffering> offeringList;

    public Course(String courseName, int courseNum) {
        this.setCourseName(courseName);
        this.setCourseNum(courseNum);
        preReq = new ArrayList<Course>();
        offeringList = new ArrayList<CourseOffering>();
    }

    public void addOffering(CourseOffering offering) {
        if (offering != null && offering.getTheCourse() == null) {
            offering.setTheCourse(this);
            if (!offering.getTheCourse().getCourseName().equals(courseName)
                    || offering.getTheCourse().getCourseNum() != courseNum) {
                System.err.println("Error! This section belongs to another course!");
                return;
            }
            offeringList.add(offering);
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int checkStudentNumber() {
        int student = 0;
        for (int i = 0; i < offeringList.size(); i++) {
            student += offeringList.get(i).numberOfStudentsOffering();
        }
        return student;
    }

    public void conditionSNumThisCourse() {
        int studentNum = checkStudentNumber();
        if (studentNum < 8) {
            System.out.println("Total number of student in " + courseName + "   " + courseNum
                    + " is smaller than 8 , this course cannot be run ");
        }
    }

    @Override
    public String toString() {
        String st = "";
        st += getCourseName() + " " + getCourseNum();

        return st;
    }

    public CourseOffering getCourseOfferingAt(int i) {
        if (i < 0 || i >= offeringList.size()) {
            return null;
        } else {
            return offeringList.get(i);
        }
    }

    public void addPreReq(Course preReq) {
        this.preReq.add(preReq);
    }

    /*
     * public static void main(String[] args) { Course c = new Course("ENSF", 1);
     * System.out.println(c.getCourseName()); System.out.println(c.getCourseNum());
     * }
     */
}
