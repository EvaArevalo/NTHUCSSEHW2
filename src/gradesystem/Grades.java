package gradesystem;

public class Grades {
	
	/**
	 * CLASS PURPOSE: Grades class stores the information for each tuple of the database for the Grading System.
	 * 				  Its attributes are ID, name and the grades for each one of the five exams:lab1, lab2, lab3, 
	 * 				  midTerm, finalExam, and totalGrade.
	 * 
	 **/
	
	public static final int EXAMS_NUMBER = 5;
	
	int ID;
	int[] exams = new int[6];
	String name;
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE: Constructor, assigns values to each attribute of the instance. Calls CalculateTotalGrade()
	 * 			to assign a value to the totalGrade attribute, takes arguments for the rest of the arguments. 
	 * 
	 * @param name								String representing name for student
	 * @param id								int representing the ID of the student, id 0 is reserved for the class average
	 * @param lab1,lab2,lab3,midTerm,finalExam  int corresponding to the grades for those exams
	 * @param weights							float[5] array with the weight for each exam
	 **/
	public Grades(String name, int ID, int[] scores, float[] weights){
		
		this.ID = ID;
		this.name = name;

		for (int i=0; i<EXAMS_NUMBER; i++){
			exams[i] = scores[i];
		}
		
		exams[5] = calculateTotalGrade(weights);
		
	}
	
	/**
	 * TIME COMPLEXITY: O(n)
	 * PURPOSE: Calculates the total grade as a weighted average of the 5 grades in the attributes for the ID. The weighted average is 
	 * 			rounded to the closest integer.
	 * 
	 * @param weights	float[4] array for the weights of each exam
	 * @return total	int representing rounded weighted average of the 5 grades
	 * 
	 */
	public int calculateTotalGrade(float[] weights){

		float total = 0;
		
		for (int i=0; i<EXAMS_NUMBER; i++)
			total += exams[i] * weights[i];

		return Math.round(total);
	}
	
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE:	Getter method for the student's ID.
	 * @return this.ID  int representing student's ID
	 */
	public int getID(){
		return this.ID;
	}
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE:	Getter method for the student's name.
	 * @return this.name  string representing the student's name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE:	Getter method for the scores.
	 * @return this.exams  int[5] representing the grades for each exam
	 */
	public int[] getScores(){
		return this.exams;
	}
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE:	Getter method for the student's total grade.
	 * @return this.exams[5]  int representing the total averaged Grade
	 */
	public int getTotalGrade() {
		return this.exams[5];
	}
	
	/**
	 * TIME COMPLEXITY: O(n)
	 * PURPOSE:	Recalculates the Student's Total average grade with the weights provided.
	 * @param  weights		  float[4] array for the weights of each exam
	 * @return this.exams[5]  int representing the total averaged Grade
	 */
	public int resetTotalGrade(float[] weights) {
		this.exams[5] = calculateTotalGrade(weights);
		return this.exams[5];
	}
	
}
