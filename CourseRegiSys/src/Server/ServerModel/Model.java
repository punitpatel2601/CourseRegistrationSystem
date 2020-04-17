package Server.ServerModel;

import Server.ServerModel.Registration.*;

/**
 * Provides data fields and methods to define, search, view a catalogue database
 * and to view/edit a student's courses Serves as a communication link between
 * ServerCommunication and the backend(Registration package)
 * 
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
	 * constructs a catalogue and assigns student
	 */
	public Model(String name, int id) {
		cat = new CourseCatalogue();
		theStudent = new Student(name, id);
	}

	/**
	 * searches database for specified course
	 * 
	 * @param courseName course name
	 * @param courseId   course id
	 * @return String object containing msg representing information about the
	 *         searched course
	 */
	public String searchCourse(String courseName, int courseId) {
		Course courseSearched = cat.searchCat(courseName, courseId);

		if (courseSearched == null) { // if not found,display error
			return cat.displayCourseNotFoundError();
		}

		return ("Found course: " + courseSearched.toString());
	}

	/**
	 * registers student for specified course
	 * 
	 * @param courseName course name
	 * @param courseId   course id
	 * @param secNum     course section number
	 * @return String object containing message representing information about
	 *         student's registration
	 */
	public String addCourse(String courseName, int courseId, int secNum) {
		Course addi = cat.searchCat(courseName, courseId);

		if (addi == null) {
			return ("Course is not in Catalogue# #Please view the Catalogue to see all avaliable courses# # # # #"
					+ coursesTaken());
		} else if (secNum > addi.getOfferingList().size() || secNum < 1) {
			return ("That section does not exist for this course# # # #" + coursesTaken());
		} else if (theStudent.hasRegAdded(courseName, courseId)) {
			return ("You are already registered for this course!# # # #" + coursesTaken());
		}
		Registration reg = new Registration();

		return (reg.completeRegistration(theStudent, addi.getCourseOfferingAt(secNum - 1)) + "# # #" + coursesTaken());
	}


	public String addNewCourse(String courseName, int courseId, int secNums, int cap){
		Course n = new Course(courseName, courseId);
		n.addOffering(new CourseOffering(secNums, cap));
		cat.getCourseList().add(n);
		Course check = cat.searchCat(courseName, courseId);
		if(check == null){
			return "course not added! #";
		}
		return ("Course: "+ n.toString() + "# was successfully added!");
	}

	public String runCourse(String name, int id){
		Course run = cat.searchCat(name, id);
		if(run == null){
			return "Course not found! #";
		}
		return run.conditionSNumThisCourse();
	}

	/**
	 * removes specified course from the student
	 * 
	 * @param courseName course name
	 * @param courseId   course id
	 * @return String object containing message representing information about
	 *         removal of a course from a Student object
	 */
	public String removeCourse(String courseName, int courseId) {
		if (theStudent.removeRegistration(courseName, courseId)) {
			return (courseName + " " + courseId + " is successfully removed!# # #" + coursesTaken());
		} else {
			return ("The course was not found, so it could not be removed!# # #" + coursesTaken());
		}
	}

	/**
	 * shows all courses available in database
	 * 
	 * @return String object representing all courses in database
	 */
	public String viewAllCourses() {
		if (cat.getCourseList().isEmpty()) {
			return ("Critical Error: No DataBase Found!!");
		}
		return cat.toString();
	}

	/**
	 * shows all courses taken by specified student
	 * 
	 * @return String object containing courses that the student took
	 */
	public String coursesTaken() {
		if (theStudent.getStudentRegList().isEmpty() || theStudent.getStudentRegList() == null) {
			return "You currently have no courses added.";
		}

		String takenCourses = "Current Courses taken by the student are: #";

		for (Registration r : theStudent.getStudentRegList()) {
			takenCourses += r.getTheOffering().getTheCourse().toString() + " in Section "
					+ r.getTheOffering().getSecNum() + "#";
		}

		return takenCourses;
	}
}