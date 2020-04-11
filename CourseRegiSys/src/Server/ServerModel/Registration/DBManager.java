package Server.ServerModel.Registration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DBManager {
    ArrayList<Course> courseList;
    Scanner scan;
    String fileName;

    public DBManager() {
        courseList = new ArrayList<Course>();
        fileName = "courses.txt";
    }

    public ArrayList<Course> readFromDataBase() {
    	try {
    		scan = new Scanner(new FileReader(fileName));
    		String line;
    		while(scan.hasNextLine()) {
    			line = scan.nextLine();
    			String[] inputs = line.split(" ");
    			courseList.add(new Course(inputs[0], Integer.parseInt(inputs[1])));
    		}
    	} catch (FileNotFoundException e) {
    		e.getStackTrace();
    	}
        return courseList;
	}
	
    
    public static void main(String[] args) {
		DBManager db = new DBManager();
		ArrayList<Course> cl = db.readFromDataBase();
		for (Course c : cl) {
			System.out.println(c);
		}
	}

}
