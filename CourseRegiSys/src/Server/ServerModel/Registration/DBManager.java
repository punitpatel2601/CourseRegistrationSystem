package Server.ServerModel.Registration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

/**
 * Simulates a database, provides logic and data fields to load data in from a
 * text file containing course records
 * 
 * @author P. Patel, A. Mohar, T. Pritchard
 * @version 1.0
 * @since April 18, 2020
 */
public class DBManager {

	/**
	 * The connection to the DB
	 */
	private static Connection connection;

	/**
	 * The statement to the DB
	 */
	private static Statement st;

	/**
	 * The result set
	 */
	private ResultSet rs;

	/**
	 * Username for the DB
	 */
	private String username;

	/**
	 * Passcode for the DB
	 */
	private String passcode;
	
	private ArrayList<Course> courseList;

	/**
	 * Constructs a DBManager with given credentials and calls
	 * initializeConnection method.
	 */
	public DBManager() {
		username = "root";
		passcode = "root";

		connection = null;
		st = null;
		
		courseList = new ArrayList<Course>();

		initializeConnection();
	}

	public void initializeConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliant"
					+ "TimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, passcode);
			System.out.println("CONNECTED");
			selectAllCourses();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	public void createDatabase() {
		boolean dbexists = false;
		try {
			System.out.println("Creating to Database..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliant"
												+ "TimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username

					"jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					username, passcode);
			System.out.println("CONNECTED");
			st = connection.createStatement();
			st.executeUpdate("CREATE DATABASE CRS_P_A_T");
		} catch (Exception e) {
			System.out.println("Database already exists, not created");
			dbexists = true;
		}
		if (dbexists == false) {
			createTable();
		}
	}
	 */

	public void createTable() {

		try {
			st = connection.createStatement();
			String sql = "CREATE TABLE COURSES (course_name VARCHAR(255) not NULL, course_id INTEGER not NULL, secNum INTEGER , secCap INTEGER , PRIMARY KEY (course_id))";
			st.executeUpdate(sql);

			sql = "CREATE TABLE STUDENTS (sName VARCHAR(255) not Null, sId INTEGER not NULL, PRIMARY KEY (sId))";
			st.executeUpdate(sql);

			System.out.println("created tables");
		} catch (Exception e) {
			System.out.println("Tables already exists, not creating another ones.");
		}

		populateDB();
	}

	public void populateDB() {

		try {
			st = connection.createStatement();
			String sql = "INSERT INTO COURSES VALUES('CPSC',319,2,30)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('ENCM',369,3,50)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('ENEL',327,1,50)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('ENEL',353,1,100)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('ENGG',200,1,100)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('ENGG',233,2,75)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('ENSF',337,4,30)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('ENSF',409,3,30)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('MATH',211,2,100)";
			st.executeUpdate(sql);

			sql = "INSERT INTO COURSES VALUES('MATH',271,1,150)";
			st.executeUpdate(sql);

			System.out.println("INSERTED RECORDS ARE:");

			sql = "select * from COURSES";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				{
					System.out.println(rs.getString("course_name") + " - " + rs.getString("course_id") + " - "
							+ rs.getString("secNum") + " - " + rs.getString("secCap"));
				}
			}
		} catch (Exception e) {
			System.out.println("Duplicate entries.. not entering the existing data.");
		}
	}

	public void deleteAllTables() {

		try {
			String sql = "DROP TABLE COURSES";
			st.executeUpdate(sql);

			sql = "DROP TABLE STUDENTS";
			st.executeUpdate(sql);

			System.out.println("All tables deleted, database is empty");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCourseList() {
		try {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectAllCourses() {
		try {
			st = connection.createStatement();
			String query = "select * from courses";
			rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString("course_name") + " " + 
						rs.getString("course_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads data from database and inserts it accordingly into courseList
	 */
	public void selectCourse(String cName, int cId) {
		try {
			String query = "select * from courses where course_name = ? and course_id = ?";
			PreparedStatement pStat = connection.prepareStatement(query);
			pStat.setString(1, cName);
			pStat.setInt(2, cId);
			rs = pStat.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(""));
			}
			pStat.close();
		} catch (SQLException e ) {
			e.printStackTrace();
		}

		/*
		st = null;


		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from COURSES");

			while (rs.next()) {
				String coursename = rs.getString("course_name");
				int courseNum = Integer.parseInt(rs.getString("course_id"));
				int secNum = Integer.parseInt(rs.getString("secNum"));
				int secCap = Integer.parseInt(rs.getString("secCap"));
				Course c = new Course(coursename, courseNum);
				for (int i = 1; i <= secNum; i++) {
					c.addOffering(new CourseOffering(i, secCap));
				}
				courseList.add(c);
			}
		} catch (Exception e) {
			System.out.println("Database unreadable");
		}
		for (int i = 0; i < courseList.size(); i++) {
			System.out.println(courseList.get(i).getCourseName() + " - " + courseList.get(i).getCourseNum());
		}
		 */
	}

	/**
	 * Closes all connections to Database
	 */
	public void close() {
		try {
			st.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Inserts a new student/user into the database
	 */
	public void insertStudent(Student stu) {
		try {
			String query = "INSERT INTO STUDENTS (student_name, student_id) values (?, ?)";
			PreparedStatement pStat = connection.prepareStatement(query);
			pStat.setString(1, stu.getStudentName());
			pStat.setInt(2, stu.getStudentId());
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DBManager();
	}
}