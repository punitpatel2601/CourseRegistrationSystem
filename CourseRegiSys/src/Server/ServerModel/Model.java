package Server.ServerModel;

import Server.ServerModel.Registration.*;

/**
 * Provides data fields and methods to define, search, view a catalogue database
 * and to view/edit a student's courses
 * Serves as a communication link between ServerCommunication and the logic and database of the
 * program (Registration package)
 * @author A. Mohar, T. Pritchard, P. Patel
 * @version 1.0
 * @since April 13, 2020
 */
public class Model {
	/**
	 * the database catalogue, contains all courses available
	 */
	private CourseCatalogue cat;

	/**
	 * the student/user that interacts with ClientGUI
	 */
	private Student theStudent; 

	/**
	 * constructs a catalogue
	 */
	public Model() {
		cat = new CourseCatalogue();
	}
	
	/**
	 * assigns user to a student object
	 * @param studentName the Student object's name
	 * @param studentId the Student object's id
	 */
	public void initializeStudent(String studentName, int studentId) {
		theStudent = new Student(studentName, studentId);
	}

	/**
	 * searches database for specified course 
	 * @param courseName
	 * @param courseId
	 * @return String object containing message representing information about the searched course
	 */
	public String searchCourse(String courseName, int courseId) {
		Course courseSearched = cat.searchCat(courseName, courseId);

		if (courseSearched == null) { // if not found,display error
			String serr = cat.displayCourseNotFoundError();
			return serr;
		}

		return ("Found course: " + courseSearched.toString());
	}

	/**
	 * registers student for specified course
	 * @param courseName
	 * @param courseId
	 * @param secNum
	 * @return String object containing message representing information about student's registration
	 */
	public String addCourse(String courseName, int courseId, int secNum) {
		Course add = cat.searchCat(courseName, courseId);
		Registration reg = new Registration();
		return reg.completeRegistration(theStudent, add.getCourseOfferingAt(secNum - 1));
	}

	/**
	 * removes specified course from the student
	 * @param courseName
	 * @param courseId
	 * @return	String object containing message representing information about removal of a course from a Student object
	 */
	public String removeCourse(String courseName, int courseId) {
		if (theStudent.removeRegistration(courseName)) {
			return courseName + " " + courseId + " is successfully removed!";
		} else {
			return "The course was not found, so it could not be removed!";
		}
	}

	/**
	 * shows all courses available in database
	 * @return String object representing all courses in database
	 */
	public String viewAllCourses() {
		if (cat.getCourseList().isEmpty()) {
			return ("Critical Error: No DataBase Found!!");
		}
		return cat.toString();
	}
	
	/**
	 * setter function: sets user to Student object
	 */
	public void setTheStudent(String name, int id) {
		theStudent = new Student(name, id);
	}

	/**
	 * shows all courses taken by specified student
	 * @return String object containing courses that the student took
	 */
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