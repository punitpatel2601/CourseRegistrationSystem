package Server.ServerModel.Registration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DBManager {
	ArrayList<Course> courseList;

	public DBManager() {
		courseList = new ArrayList<Course>();
		readFromDataBase();
	}

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
			System.out.println("File Error");
		}
		/*
		 * for (int i = 0; i < courseList.size(); i++) {
		 * System.out.println(courseList.get(i).getCourseName()); }
		 */

	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}
}
