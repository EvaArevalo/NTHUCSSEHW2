package gradesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gradesystem.GradeSystems.ExamsName;

/**
 * class UI
 * 
 * UI()
 * checkId(id)
 * promptCommand()
 * promptId()
 * showFinishMsg()
 * showWelcomeMsg()
 *
 */
public class UI {

	/**
	 * constructor UI
	 * 
	 * @exception NoSuchIDExceptions, NoSuchCommandExceptions
	 */
	BufferedReader keyReader = new BufferedReader(new InputStreamReader(
			System.in));
	
	GradeSystems aGradeSystem;
	int userID;
	String userName;

	public UI() throws IOException {
		aGradeSystem = new GradeSystems();
		runPromptID();
	}
	
	public void runPromptID() throws IOException {
		try {
			while(promptID()) {
				runPromptCommand();					
			}
		} catch(NoSuchIDExceptions e){
			System.out.println(e);
			runPromptID();
		}
	}
	
	public void runPromptCommand() throws IOException {
		try {
			while(promptCommand()) {
				
			}
		} catch(NoSuchCommandExceptions e){
			System.out.println(e);
			runPromptCommand();
		} 	
	}
	
	public boolean checkID(int ID) {
		return aGradeSystem.containsID(ID);
	}
	
	public boolean promptCommand() throws NoSuchCommandExceptions, IOException  {
		System.out.println("Enter Command");
		System.out.println("\t(G)rade");
		System.out.println("\t(R)ank");
		System.out.println("\t(A)verage");
		System.out.println("\t(W)eights Update");
		System.out.println("\t(E)xit:");
		String line = keyReader.readLine();
		switch (line) {
		case "G": // show grades of the user
			int[] scores = aGradeSystem.showGrade(userID);
			System.out.println("Grades for " + userName + ":");
			for (ExamsName e : ExamsName.values())
				System.out.println("\t" + e.toString() + ": " + scores[e.getCode()]);
			break;
		case "R": // show rank of the user
			int rank = aGradeSystem.showRank(userID);
			System.out.println("Rank for " + userName + ": " + rank);
			break;
		case "A": // show average grades of the class
			int[] classAvgScore = aGradeSystem.showAvg();
			System.out.println("Average grades for the class:");
			for (ExamsName e : ExamsName.values())
				System.out.println("\t" + e.toString() + ": " + classAvgScore[e.getCode()]);
			break;
		case "W": // update weights
			System.out.println("Current weights for each grade:");
			for (ExamsName e : ExamsName.values())
				if (!e.toString().equals("totalGrade")) // totalGrade does't have weight
					System.out.println("\t" + e.toString() + ": " + (int)(aGradeSystem.weights[e.getCode()]*100) + "%");
			System.out.println("Enter new weights (in percentage):");
			float[] newWeights = new float[5];
			for (ExamsName e : ExamsName.values())
				if (!e.toString().equals("totalGrade")) {
					System.out.print(e.toString() + ": ");
					newWeights[e.getCode()] = (float) (Integer.parseInt(keyReader.readLine())) / 100;
				}
			System.out.println("Comfirm the new weights:");
			for (ExamsName e : ExamsName.values())
				if (!e.toString().equals("totalGrade")) // totalGrade does't have weight
					System.out.println("\t" + e.toString() + ": " + (int)(newWeights[e.getCode()]*100) + "%");
			System.out.print("Save? (Y/N): ");
			if (keyReader.readLine().equals("Y")) aGradeSystem.updateWeights(newWeights);
			break;
		case "E":
			return false;
		default: // invalid command
			throw new NoSuchCommandExceptions();
		}
		return true;
	}
	
	public boolean promptID() throws NoSuchIDExceptions, IOException {
		System.out.println("Enter your ID or 'Q' to quit:");
		String line = keyReader.readLine();
		if (line.equals("Q")) {
			showFinishMsg();
			return false;
		} else {
			int ID = Integer.parseInt(line);
			if (!checkID(ID)){
				throw new NoSuchIDExceptions();
			} else {
				userID = ID;
				showWelcomeMsg(ID);
				return true;
			}
		}
	}
	
	public void showFinishMsg() {
		System.out.println("Goodbye!");
	}
	
	public void showWelcomeMsg(int ID) {
		userName = aGradeSystem.getName(ID);
		System.out.println("Welcome " + userName + " !");
	}
}
