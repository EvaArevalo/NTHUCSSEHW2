package gradesystem;

public class Grades {
	
	/**
	 * Grades class stores the information for each
	 * tuple of the database for the Grading System.
	 * Its attributes are ID, name and the grades for
	 * each one of the five exams:lab1, lab2, lab3, 
	 * midTerm, finalExam, and totalGrade.
	 * 
	 **/
	
	public enum ExamsName{
		lab1, lab2, lab3, midTerm, finalExam, totalGrade
	}
	
	int ID;
	int[] exams;
	String name;
	
	public Grades(String name, int ID, int lab1, int lab2, 
			int lab3, int midTerm, int finalExam, float[] weights){
		/**
		 * Constructor, assigns values to each attribute of the 
		 * instance. Calls CalculateTotalGrade() to assign a value
		 * to the totalGrade attribute, takes arguments for the 
		 * rest of the arguments.
		 * 
		 **/
		
		this.ID = ID;
		this.name = name;
		exams[ExamsName.lab1.ordinal()] = lab1;
		exams[ExamsName.lab2.ordinal()] = lab2;
		exams[ExamsName.lab3.ordinal()] = lab3;
		exams[ExamsName.midTerm.ordinal()] = midTerm;
		exams[ExamsName.finalExam.ordinal()] = finalExam;
		exams[ExamsName.totalGrade.ordinal()] = calculateTotalGrade(weights);
		
	}
	
	public int calculateTotalGrade(float[] weights){
		/**
		 * Calculates the total grade as a weighted average of the 5
		 * grades in the attributes for the ID. The weighted average is 
		 * rounded to the closest integer.
		 **/
		
		float total = 0;
		
		for (int i=0; i<5; i++)
			total += exams[i] * weights[i];

		return Math.round(total);
	}
	
	public int getID(){
		/**
		 * Getter method for the ID attribute.
		 **/
		return this.ID;
	}
	
	
	public int[] getScores(){
		/**
		 * Getter method for the Exams array of grades.
		 **/
		return this.exams;
	}

	public int getTotalGrade() {
		/**
		 * Getter method for the totalGrade score, last entry of exams array.
		 **/
		return this.exams[5];
	}
	
}
