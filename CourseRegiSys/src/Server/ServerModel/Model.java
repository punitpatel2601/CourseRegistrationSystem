package Server.ServerModel;
import java.util.ArrayList;

import Server.ServerModel.Registration.*;

public class Model {

	CourseCatalogue cat;
	
	public Model() {
		cat = new CourseCatalogue();
	}
	
	public String searchCourse(String courseName, int courseId) {
		return cat.searchCat(courseName, courseId).toString();
	}
	
	public void addCourse(String courseName, int courseId) {
		cat.getCourseList().add(new Course(courseName, courseId));
	}
	
	public void removeCourse(String courseName, int courseId) {
		cat.removeCourse(courseName, courseId);
	}
	
	public String viewAllCourses() {
		return cat.toString();
	}
	
	public String coursesTaken() {
		ArrayList<Course> courses = cat.coursesTaken();
		String takenCourses = "";
		for (Course c : courses) {
			takenCourses += c.toString();
		}
		return takenCourses;
	}
	public static void main(String[] args) {
		Model m = new Model();
		System.out.println("Model is initialized");
		
	}
}