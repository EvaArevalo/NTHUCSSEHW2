package gradesystem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * CLASS PURPOSE:	Test functions generated using JUnit to test the Grades class.
 */
public class GradeSystemsTest {
	
	GradeSystems aSystem;
	
	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Initializes the system with a few Grade instances,run only at start
	 */
	@Before
	public void initialize(){
		try{
		aSystem = new GradeSystems();
		}catch(Exception e){}
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the containsID()function
	 * APPLIED PARAMETERS: GradesSystem instance initialized in initialize()
	 * CASE1:
	 * 		 INPUT:  		   985002001 (ID)
	 * 		 EXPECTED OUTPUT:  true
	 * CASE2:
	 * 		 INPUT:  		   958500 (non-valid ID)
	 * 		 EXPECTED OUTPUT:  false
	 */	
	@Test
	public void testContainsID() {
		assertTrue(aSystem.containsID(985002001));
		assertFalse(aSystem.containsID(958500));
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the showGrade()function
	 * APPLIED PARAMETERS: GradesSystem instance initialized in initialize()
	 *  
	 * CASE1:
	 * 		 INPUT: 		  962001051 (ID)
	 * 		 EXPECTED OUTPUT: lab1 score 81
	 * 					  	  lab2 score 32
	 * 					 	  lab3 score 50
	 *					 	  midTerm score 90
	 *					 	  finalExam score 93
	 *						  totalGrade score 81
	 *									(81*0.1+32*0.1+50*0.1+90*0.3+93*0.4=80.5)
	 *CASE2:
	 * 		 INPUT: 		  985002002 (ID)
	 * 		 EXPECTED OUTPUT: lab1 score 99
	 * 					  	  lab2 score 81
	 * 					 	  lab3 score 91
	 *					 	  midTerm score 92
	 *					 	  finalExam score 95
	 *						  totalGrade score 93
	 *									 (99*0.1+81*0.1+91*0.1+92*0.3+95*0.4=92.7)
	 */	
	@Test
	public void testShowGrade() {
		//962001051
		assertEquals(81,aSystem.showGrade(962001051)[0]);
		assertEquals(32,aSystem.showGrade(962001051)[1]);
		assertEquals(50,aSystem.showGrade(962001051)[2]);
		assertEquals(90,aSystem.showGrade(962001051)[3]);
		assertEquals(93,aSystem.showGrade(962001051)[4]);
		assertEquals(81,aSystem.showGrade(962001051)[5]);
		//985002002
		assertEquals(99,aSystem.showGrade(985002002)[0]);
		assertEquals(81,aSystem.showGrade(985002002)[1]);
		assertEquals(91,aSystem.showGrade(985002002)[2]);
		assertEquals(92,aSystem.showGrade(985002002)[3]);
		assertEquals(95,aSystem.showGrade(985002002)[4]);
		assertEquals(93,aSystem.showGrade(985002002)[5]);
		
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the showRank()function
	 * APPLIED PARAMETERS: GradesSystem instance initialized in initialize()
	 *  
	 * CASE1:
	 * 		 INPUT: 		  985002004 (ID)
	 * 		 EXPECTED OUTPUT: 4
	 *CASE2:
	 * 		 INPUT: 		  985002509 (ID)
	 * 		 EXPECTED OUTPUT: 1
	 */	
	@Test
	public void testShowRank() {
		assertEquals(4,aSystem.showRank(985002004));
		assertEquals(1,aSystem.showRank(985002509));
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the showAvg()function
	 * APPLIED PARAMETERS: GradesSystem instance initialized in initialize()
	 *  
	 * UNIQUE TEST CASE:
	 * 		 INPUT: 		  None
	 * 		 EXPECTED OUTPUT: lab1 avg 90
	 * 					  	  lab2 avg 88
	 * 					 	  lab3 avg 89
	 *					 	  midTerm avg 90
	 *					 	  finalExam avg 90
	 *						  totalGrade avg 90
	 */	
	@Test
	public void testShowAvg() {
		assertEquals(90,aSystem.showAvg()[0]);
		assertEquals(88,aSystem.showAvg()[1]);
		assertEquals(89,aSystem.showAvg()[2]);
		assertEquals(90,aSystem.showAvg()[3]);
		assertEquals(90,aSystem.showAvg()[4]);
		assertEquals(90,aSystem.showAvg()[5]);
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the showAvg()function
	 * APPLIED PARAMETERS: GradesSystem instance initialized in initialize()
	 *  
	 * CASE1:
	 * 		 APPLIED PARAMETERS: 0.2 0.2 0.2 0.2 0.2 (Weights)
	 * 		 INPUT: 		     985002004 (ID)
	 * 		 EXPECTED OUTPUT:    4
	 * CASE2:
	 *  	 APPLIED PARAMETERS: 0.1 0.1 0.1 0.3 0.4 (Weights)
	 * 		 INPUT: 		     985002004 (ID)
	 * 		 EXPECTED OUTPUT:    17
	 */	
	@Test
	public void testUpdateWeights() {
		float[] weights2 = new float[]{0.2f,0.2f, 0.2f,0.2f,0.2f};
		float[] weights1 = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		
		assertEquals(4,aSystem.showRank(985002004));
		aSystem.updateWeights(weights2);
		assertEquals(17,aSystem.showRank(985002004));
		aSystem.updateWeights(weights1);
	}

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the getName()function
	 * APPLIED PARAMETERS: GradesSystem instance initialized in initialize()
	 *  
	 * CASE1:
	 * 		 INPUT: 		  962001051 (ID)
	 * 		 EXPECTED OUTPUT: §õ«Â§Ê
	 * CASE2:
	 * 		 INPUT: 		  985002002 (ID)
	 * 		 EXPECTED OUTPUT: ªLªä§±
	 */	
	@Test
	public void testGetName() {
		assertEquals("§õ«Â§Ê",aSystem.getName(962001051));
		assertEquals("ªLªä§±",aSystem.getName(985002002));
	}

}
