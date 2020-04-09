package Server.ServerModel.Registration;

import java.util.ArrayList;

public class DBManager {
    ArrayList<Course> courseList;

    public DBManager() {
        courseList = new ArrayList<Course>();
    }

    @SuppressWarnings("rawtypes")
    public ArrayList readFromDataBase() {
        // TODO Auto-generated method stub
        courseList.add(new Course("ENGG", 233));
        courseList.add(new Course("ENSF", 409));
        courseList.add(new Course("PHYS", 259));
        return courseList;
    }

}
