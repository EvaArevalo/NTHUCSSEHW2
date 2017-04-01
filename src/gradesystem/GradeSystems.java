package gradesystem;
import java.util.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * CLASS PURPOSE: The GradeSystems defines a System that keeps records for each students of their grades and their final scores,
 * 				  as well as keeping the average score for the entire class.
 * 
 * DATA STRUCTURES: We used a linked list of instances of the Grades class, one per student. 
 */
public class GradeSystems {
	
	public static final int EXAMS_NUMBER = 5;
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
	float[] weights;
	LinkedList<Grades> aList = new LinkedList<Grades>();
	Grades classAvg;

	/**
	 * TIME COMPLEXITY: O(n^2) worst case
	 * PURPOSE: Class constructor. Builds the Grades Systems by:
	 * 			[1] Reading grades from input file "gradeinput.txt"
	 * 			[2] Creating a Grades instance for each student/ID  .
	 * 			[3] Calculates the class Average.
	 * NOTE:	Supports UTF-8 Characters.
	 * 
	 * @throws IOException
	 */
	GradeSystems() throws IOException {
		
		//Default values for weights
		weights = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		
		Path path = FileSystems.getDefault().getPath(".", "gradeinput.txt");
	    BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String line = br.readLine();
		
		while (line != null) {
			Grades aGrade = buildGrade(line);
			orderedInsert(aGrade);
			line = br.readLine();
		}
		
		classAvg = calculateClassAvg();
		br.close();
	}
	
	/**
	 * TIME COMPLEXITY: O(n) worst case
	 * PURPOSE:	Checks whether an ID is in the GradeSystem. Iterates linearly
	 * 			through the aListand checks their ID.
	 * 
	 * @param  ID			int 	 ID of the student
	 * @return True/False	boolean	 true if the ID is in sList, false otherwise
	 */
	public boolean containsID(int ID){
		
		Iterator<Grades> it = aList.iterator();
		Grades g;
		
		while(it.hasNext()){
			g = it.next();
			if (g.getID() == ID)
				return true;
		}
		
		return false;
	}
	
	/**
	 * TIME COMPLEXITY: O(n) worst case
	 * PURPOSE:	Gets the scores for each one of the 5 exams and the final grade
	 * 			of the student identified with the ID.
	 * 
	 * @param   ID				 int    ID of the student whose grade we want
	 * @return  g.getScores()	 int[5]	Array of scores for ID
	 */
	public int[] showGrade(int ID){
		
		Iterator<Grades> it = aList.iterator();
		Grades g;
		
		while(it.hasNext()){
			g = it.next();
			if (g.getID() == ID)
				return g.getScores();
		}
		
		//if ID not found, which shouldn't happen
		return new int[]{0};
		
	}
	
	/**
	 * TIME COMPLEXITY: O(n) worst case
	 * PURPOSE:	Gets the rank of a student in the Grade System.
	 * 
	 * @param  ID	 int  ID of the student whose rank we want
	 * @return rank	 int  Shows the rank of the student, if other students have same final grade, all of them share same rank
	 */
	public int showRank(int ID){
	
		int index = 0;
		Grades element;
		int IDscore = 0;
		
		while((element = aList.get(index)) != null) {
			if (element.getID() == ID) {
				IDscore = element.getTotalGrade();
				break;
			}
			index++;
		}
		
		//Check if students with same grade
		if(index == 0) 
			return 1;

		element = aList.get(--index);
		
		while(element.getTotalGrade()==IDscore)
			if(index > 0)
				element = aList.get(--index);
			else
				return 1;

		return index+2;
		
	}
	
	/**
	 * TIME COMPLEXITY: O(1) 
	 * PURPOSE:	Gets the array of 6 average grades for the students in the system
	 * 			for each exam ant for the final grade.
	 * 
	 * @return classAvg.getScores() int[5] Representing the grades for each exam
	 */
	public int[] showAvg() {
		return classAvg.getScores();
	}
	
