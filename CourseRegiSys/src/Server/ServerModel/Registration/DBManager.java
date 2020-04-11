package Server.ServerModel.Registration;

import java.io.File;
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

	}

	public ArrayList<Course> readFromDataBase() {
		try {
			FileReader fr = new FileReader("courses.txt");
			scan = new Scanner(fr);
			String line;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				String[] inputs = line.split(" ");
				courseList.add(new Course(inputs[0], Integer.parseInt(inputs[1])));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Error");
		}
		/*
		 * for (int i = 0; i < courseList.size(); i++) {
		 * System.out.println(courseList.get(i).getCourseName()); }
		 */
		return courseList;
	}
}
