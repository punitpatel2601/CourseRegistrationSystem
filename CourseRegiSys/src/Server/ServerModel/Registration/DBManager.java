package Server.ServerModel.Registration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulates a database, provides logic and data fields to load data in from a
 * text file containing course records
 * 
 * @author A. Mohar, T. Pritchard, P. Patel
 * @version 1.0
 * @since April 13, 2020
 */
public class DBManager {
	/**
	 * the list of courses in text file
	 */
	ArrayList<Course> courseList;

	/**
	 * Constructs a list of courses
	 */
	public DBManager() {
		courseList = new ArrayList<Course>();
		readFromDataBase();
	}

	/**
	 * Reads data from text file and inserts it accordingly into courseList
	 */
	public void readFromDataBase() {
		try {
			FileReader fr = new FileReader("courses.txt");
			Scanner scan = new Scanner(fr);
			String line;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				String[] inputs = line.split(" ");
				int courseNum = Integer.parseInt(inputs[1]);
				int secNum = Integer.parseInt(inputs[2]);
				int secCap = Integer.parseInt(inputs[3]);
				Course c = new Course(inputs[0], courseNum);
				for (int i = 1; i <= secNum; i++) {
					c.addOffering(new CourseOffering(i, secCap));
				}
				courseList.add(c);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Error: file not found!!");
		}
	}

	/**
	 * gets the course list
	 * 
	 * @return
	 */
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
}
