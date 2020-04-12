package Server.ServerModel;

import java.util.ArrayList;

import Server.ServerModel.Registration.*;

public class Model {

	CourseCatalogue cat;

	public Model() {
		cat = new CourseCatalogue();
	}

	public String searchCourse(String courseName, int courseId) {
		Course courseSearched = cat.searchCat(courseName, courseId);
		if (courseSearched == null) {
			String serr = cat.displayCourseNotFoundError();
			return serr;
		}

		return ("Found course: " + courseSearched.toString());
	}

	public String addCourse(String courseName, int courseId) {
		cat.getCourseList().add(new Course(courseName, courseId));
		Course confirm = cat.searchCat(courseName, courseId);

		if (confirm == null) {
			return cat.displayCourseNotAddedError();
		}

		return cat.searchCat(courseName, courseId).toString() + " was successfully added!";
	}

	public String removeCourse(String courseName, int courseId) {
		cat.removeCourse(courseName, courseId);
		Course remove = cat.searchCat(courseName, courseId);
		if (remove != null) {
			return cat.displayCourseNotRemovedError();
		}

		return "Course: " + courseName + " was successfully removed!";
	}

	public String viewAllCourses() {
		return cat.toString(); // if null?
	}

	public String coursesTaken() {
		ArrayList<Course> courses = cat.coursesTaken();
		String takenCourses = "";
		for (Course c : courses) {
			takenCourses += c.toString();
		}
		return takenCourses;
	}

	/*
	 * public static void main(String[] args) { Model m = new Model();
	 * System.out.println("Model is initialized"); }
	 */
}