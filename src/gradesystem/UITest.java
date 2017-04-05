package gradesystem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import gradesystem.GradeSystems.ExamsName;

/**
 * CLASS PURPOSE:	Test functions generated using JUnit to test the UI class.
 */
public class UITest {
	
	UI aUI ;

	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Initializes the system with a UI instance,run only at start
	 */
	@Before
	public void initialize(){
		try{
			aUI = new UI();
		}catch(Exception e){}
	}
	
	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the checkID()function
	 * APPLIED PARAMETERS: UI instance initialized in initialize()
	 * CASE1:
	 * 		 INPUT:  		   985002001 (ID)
	 * 		 EXPECTED OUTPUT:  true
	 * CASE2:
	 * 		 INPUT:  		   958500 (non-valid ID)
	 * 		 EXPECTED OUTPUT:  false
	 */	
	@Test
	public void testCheckID() {
		assertTrue(aUI.checkID(985002001));
		assertFalse(aUI.checkID(958500));
	}
	
	
	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the promptCommand()function
	 * APPLIED PARAMETERS: UI instance initialized in initialize()
	 * CASE1:
	 * 		 INPUT:  		   G
	 * 		 EXPECTED OUTPUT:  true
	 * CASE2:
	 * 		 INPUT:  		   A
	 * 		 EXPECTED OUTPUT:  true
	 * CASE3:
	 * 		 INPUT:  		   R
	 * 		 EXPECTED OUTPUT:  true
	 * CASE4:
	 * 		 INPUT:  		   W
	 * 		 EXPECTED OUTPUT:  true
	 * CASE5:
	 * 		 INPUT:  		   E
	 * 		 EXPECTED OUTPUT:  false
	 */	
	@Test
	public void testPromptCommand(){
		ByteArrayInputStream in = new ByteArrayInputStream("G".getBytes());
		System.setIn(in);
		try{
			assertTrue(aUI.promptCommand());
			in = new ByteArrayInputStream("A".getBytes());
			assertTrue(aUI.promptCommand());
			in = new ByteArrayInputStream("R".getBytes());
			assertTrue(aUI.promptCommand());
			in = new ByteArrayInputStream("W".getBytes());
			assertTrue(aUI.promptCommand());
			in = new ByteArrayInputStream("E".getBytes());
			assertFalse(aUI.promptCommand());

		}catch(Exception e){}
		finally{System.setIn(System.in);}
	}
	
	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the promptCommand()function when a non command is entered
	 * APPLIED PARAMETERS: UI instance initialized in initialize()
	 * UNIQUE CASE:
	 * 		 INPUT:  		   Dummy
	 * 		 EXPECTED OUTPUT:  Raised Exception NoSuchCommandExceptions
	 */	
	@Test(expected=NoSuchCommandExceptions.class)
	public void testNoSuchCommandExceptions(){
		ByteArrayInputStream in = new ByteArrayInputStream("Dummy".getBytes());
		System.setIn(in);
		in = new ByteArrayInputStream("Dummy".getBytes());
		aUI.promptID();
		System.setIn(System.in);
	}
	
	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the promptID()function 
	 * APPLIED PARAMETERS: UI instance initialized in initialize()
	 * CASE 1:
	 * 		 INPUT:  		   985002001 
	 * 		 EXPECTED OUTPUT:  true
	 * CASE 2:
	 * 		 INPUT:  		   Q
	 * 		 EXPECTED OUTPUT:  false
	 */	
	@Test
	public void testPromptID() {
		ByteArrayInputStream in = new ByteArrayInputStream("985002001 ".getBytes());
		System.setIn(in);
		try{
			assertTrue(aUI.promptCommand());
			in = new ByteArrayInputStream("Q".getBytes());
			assertFalse(aUI.promptCommand());
		}catch(Exception e){}
		finally{System.setIn(System.in);}
	}
	
	/**
	 * TIME COMPLEXITY: N/A (testing)
	 * PURPOSE: Test the promptID()function when a non-valid ID is used
	 * APPLIED PARAMETERS: UI instance initialized in initialize()
	 * UNIQUE CASE:
	 * 		 INPUT:  		   985002001
	 * 		 EXPECTED OUTPUT:  Raised Exception NoSuchIDExceptions
	 */	
	@Test(expected=NoSuchIDExceptions.class)
	public void testNoSuchIDException(){
		ByteArrayInputStream in = new ByteArrayInputStream("985002001".getBytes());
		System.setIn(in);
		aUI.promptID();
		System.setIn(System.in);
	}
		
	

}
