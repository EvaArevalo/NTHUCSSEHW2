package gradesystem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GradesTest {
	
	Grades g1a,g1b,g2;
	float[] weights1, weights2;
	int[] scores1, scores2;

	
	/**
	 * Applied parameters: Student 962001051 with Grades: 81 32 50 90 93 
		case1:
			input: (0.1,0.1,0.1,0.3,0.4) //weights
			expected output: (81*0.1+32*0.1+50*0.1+90*0.3+93*0.4=80.5四捨五入81)
		case2:
			input: (0.2,0.2,0.2,0.2,0.2)
			expected output:(81*0.2+32*0.2+50*0.2+90*0.2+93*0.2=69.2四捨五入69)

	 */
	@Before
	public void initialize(){
		weights1 = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		weights2 = new float[]{0.2f,0.2f, 0.2f,0.2f,0.2f};
		scores1 = new int[] {81, 32, 50, 90, 93};
		scores2 = new int[] {99, 81, 91, 92, 95};
		g1a = new Grades ("李威廷", 962001051, scores1, weights1);
		g1b = new Grades ("李威廷", 962001051, scores1, weights2);
		g2 = new Grades("林芯妤",985002002,scores2,weights1);
	}
	
	@Test
	public void testCalculateTotalGrade() {
		assertEquals(81,g1a.calculateTotalGrade(weights1));
		assertEquals(69,g1b.calculateTotalGrade(weights2));
	}

	@Test
	public void testGetID() {
		assertEquals(962001051,g1a.getID());
		assertEquals(985002002,g2.getID());
	}

	@Test
	public void testGetName() {
		assertEquals("李威廷",g1a.getName());
		assertEquals("林芯妤",g2.getName());
	}

	@Test
	public void testGetScores() {
		assertEquals(81,g1a.getScores()[0]);
		assertEquals(32,g1a.getScores()[1]);
		assertEquals(50,g1a.getScores()[2]);
		assertEquals(90,g1a.getScores()[3]);
		assertEquals(93,g1a.getScores()[4]);
	}

	@Test
	public void testGetTotalGrade() {
		assertEquals(81,g1a.getTotalGrade());
		assertEquals(69,g1b.getTotalGrade());
	}

	@Test
	public void testResetTotalGrade() {
		assertEquals(69,g1a.resetTotalGrade(weights2));
		assertEquals(92,g2.resetTotalGrade(weights2));
	}

}
