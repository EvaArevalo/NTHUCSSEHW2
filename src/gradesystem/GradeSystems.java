package gradesystem;
import java.util.*;
import java.io.*;

public class GradeSystems {
	
	float[] weights;
	LinkedList<Grades> aList = new LinkedList<Grades>();
	
	GradeSystems(){
		
		weights = new float[]{0.1f,0.1f,0.1f,0.3f,0.4f};
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("gradeinput.txt"));
			String line = br.readLine();
			
			while (line != null){
				String[] IdInfo = line.split(" ");
				
				int ID = Integer.parseInt(IdInfo[0]);
				String name = IdInfo[1];
				int lab1 = Integer.parseInt(IdInfo[2]);
				int lab2 = Integer.parseInt(IdInfo[3]);
				int lab3 = Integer.parseInt(IdInfo[4]);
				int midTerm = Integer.parseInt(IdInfo[5]);
				int finalExam = Integer.parseInt(IdInfo[6]);
				
				Grades aGrade = new Grades(name,ID,lab1,lab2,lab3,midTerm,finalExam,weights);
				
				//Ordered insertion
				int index = 0;
				Grades element;
				
				while((element = aList.get(index)) != null) {
					if (aGrade.getTotalGrade() > element.getTotalGrade()) {
						aList.add(index, aGrade);
						break;
					}
					index++;
				}
				if(index == aList.size())
					aList.add(index,aGrade);
				
				line = br.readLine();
			}
			br.close();
		}
		catch(IOException e){}
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
	
	public void updateWeights(float[] weights){
		//suppose we receive list with 5 elements
		this.weights = weights;
	}

	public String getName(int ID){
		
		Iterator<Grades> it = aList.iterator();
		
		while(it.hasNext()){
			if (it.next().getID() == ID)
				return it.next().getName();
		}
		
		//if ID not found, which shouldn't happen
		return "";
	}
}
