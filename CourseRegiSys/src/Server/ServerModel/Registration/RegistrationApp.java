package Server.ServerModel.Registration;

import java.util.Scanner;

public class RegistrationApp {
    private static Scanner scan;
    private CourseCatalogue courseCatalogue;

    public RegistrationApp(CourseCatalogue courseCat) {
        courseCatalogue = courseCat;
        scan = new Scanner(System.in);
    }

    private String getCourseName() {
        System.out.println("\nEnter the name of the course: ");
        String name = scan.nextLine();
        return name;
    }

    private int getCourseID() {
        System.out.println("\nEnter the number of the course: ");
        int name = scan.nextInt();
        return name;
    }

    public void menu() {
        System.out.println("\nSelect from choices..\n1. Search course catalogue");
        System.out.println("2. Add course to course catalogue");
        System.out.println("3. Remove course from course catalogue");
        System.out.println("4. View all courses in catalogue");
        System.out.println("5. View all courses taken by students");
        System.out.println("6. Quit");
    }

    public void options() {
        while (true) {
            menu();

            String choices = scan.nextLine();
            if (choices.isEmpty())
                choices = scan.nextLine();
            int choice = Integer.parseInt(choices);

            switch (choice) {

                case 1:
                    courseCatalogue.getCourseDetails(getCourseName(), getCourseID());
                    break;
                case 2:
                    courseCatalogue.getCourseList().add(new Course(getCourseName(), getCourseID()));
                    break;
                case 3:
                    courseCatalogue.removeCourse(getCourseName(), getCourseID());
                    break;
                case 4:
                    courseCatalogue.displayToString();
                    break;
                case 5:
                    courseCatalogue.coursesTaken();
                    break;
                case 6:
                    System.out.println("GoodBye!!");
                    return;
                default:
                    System.out.println("Invalid choice!!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        CourseCatalogue cat = new CourseCatalogue();
        RegistrationApp ra = new RegistrationApp(cat);

        Student st1 = new Student("Sara", 1);
        Student st2 = new Student("Sam", 2);
        Student st3 = new Student("Kri", 3);
        Student st4 = new Student("Ana", 4);
        Student st5 = new Student("Pat", 5);
        Student st6 = new Student("Han", 6);
        Student st7 = new Student("Punit", 7);
        Student st8 = new Student("Kabir", 8);

        Course myCourse = cat.searchCat("ENGG", 233);
        if (myCourse != null) {
            cat.createCourseOffering(myCourse, 1, 100);
            cat.createCourseOffering(myCourse, 2, 200);
        }

        Course myCourse2 = cat.searchCat("ENSF", 409);
        myCourse2.addPreReq(myCourse);
        if (myCourse2 != null) {
            cat.createCourseOffering(myCourse, 1, 300);
            cat.createCourseOffering(myCourse, 2, 400);
        }

        Course myCourse3 = cat.searchCat("PHYS", 259);
        if (myCourse3 != null) {
            cat.createCourseOffering(myCourse3, 1, 200);
            cat.createCourseOffering(myCourse3, 2, 400);
        }

        Registration engg_233 = new Registration();

        engg_233.completeRegistration(st1, myCourse.getCourseOfferingAt(0));
        engg_233.completeRegistration(st2, myCourse.getCourseOfferingAt(0));
        engg_233.completeRegistration(st3, myCourse.getCourseOfferingAt(0));
        engg_233.completeRegistration(st4, myCourse.getCourseOfferingAt(0));
        engg_233.completeRegistration(st5, myCourse.getCourseOfferingAt(0));
        engg_233.completeRegistration(st6, myCourse.getCourseOfferingAt(0));
        engg_233.completeRegistration(st7, myCourse.getCourseOfferingAt(0));
        engg_233.completeRegistration(st8, myCourse.getCourseOfferingAt(0));

        myCourse.conditionSNumThisCourse();

        Registration phys_259 = new Registration();
        phys_259.completeRegistration(st1, myCourse3.getCourseOfferingAt(0));
        phys_259.completeRegistration(st2, myCourse3.getCourseOfferingAt(0));
        phys_259.completeRegistration(st3, myCourse3.getCourseOfferingAt(0));
        phys_259.completeRegistration(st4, myCourse3.getCourseOfferingAt(0));
        phys_259.completeRegistration(st5, myCourse3.getCourseOfferingAt(0));
        phys_259.completeRegistration(st6, myCourse3.getCourseOfferingAt(0));
        phys_259.completeRegistration(st7, myCourse3.getCourseOfferingAt(0));
        phys_259.completeRegistration(st8, myCourse3.getCourseOfferingAt(0));
        myCourse3.conditionSNumThisCourse();

        ra.options();
    }
}
