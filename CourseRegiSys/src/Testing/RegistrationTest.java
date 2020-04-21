package Testing;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Server.ServerModel.Registration.*;

class RegistrationTest {

	private static Registration regTest = new Registration();
	private static Student stu;
	private static CourseOffering co;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		stu = new Student("Tester", 1234);
		co = new CourseOffering(1, 50);
		co.setTheCourse(new Course("ENSF", 409));
	}

	// Note: the completeReg method calls the private method addReg
	@Test
	public void testCompleteReg() {
		String output = regTest.completeRegistration(stu, co);
		System.out.println(output);
		String expected = "Tester, you have successfully registered for " +
						  "ENSF 409 in Section 1";
		assertEquals("Checking output of completeReg", expected, output);
	}
}