	/**
	 * TIME COMPLEXITY: O(n^2) 
	 * PURPOSE: Recalculates all grades for students based on new weights. 
	 * 			Iterates through the aList of Grade instances and calls the 
	 * 			resetTotalGrade(weights) for each of them.
	 * 
	 * @param  weights 		float[5]  Representing the grades for each exam
	 */
	public void updateWeights(float[] weights) {
		//suppose we receive list with 5 elements
		this.weights = weights;

		List<Grades> tmp = new ArrayList<Grades>();
		for (Grades g : aList) {
			g.resetTotalGrade(weights);
			tmp.add(g);
		}
		
		aList.clear();
		for (Grades g : tmp) {
			orderedInsert(g);
		}
	}

	/**
	 * TIME COMPLEXITY: O(1) 
	 * PURPOSE: Gets the name of a student ID in the system.
	 * 
	 * @param  ID			Int		ID of the student 
	 * @return ID.getName()	String	The string corresponding to the ID's name
	 */
	public String getName(int ID) {
		
		Iterator<Grades> it = aList.iterator();
		Grades g;
		
		while(it.hasNext()) {
			g = it.next();
			if (g.getID() == ID)
				return g.getName();
		}
		
		//if ID not found, which shouldn't happen
		return "";
	}
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE: Parses the line read from input file into a Grades instance for the ID.
	 * 
	 * @param	IdInfo	String[6]   An array of ID, name, and 5 grades.
	 * @returns Grades	new Grades  Instance for the ID
	 */
	private Grades buildGrade(String line) {
		
		String[] IdInfo = line.split(" ");
		int[] scores = new int[EXAMS_NUMBER];
		
		int ID = Integer.parseInt(IdInfo[0]);
		String name = IdInfo[1];
		scores[0] = Integer.parseInt(IdInfo[2]);
		scores[1] = Integer.parseInt(IdInfo[3]);
		scores[2] = Integer.parseInt(IdInfo[4]);
		scores[3] = Integer.parseInt(IdInfo[5]);
		scores[4] = Integer.parseInt(IdInfo[6]);
		
		return new Grades(name,ID,scores,weights);
	}
	
	/**
	 * TIME COMPLEXITY: O(n) worst case
	 * PURPOSE: Inserts a Grade instance into the Linked List aList in ascending order. 
	 * 
	 * @param insertedGrade	 Grades	 The instance we want to insert into aList
	 */
	private void orderedInsert(Grades insertedGrade) {
		int index = 0;
		Grades element;
		
		while(index < aList.size()) {
			element = aList.get(index);
			if (insertedGrade.getTotalGrade() > element.getTotalGrade()) {
				aList.add(index, insertedGrade);
				break;
			}
			index++;
		}
		if(index == aList.size())
			aList.add(index, insertedGrade);
	}
	
	/**
	 * TIME COMPLEXITY: O(n)
	 * PURPOSE: Calculates the average of all students in the system for each of the 5 
	 * 			exams and for the final grade. Iterates linearly through the aList.
	 * 
	 * @return new Grades instance	  Grades  A new instance of grades representing the
	 * 									 	  averaged grades of all students in the system,
	 * 									 	  with 0 as ID and "class" as name.
	 */
	private Grades calculateClassAvg() {
		
		Iterator<Grades> it = aList.iterator();
		Grades g;
		int[] totalScores = new int[EXAMS_NUMBER];
		float[] avgscores = new float[EXAMS_NUMBER];
		
		while(it.hasNext()) {
			g = it.next();
			int[] scores = g.getScores();
			for (int i=0; i<EXAMS_NUMBER; i++){
				totalScores[i] += scores[i];
			}
		}
		
		for (int i=0; i<EXAMS_NUMBER; i++){
			avgscores[i] = (float) (totalScores[i]) / aList.size();
			totalScores[i] = Math.round(avgscores[i]);
		}
		
		return new Grades("class",0,totalScores,weights);
	}
}
