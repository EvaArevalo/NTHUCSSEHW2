package gradesystem;
import java.util.*;

import gradesystem.Grades.ExamsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class GradeSystems {
	
	float[] weights;
	LinkedList<Grades> aList = new LinkedList<Grades>();
	Grades classAvg;
	
	GradeSystems() throws IOException {
		
		weights = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		Path path = FileSystems.getDefault().getPath(".", "gradeinput.txt");
	    BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String line = br.readLine();
		
		while (line != null) {
			String[] IdInfo = line.split(" ");
			
			Grades aGrade = buildGrade(IdInfo);
			orderedInsert(aGrade);
			
			line = br.readLine();
		}
		classAvg = calculateClassAvg();
		
		br.close();
	}
	
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
	
	private Grades calculateClassAvg() {
		
		Iterator<Grades> it = aList.iterator();
		Grades g;
		int lab1 = 0, lab2 = 0, lab3 = 0, midTerm = 0, finalExam = 0, num = aList.size();
		
		while(it.hasNext()) {
			g = it.next();
			int[] scores = g.getScores();
			lab1 	+= scores[ExamsName.lab1.getCode()];
			lab2 	+= scores[ExamsName.lab2.getCode()];
			lab3 	+= scores[ExamsName.lab3.getCode()];
			midTerm += scores[ExamsName.midTerm.getCode()];
			finalExam += scores[ExamsName.finalExam.getCode()];
		}
		
		return new Grades("class",0,lab1/num,lab2/num,lab3/num,midTerm/num,finalExam/num,weights);
	}
}
