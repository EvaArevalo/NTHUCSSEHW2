package gradesystem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GradeSystemsTest {
	
	GradeSystems aSystem;
	
	@Before
	public void initialize(){
		try{
		aSystem = new GradeSystems();
		}catch(Exception e){}
	}

	/**
		case1:
			input: 985002001
			expected output: True
		case2:
			input: 915002001
			expected output: NoSuchIdExceptions

	 */
	@Test
	public void testContainsID() {
		assertTrue(aSystem.containsID(985002001));
		assertFalse(aSystem.containsID(958500));
	}

	@Test
	public void testShowGrade() {
		assertEquals(81,aSystem.showGrade(962001051)[0]);
		assertEquals(32,aSystem.showGrade(962001051)[1]);
		assertEquals(50,aSystem.showGrade(962001051)[2]);
		assertEquals(90,aSystem.showGrade(962001051)[3]);
		assertEquals(93,aSystem.showGrade(962001051)[4]);
		assertEquals(81,aSystem.showGrade(962001051)[5]);
	}

	@Test
	public void testShowRank() {
		assertEquals(4,aSystem.showRank(985002004));
		assertEquals(1,aSystem.showRank(985002509));
	}

	@Test
	public void testShowAvg() {
		assertEquals(90,aSystem.showAvg()[0]);
		assertEquals(88,aSystem.showAvg()[1]);
		assertEquals(89,aSystem.showAvg()[2]);
		assertEquals(90,aSystem.showAvg()[3]);
		assertEquals(90,aSystem.showAvg()[4]);
		assertEquals(90,aSystem.showAvg()[5]);
	}

	@Test
	public void testUpdateWeights() {
		float[] weights2 = new float[]{0.2f,0.2f, 0.2f,0.2f,0.2f};
		float[] weights1 = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		
		assertEquals(4,aSystem.showRank(985002004));
		aSystem.updateWeights(weights2);
		assertEquals(17,aSystem.showRank(985002004));
		aSystem.updateWeights(weights1);
	}

	@Test
	public void testGetName() {
		assertEquals("§õ«Â§Ê",aSystem.getName(962001051));
		assertEquals("ªLªä§±",aSystem.getName(985002002));
	}

}
