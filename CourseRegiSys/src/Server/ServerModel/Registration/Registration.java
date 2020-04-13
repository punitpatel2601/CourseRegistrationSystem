package Server.ServerModel.Registration;

public class Registration {
    private Student theStudent;
    private CourseOffering theOffering;

    public String completeRegistration(Student st, CourseOffering of) {
        String ret;
    	theStudent = st;
        theOffering = of;
        addRegistration();
        if (!(theStudent.maxCourse())) {
            theStudent.removeMaxCourse();
            theOffering.removeMaxOffering();
            ret = "You cannot register for " + of.getTheCourse().getCourseName() + " " + of.getTheCourse().getCourseNum() +
            		" Section " + of.getSecNum() +  ".\nYou are only allowed to register for a maximum of 6 courses.";
            
        } else if (theOffering.numberOfStudentsOffering() >= theOffering.getSecCap()) {
        	ret = "Sorry! There is currently no available place in " + of.getTheCourse().getCourseName() + " " +
        			of.getTheCourse().getCourseNum() + " Section " + of.getSecNum();
        } else {
        	ret = theStudent.getStudentName() + ", you have successfully registered for " + of.getTheCourse().getCourseName() +
        			" " + of.getTheCourse().getCourseNum() + " Section " + of.getSecNum();
        }
        return ret;
    }

    private void addRegistration() {
        theStudent.addRegistration(this);
        theOffering.addRegistration(this, theStudent);
    }

    public Student getTheStudent() {
        return theStudent;
    }

    public void setTheStudent(Student theStudent) {
        this.theStudent = theStudent;
    }

    public CourseOffering getTheOffering() {
        return theOffering;
    }

    public void setTheOffering(CourseOffering theOffering) {
        this.theOffering = theOffering;
    }

    @Override
    public String toString() {
        String st = "\n";
        st += "Student Name: " + getTheStudent() + ".\n";
        st += "The Offering: " + getTheOffering() + ".\n";
        st += ".\n------------------\n";
        return st;
    }
}
