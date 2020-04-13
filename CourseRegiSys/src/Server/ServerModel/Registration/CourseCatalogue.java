package Server.ServerModel.Registration;

import java.util.ArrayList;

public class CourseCatalogue {
    private ArrayList<Course> courseList;

    public CourseCatalogue() {
        courseList = new ArrayList<Course>();
            DBManager db = new DBManager();
            courseList = db.getCourseList();
            /*
             * for (int i = 0; i < courseList.size(); i++) {
             * System.out.println(courseList.get(i).getCourseName()); }
             */
    }

    public void createCourseOffering(Course c, int secNum, int secCap) {
        if (c != null) {
            CourseOffering theOffering = new CourseOffering(secNum, secCap);
            c.addOffering(theOffering);
        }
    }

    public Course searchCat(String courseName, int courseNum) {
        for (Course c : courseList) {
            if (courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum()) {
                return c;
            }
        }

        return null;
    }
    
    public String displayCourseNotFoundError() {
        return "Course/s was not found!# #It does not exists.";
    }

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

    public void removeCourse(String coursename, int coursenum) {
        String courseUpper = coursename.toUpperCase();
        int index = indexOfName(courseUpper, coursenum);

        courseList.remove(index);
    }

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

    public void printAllCourses() {
        System.out.println();
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i).getCourseName() + " " + courseList.get(i).getCourseNum());
        }
    }

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
