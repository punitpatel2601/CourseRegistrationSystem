package Testing;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Server.ServerModel.Registration.*;

public class CourseTest {
	
	private Course cTest = new Course("ENSF", 409);
	
	@BeforeEach
	public void setUp() throws Exception {
		// Initializes Course with 3 sections, each with a 
		// student capacity of 50
		for (int i = 1; i <= 3; i++) {
			cTest.addOffering(new CourseOffering(i, 50));
		}
		// Initializes Course with a prerequisite
		cTest.addPreReq(new Course("ENSF", 337));
	}
	
	@Test
	public void testOfferingSize() {
		assertEquals("Checking size of cTest offeringList", 3, cTest.offeringListSize());
	}
	
	@Test
	public void testPreReqSize() {
		assertEquals("Checking the size of cTest preReq", 1, cTest.preReqSize());
	}
	
	@Test
	public void testAddOffering() {
		cTest.addOffering(new CourseOffering(4, 100));
		assertEquals("Checking new size of cTest offeringList", 4, cTest.offeringListSize());
	}
	
	@Test
	public void testCheckStudentNumber() {
		assertEquals("Checking number of students in Course", 0, cTest.checkStudentNumber());
	}
	
	@Test
	public void testConditionSNum() {
		assertEquals("Checking if the course can run", "Total number of student in ENSF   409" +
					 " is 0, this course cannot be run ", cTest.conditionSNumThisCourse());
	}

}
