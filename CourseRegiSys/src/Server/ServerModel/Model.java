package Server.ServerModel;

import java.util.ArrayList;

import Server.ServerModel.Registration.*;

public class Model {

	CourseCatalogue cat; // the database catalogue, contains all courses avaliable
	CourseCatalogue stuCat; // student selected courses

	public Model() {
		cat = new CourseCatalogue();
		stuCat = new CourseCatalogue();
	}

	public String searchCourse(String courseName, int courseId) {
		Course courseSearched = cat.searchCat(courseName, courseId);

		if (courseSearched == null) { // if not found,display error
			String serr = cat.displayCourseNotFoundError();
			return serr;
		}

		return ("Found course: #" + courseSearched.toString());
	}

	public String addCourse(String courseName, int courseId) {
		Course confirm = stuCat.searchCat(courseName, courseId);

		if (confirm == null) {
			stuCat.getCourseList().add(new Course(courseName, courseId));
			addCourse(courseName, courseId);
		}

		return cat.searchCat(courseName, courseId).toString() + " was successfully added!";
	}

	public String removeCourse(String courseName, int courseId) {
		Course remove = stuCat.searchCat(courseName, courseId);

		if (remove != null) {
			stuCat.removeCourse(courseName, courseId);
			removeCourse(courseName, courseId);
		}

		return ("Course: " + courseName + " was successfully removed!");
	}

	public String viewAllCourses() {
		if (cat.getCourseList().isEmpty()) {
			return ("Critical Error: No DataBase Found!!");
		}
		return cat.toString(); // if null?
	}

	public String coursesTaken() {
		ArrayList<Course> courses = stuCat.coursesTaken();
		String takenCourses = "";

		for (Course c : courses) {
			takenCourses += c.toString() + "#";
		}

		return takenCourses;
	}

	/*
	 * public static void main(String[] args) { Model m = new Model();
	 * System.out.println("Model is initialized"); }
	 */
}