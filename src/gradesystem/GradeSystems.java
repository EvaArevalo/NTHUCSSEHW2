package gradesystem;
import java.util.*;

import gradesystem.Grades.ExamsName;

import java.io.*;

public class GradeSystems {
	
	float[] weights;
	LinkedList<Grades> aList = new LinkedList<Grades>();
	Grades classAvg;
	
	GradeSystems(){
		
		weights = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("gradeinput.txt"));
			String line = br.readLine();
			
			while (line != null) {
				String[] IdInfo = line.split(" ");
				
				Grades aGrade = buildGrade(IdInfo);				
				orderedInsert(aGrade);
				
				line = br.readLine();
			}
			classAvg = calculateClassAvg();
			
			br.close();
		} catch(IOException e){}
	}
	
	public boolean containsID(int ID){
		
		Iterator<Grades> it = aList.iterator();
		
		while(it.hasNext()){
			if (it.next().getID() == ID)
				return true;
		}
		
		return false;
	}
	
	public int[] showGrade(int ID){
		
		Iterator<Grades> it = aList.iterator();
		
		while(it.hasNext()){
			if (it.next().getID() == ID)
				return it.next().getScores();
		}
		
		//if ID not found, which shouldn't happen
		return new int[]{0};
		
	}
	
	public int showRank(int ID){
	
		int index = 0;
		Grades element;
		
		while((element = aList.get(index)) != null) {
			if (element.getID() == ID) {
				return index + 1;
			}
			index++;
		}
		
		//if ID not found, which shouldn't happen
		return 0;
	}
	
	public int[] showAvg() {
		return classAvg.getScores();
	}
	
	public void updateWeights(float[] weights) {
		//suppose we receive list with 5 elements
		this.weights = weights;
	}

	public String getName(int ID) {
		
		Iterator<Grades> it = aList.iterator();
		
		while(it.hasNext()){
			if (it.next().getID() == ID)
				return it.next().getName();
		}
		
		//if ID not found, which shouldn't happen
		return "";
	}
	
	private Grades buildGrade(String[] IdInfo) {
		
		int ID = Integer.parseInt(IdInfo[0]);
		String name = IdInfo[1];
		int lab1 = Integer.parseInt(IdInfo[2]);
		int lab2 = Integer.parseInt(IdInfo[3]);
		int lab3 = Integer.parseInt(IdInfo[4]);
		int midTerm = Integer.parseInt(IdInfo[5]);
		int finalExam = Integer.parseInt(IdInfo[6]);
		
		return new Grades(name,ID,lab1,lab2,lab3,midTerm,finalExam,weights);
	}
	
	private void orderedInsert(Grades insertedGrade) {
		int index = 0;
		Grades element;
		
		while((element = aList.get(index)) != null) {
			if (insertedGrade.getTotalGrade() > element.getTotalGrade()) {
				aList.add(index, insertedGrade);
				break;
			}
			index++;
		}
		if(index == aList.size())
			aList.add(index, insertedGrade);
	}
	
	private Grades calculateClassAvg() {
		
		Iterator<Grades> it = aList.iterator();
		int lab1 = 0, lab2 = 0, lab3 = 0, midTerm = 0, finalExam = 0;
		
		while(it.hasNext()) {
			int[] scores = it.next().getScores();
			lab1 	+= scores[ExamsName.lab1.ordinal()];
			lab2 	+= scores[ExamsName.lab2.ordinal()];
			lab3 	+= scores[ExamsName.lab3.ordinal()];
			midTerm += scores[ExamsName.midTerm.ordinal()];
			finalExam += scores[ExamsName.finalExam.ordinal()];
		}		
		return new Grades("class",0,lab1,lab2,lab3,midTerm,finalExam,weights);
	}
}
