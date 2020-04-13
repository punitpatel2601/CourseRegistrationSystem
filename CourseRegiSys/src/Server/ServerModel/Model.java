package Server.ServerModel;

import Server.ServerModel.Registration.*;

public class Model {

	private CourseCatalogue cat; // the database catalogue, contains all courses avaliable
	private Student theStudent; // the student/user

	public Model() {
		cat = new CourseCatalogue();
	}
	
	public void initializeStudent(String studentName, int studentId) {
		theStudent = new Student(studentName, studentId);
	}

	public String searchCourse(String courseName, int courseId) {
		Course courseSearched = cat.searchCat(courseName, courseId);

		if (courseSearched == null) { // if not found,display error
			String serr = cat.displayCourseNotFoundError();
			return serr;
		}

		return ("Found course: " + courseSearched.toString());
	}

	public String addCourse(String courseName, int courseId, int secNum) {
		Course add = cat.searchCat(courseName, courseId);
		Registration reg = new Registration();
		return reg.completeRegistration(theStudent, add.getCourseOfferingAt(secNum - 1));
	}

	public String removeCourse(String courseName, int courseId) {
		if (theStudent.removeRegistration(courseName)) {
			return courseName + " " + courseId + " is successfully removed!";
		} else {
			return "The course was not found, so it could not be removed!";
		}
	}

	public String viewAllCourses() {
		if (cat.getCourseList().isEmpty()) {
			return ("Critical Error: No DataBase Found!!");
		}
		return cat.toString();
	}
	
	public void setTheStudent(String name, int id) {
		theStudent = new Student(name, id);
	}

	public String coursesTaken() {
		if (theStudent.getStudentRegList().isEmpty()) {
			return "You currently have no courses added.";
		}

		String takenCourses = "Courses taken by the student are:\n";

		for (Registration r : theStudent.getStudentRegList()) {
			takenCourses += r.getTheOffering().getTheCourse().toString();
		}

		return takenCourses;
	}
}