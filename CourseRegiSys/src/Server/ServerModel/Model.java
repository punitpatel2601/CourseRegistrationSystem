package Server.ServerModel;

import java.util.ArrayList;

import Server.ServerModel.Registration.*;

public class Model {

	private CourseCatalogue cat; // the database catalogue, contains all courses avaliable
	private ArrayList<Registration> stuRegs; // student registrations

	public Model() {
		cat = new CourseCatalogue();
		stuRegs = new ArrayList<Registration>();
	}

	public String searchCourse(String courseName, int courseId) {
		Course courseSearched = cat.searchCat(courseName, courseId);

		if (courseSearched == null) { // if not found,display error
			String serr = cat.displayCourseNotFoundError();
			return serr;
		}

		return ("Found course: #" + courseSearched.toString());
	}

	public String addCourse(String courseName, int courseId, int secNum) {
		Course c = cat.searchCat(courseName, courseId);
		Registration reg = new Registration();
		
		
		/*
		Course c = cat.searchCat(courseName, courseId);
		
		}

		confirm = stuCat.searchCat(courseName, courseId);
		if (confirm != null) {
			return stuCat.displayCourseNotAddedError();
		}

		stuCat.getCourseList().add(cat.searchCat(courseName, courseId));

		return (stuCat.searchCat(courseName, courseId).toString() + " was successfully added!# # #" + coursesTaken());
		*/
	}

	public String removeCourse(String courseName, int courseId) {
		Course remove = stuCat.searchCat(courseName, courseId);
		if (remove == null) {
			return stuCat.displayCourseNotRemovedError();
		}
		stuCat.removeCourse(courseName, courseId);

		remove = stuCat.searchCat(courseName, courseId);
		if (remove != null) {
			return stuCat.displayCourseNotRemovedError();
		}

		return ("Course: " + courseName + " was successfully removed!# # #" + coursesTaken());
	}

	public String viewAllCourses() {
		if (cat.getCourseList().isEmpty()) {
			return ("Critical Error: No DataBase Found!!");
		}
		return cat.toString(); // if null?
	}

	public String coursesTaken() {
		// ArrayList<Course> courses = stuCat.coursesTaken();

		if (stuCat.getCourseList().isEmpty()) {
			return stuCat.displayCourseNotFoundError();
		}

		String takenCourses = "Courses taken by the student are:# #";

		for (Course c : stuCat.getCourseList()) {
			takenCourses += c.toString();
		}

		return takenCourses;
	}

	/*
	 * public static void main(String[] args) { Model m = new Model();
	 * System.out.println("Model is initialized"); }
	 */
}