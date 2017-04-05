package gradesystem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class IntegrationTest {
	
	UI aUI;
	String promptCommand = "Enter Command\r\n"
			+ "\t(G)rade\r\n"
			+ "\t(R)ank\r\n"
			+ "\t(A)verage\r\n"
			+ "\t(W)eights Update\r\n"
			+ "\t(E)xit:\r\n";
	/**
	 * testQuit()
	 * 
	 * Enter your ID or 'Q' to quit:
	 * Q
	 * Goodbye!
	 */
	@Test
	public void testQuit() {
		String userInput = "Q";
		String expectedOutput = "Enter your ID or 'Q' to quit:\r\n"
				+ "Goodbye!\r\n";
		ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream (outContent));
		try { aUI = new UI(); }
		catch (Exception e) {
			fail(e.toString());
		}
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * testLogin()
	 * 
	 * Enter your ID or 'Q' to quit:
	 * 962001051
	 * Welcome §õ«Â§Ê !
	 * promptCommand
	 * E
	 * Enter your ID or 'Q' to quit:
	 * Q
	 * Goodbye!
	 */
	@Test
	public void testLogin() {
		String userInput = "962001051\n"
				+ "E\n"
				+ "Q";
		String expectedOutput = "Enter your ID or 'Q' to quit:\r\n"
				+ "Welcome §õ«Â§Ê !\r\n"
				+ promptCommand
				+ "Enter your ID or 'Q' to quit:\r\n"
				+ "Goodbye!\r\n";
		ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream (outContent));
		try { aUI = new UI(); }
		catch (Exception e) {
			fail(e.toString());
		}
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * testGRA()
	 * 
	 * Enter your ID or 'Q' to quit:
	 * 962001051
	 * Welcome §õ«Â§Ê !
	 * promptCommand
	 * G
	 * Grades for §õ«Â§Ê: ...
	 * promptCommand
	 * R
	 * Rank for §õ«Â§Ê: 63
	 * promptCommand
	 * A
	 * Average grades for the class: ...
	 * promptCommand
	 * E
	 * Enter your ID or 'Q' to quit:
	 * Q
	 * Goodbye!
	 */
	@Test
	public void testGRA() {
		String userInput = "962001051\n"
				+ "G\n"
				+ "R\n"
				+ "A\n"
				+ "E\n"
				+ "Q";
		String expectedOutput = "Enter your ID or 'Q' to quit:\r\n"
				+ "Welcome §õ«Â§Ê !\r\n"
				+ promptCommand
				+ "Grades for §õ«Â§Ê:\r\n"
				+ "\tlab1: 81\r\n"
				+ "\tlab2: 32 (!)\r\n"
				+ "\tlab3: 50 (!)\r\n"
				+ "\tmidTerm: 90\r\n"
				+ "\tfinalExam: 93\r\n"
				+ "\ttotalGrade: 81\r\n"
				+ promptCommand
				+ "Rank for §õ«Â§Ê: 63\r\n"
				+ promptCommand
				+ "Average grades for the class:\r\n"
				+ "\tlab1: 90\r\n"
				+ "\tlab2: 88\r\n"
				+ "\tlab3: 89\r\n"
				+ "\tmidTerm: 90\r\n"
				+ "\tfinalExam: 90\r\n"
				+ "\ttotalGrade: 90\r\n"
				+ promptCommand
				+ "Enter your ID or 'Q' to quit:\r\n"
				+ "Goodbye!\r\n";
		ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream (outContent));
		try { aUI = new UI(); }
		catch (Exception e) {
			fail(e.toString());
		}
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/*
	 * testW()
	 * 
	 * Enter your ID or 'Q' to quit:
	 * 985002021
	 * Welcome ³¯¬f¹ü !\r\n
	 * G (totalGrade 94)
	 * R (4)
	 * W (20, 20, 20, 20, 20)
	 * Save? (Y/N): Y
	 * G (totalGrade 90)
	 * R (23)
	 * E
	 * Enter your ID or 'Q' to quit:
	 * Q
	 * Goodbye!
	 */
	@Test
	public void testW() {
		String userInput = "985002021\n"
				+ "G\n"
				+ "R\n"
				+ "W\n"
				+ "20\n20\n20\n20\n20\n"
				+ "Y\n"
				+ "G\n"
				+ "R\n"
				+ "E\n"
				+ "Q";
		String expectedOutput = "Enter your ID or 'Q' to quit:\r\n"
				+ "Welcome ³¯¬f¹ü !\r\n"
				+ promptCommand
				+ "Grades for ³¯¬f¹ü:\r\n"
				+ "\tlab1: 81\r\n"
				+ "\tlab2: 81\r\n"
				+ "\tlab3: 93\r\n"
				+ "\tmidTerm: 96\r\n"
				+ "\tfinalExam: 98\r\n"
				+ "\ttotalGrade: 94\r\n"
				+ promptCommand
				+ "Rank for ³¯¬f¹ü: 4\r\n"
				+ promptCommand
				+ "Current weights for each grade:\r\n"
				+ "\tlab1: 10%\r\n"
				+ "\tlab2: 10%\r\n"
				+ "\tlab3: 10%\r\n"
				+ "\tmidTerm: 30%\r\n"
				+ "\tfinalExam: 40%\r\n"
				+ "Enter new weights (in percentage):\r\n"
				+ "\tlab1: "
				+ "\tlab2: "
				+ "\tlab3: "
				+ "\tmidTerm: "
				+ "\tfinalExam: "
				+ "Confirm the new weights:\r\n"
				+ "\tlab1: 20%\r\n"
				+ "\tlab2: 20%\r\n"
				+ "\tlab3: 20%\r\n"
				+ "\tmidTerm: 20%\r\n"
				+ "\tfinalExam: 20%\r\n"
				+ "Save? (Y/N): "
				+ promptCommand
				+ "Grades for ³¯¬f¹ü:\r\n"
				+ "\tlab1: 81\r\n"
				+ "\tlab2: 81\r\n"
				+ "\tlab3: 93\r\n"
				+ "\tmidTerm: 96\r\n"
				+ "\tfinalExam: 98\r\n"
				+ "\ttotalGrade: 90\r\n"
				+ promptCommand
				+ "Rank for ³¯¬f¹ü: 23\r\n"
				+ promptCommand
				+ "Enter your ID or 'Q' to quit:\r\n"
				+ "Goodbye!\r\n";
		ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream (outContent));
		try { aUI = new UI(); }
		catch (Exception e) {
			fail(e.toString());
		}
		assertEquals(expectedOutput, outContent.toString());		
	}
	
	/*
	 * testInvalid()
	 * 
	 * Enter your ID or 'Q' to quit:
	 * 985000000
	 * No such ID in the system!
	 * Enter your ID or 'Q' to quit:
	 * 985002021
	 * Welcome ³¯¬f¹ü !\r\n
	 * promptCommand
	 * B
	 * Invalid command!
	 * promptCommand
	 * E
	 * Enter your ID or 'Q' to quit:
	 * Q
	 * Goodbye!
	 */
	@Test
	public void testInvalid() {
		String userInput = "985000000\n"
				+ "985002021\n"
				+ "B\n"
				+ "E\n"
				+ "Q";
		String expectedOutput = "Enter your ID or 'Q' to quit:\r\n"
				+ "No such ID in the system!\r\n"
				+ "Enter your ID or 'Q' to quit:\r\n"
				+ "Welcome ³¯¬f¹ü !\r\n"
				+ promptCommand
				+ "Invalid command!\r\n"
				+ promptCommand
				+ "Enter your ID or 'Q' to quit:\r\n"
				+ "Goodbye!\r\n";
		ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream (outContent));
		try { aUI = new UI(); }
		catch (Exception e) {
			fail(e.toString());
		}
		assertEquals(expectedOutput, outContent.toString());		
	}
}
