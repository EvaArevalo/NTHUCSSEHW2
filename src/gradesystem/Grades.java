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
	
	public enum ExamsName {
		lab1(0), lab2(1), lab3(2), midTerm(3), finalExam(4), totalGrade(5);
		private int code;
		private ExamsName(int code) {
			this.code = code;
		}
		public int getCode(){
			return this.code;
		}
	}
	
	int ID;
	int[] exams = new int[6];
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
		exams[ExamsName.lab1.getCode()] = lab1;
		exams[ExamsName.lab2.getCode()] = lab2;
		exams[ExamsName.lab3.getCode()] = lab3;
		exams[ExamsName.midTerm.getCode()] = midTerm;
		exams[ExamsName.finalExam.getCode()] = finalExam;
		exams[ExamsName.totalGrade.getCode()] = calculateTotalGrade(weights);
		
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
	
	public String getName(){
		return this.name;
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
	
	public void resetTotalGrade(float[] weights) {
		this.exams[5] = calculateTotalGrade(weights);
	}
	
}
