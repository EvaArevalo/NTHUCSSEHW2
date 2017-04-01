package gradesystem;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * CLASS PURPOSE:	Test functions generated using JUnit to test the Grades class.
 */
public class GradesTest {
	
	Grades g1a,g1b,g2;
	float[] weights1, weights2;
	int[] scores1, scores2;

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Initializes the system with a few Grade instances,run only at start
	 */
	@Before
	public void initialize(){
		weights1 = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		weights2 = new float[]{0.2f,0.2f, 0.2f,0.2f,0.2f};
		scores1 = new int[] {81, 32, 50, 90, 93};
		scores2 = new int[] {99, 81, 91, 92, 95};
		g1a = new Grades ("§õ«Â§Ê", 962001051, scores1, weights1);
		g1b = new Grades ("§õ«Â§Ê", 962001051, scores1, weights2);
		g2 = new Grades("ªLªä§±",985002002,scores2,weights1);
	}
	
	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the calculateTotalGrade()function
	 * APPLIED PARAMETERS: Grades instances g1a and g1b initialized in initialize()
	 * 					   g1a,g1b: Student: 962001051, Grades: 81 32 50 90 93 
	 * CASE1:
	 * 		 INPUT:  		  0.1 0.1 0.1 0.3 0.4 (weights)
	 * 		 EXPECTED OUTPUT: 81
	 * CASE2:
	 * 		 INPUT:  		  0.2 0.2 0.2 0.2 0.2 (weights)
	 * 		 EXPECTED OUTPUT: 69
	 */	
	@Test
	public void testCalculateTotalGrade() {
		assertEquals(81,g1a.calculateTotalGrade(weights1));
		assertEquals(69,g1b.calculateTotalGrade(weights2));
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the getID()function
	 * APPLIED PARAMETERS: Grades instances g1a and g2 initialized in initialize()
	 * 					   g1a: Student: 962001051, Grades: 81 32 50 90 93 
	 * 					   g2:  Student: 985002002, Grades: 99 81 91 92 95 
	 * 
	 * CASE1:
	 * 		 INPUT: 		  g1a (Grades instance)
	 * 		 EXPECTED OUTPUT: 962001051
	 * CASE2:
	 * 		 INPUT:  		  g2 (Grades instance)
	 * 		 EXPECTED OUTPUT: 985002002
	 */	
	@Test
	public void testGetID() {
		assertEquals(962001051,g1a.getID());
		assertEquals(985002002,g2.getID());
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the getName()function
	 * APPLIED PARAMETERS: Grades instances g1a and g2 initialized in initialize()
	 * CASE1:
	 * 		 INPUT: 		  g1a (Grades instance)
	 * 		 EXPECTED OUTPUT: §õ«Â§Ê
	 * CASE2:
	 * 		 INPUT:  		  g2 (Grades instance)
	 * 		 EXPECTED OUTPUT: ªLªä§±
	 */	
	@Test
	public void testGetName() {
		assertEquals("§õ«Â§Ê",g1a.getName());
		assertEquals("ªLªä§±",g2.getName());
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the getScores()function
	 * APPLIED PARAMETERS: Grades instance g1a initialized in initialize()
	 * 					   g1a: Student: 962001051, Grades: 81 32 50 90 93
	 * 					   g2:  Student: 985002002, Grades: 99 81 91 92 95  
	 * CASE1:
	 * 		 INPUT: 		  g1a (Grades instance)
	 * 		 EXPECTED OUTPUT: lab1 score 81
	 * 					  	  lab2 score 32
	 * 					 	  lab3 score 50
	 *					 	  midTerm score 90
	 *					 	  finalExam score 93
	 *CASE2:
	 * 		 INPUT: 		  g2 (Grades instance)
	 * 		 EXPECTED OUTPUT: lab1 score 99
	 * 					  	  lab2 score 81
	 * 					 	  lab3 score 91
	 *					 	  midTerm score 92
	 *					 	  finalExam score 95
	 */	
	@Test
	public void testGetScores() {
		//g1a
		assertEquals(81,g1a.getScores()[0]);
		assertEquals(32,g1a.getScores()[1]);
		assertEquals(50,g1a.getScores()[2]);
		assertEquals(90,g1a.getScores()[3]);
		assertEquals(93,g1a.getScores()[4]);
		//g2
		assertEquals(99,g2.getScores()[0]);
		assertEquals(81,g2.getScores()[1]);
		assertEquals(91,g2.getScores()[2]);
		assertEquals(92,g2.getScores()[3]);
		assertEquals(95,g2.getScores()[4]);
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the getTotalGrade()function
	 * APPLIED PARAMETERS: Grades instances g1a and g1b initialized in initialize()
	 * 					   g1a,g1b: Student: 962001051, Grades: 81 32 50 90 93 
	 * CASE1:
	 * 		 INPUT: 		  g1a (Grades instance)
	 * 		 EXPECTED OUTPUT: 81 (81*0.1+32*0.1+50*0.1+90*0.3+93*0.4=80.5)
	 * CASE2:
	 * 		 INPUT:  		  g1b (Grades instance)
	 * 		 EXPECTED OUTPUT: 69 (81*0.2+32*0.2+50*0.2+90*0.2+93*0.2=69.2)
	 */	
	@Test
	public void testGetTotalGrade() {
		assertEquals(81,g1a.getTotalGrade());
		assertEquals(69,g1b.getTotalGrade());
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the resetTotalGrade()function
	 * APPLIED PARAMETERS: Grades instances g1a and g2 initialized in initialize()
	 * 					   g1a: Student: 962001051, Grades: 81 32 50 90 93 
	 * 					   g2:  Student: 985002002, Grades: 99 81 91 92 95 
	 * CASE1:
	 * 		 INPUT:  		  0.2 0.2 0.2 0.2 0.2 (Weights)
	 * 		 EXPECTED OUTPUT: 69 (81*0.2+32*0.2+50*0.2+90*0.2+93*0.2=69.2)
	 * CASE2:
	 * 		 INPUT:           0.2 0.2 0.2 0.2 0.2 (Weights)
	 * 		 EXPECTED OUTPUT: 92 (99*0.2+81*0.2+91*0.2+92*0.2+95*0.2=91.6)
	 */	
	@Test
	public void testResetTotalGrade() {
		assertEquals(69,g1a.resetTotalGrade(weights2));
		assertEquals(92,g2.resetTotalGrade(weights2));
	}

}
