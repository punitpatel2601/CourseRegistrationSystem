package Server.ServerModel.Registration;

public class Registration {
    private Student theStudent;
    private CourseOffering theOffering;
    private char grade;

    void completeRegistration(Student st, CourseOffering of) {
        theStudent = st;
        theOffering = of;
        addRegistration();
        if (!(theStudent.maxCourse())) {
            System.out.println(st.getStudentName() + " cannot register for" + of.getTheCourse().getCourseName() + "  "
                    + of.getTheCourse().getCourseNum()
                    + " . This student is only allowed to have 6 courses at maximum .");
            theStudent.removeMaxCourse();
            theOffering.removeMaxOffering();
        }
        if (theOffering.numberOfStudentsOffering() >= theOffering.getSecCap()) {
            System.out.println("There is currently no available place in " + of.getTheCourse().getCourseName() + " "
                    + of.getTheCourse().getCourseNum());
        }
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

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        String st = "\n";
        st += "Student Name: " + getTheStudent() + ".\n";
        st += "The Offering: " + getTheOffering() + ".\n";
        st += "Grade: " + getGrade();
        st += ".\n------------------\n";
        return st;
    }
}
