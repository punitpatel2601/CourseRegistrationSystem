package Server.ServerModel.Registration;

import java.util.ArrayList;

/**
 * Contains logic and data-fields to create a CourseCatalogue list which
 * simulates a database of courses.
 * @author A. Mohar, T. Pritchard, P. Patel
 * @version 1.0
 * @since April 13, 2020
 */
public class CourseCatalogue {
    /**
     * the list of course available
     */
    private ArrayList<Course> courseList;

    /**
     * constructs a course list and initializes it to the data in text file(database)
     */
    public CourseCatalogue() {
        courseList = new ArrayList<Course>();
            DBManager db = new DBManager();
            courseList = db.getCourseList();
            /*
             * for (int i = 0; i < courseList.size(); i++) {
             * System.out.println(courseList.get(i).getCourseName()); }
             */
    }

    /**
     * Creates a new CourseOffering for a specified Course
     * @param c
     * @param secNum
     * @param secCap
     */
    public void createCourseOffering(Course c, int secNum, int secCap) {
        if (c != null) {
            CourseOffering theOffering = new CourseOffering(secNum, secCap);
            c.addOffering(theOffering);
        }
    }

    /**
     * searches catalogue for Course based on name and number
     * @param courseName
     * @param courseNum
     * @return
     */
    public Course searchCat(String courseName, int courseNum) {
        for (Course c : courseList) {
            if (courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum()) {
                return c;
            }
        }

        return null;
    }
    
    /**
     * Course not found error message
     * @return String error message
     */
    public String displayCourseNotFoundError() {
        return "Course/s was not found!# #It does not exists.";
    }

    /**
     * gets the course list
     * @return
     */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    @Override
    public String toString() {
        String st = "All courses in the catalogue: #";
        for (Course c : courseList) {
            st += "#" + c.getCourseName() + " " + c.getCourseNum(); // This line invokes the toString() method
        }
        return st;
    }

    /**
     * removes specified course from Course list
     * @param coursename
     * @param coursenum
     */
    public void removeCourse(String coursename, int coursenum) {
        String courseUpper = coursename.toUpperCase();
        int index = indexOfName(courseUpper, coursenum);

        courseList.remove(index);
    }

    /**
     * gets the index where specified Course is located within CourseCatalogue list
     * @param cname
     * @param cid
     * @return int value representing index
     */
    private int indexOfName(String cname, int cid) {
        int index = -1;
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseNum() == cid && courseList.get(i).getCourseName().equals(cname)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * prints all courses currently in CourseCatalogue
     */
    public void printAllCourses() {
        System.out.println();
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i).getCourseName() + " " + courseList.get(i).getCourseNum());
        }
    }

    /**
     * gets Course information based on name and id
     * @param n
     * @param id
     */
    public void getCourseDetails(String n, int id) {
        int i;
        for (i = 0; i < courseList.size(); i++) {
            if (n.equalsIgnoreCase(courseList.get(i).getCourseName()) && courseList.get(i).getCourseNum() == id) {
                System.out.println(
                        courseList.get(i).getCourseName() + "  " + courseList.get(i).getCourseNum() + " found\n");
                break;
            }
        }
        if (i == courseList.size()) {
            System.out.println(n + " " + id + " not found in the catalogue\n");
        }
    }

    public void displayToString() {
        System.out.println(toString());
    }
}
