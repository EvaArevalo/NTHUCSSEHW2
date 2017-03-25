package gradesystem;

import java.io.InputStream;

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
	GradeSystems aGradeSystem;
	int userID;
	String userName;
	
	public enum ExamsName{
		lab1, lab2, lab3, midTerm, finalExam, totalGrade
	}
	public UI(){
		aGradeSystem = new GradeSystems();
		runPromptID();
	}
	
	public void runPromptID() {
		try {
			while(promptID()) {
				runPromptCommand();					
			}
		} catch(NoSuchIDExceptions e){
			System.out.println(e);
			runPromptID();
		}
	}
	
	public void runPromptCommand(){
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
	
	public boolean promptCommand() throws NoSuchCommandExceptions {
		System.out.println("Enter Command");
		System.out.println("\t(G)rade");
		System.out.println("\t(R)ank");
		System.out.println("\t(A)verage");
		System.out.println("\t(W)eight Update");
		System.out.println("\t(E)xit:");
		String line = System.console().readLine();
		switch (line) {
		case "G":
			int[] scores = aGradeSystem.showGrade(userID);
			System.out.println("Grades for " + userName + " :");
			for (ExamsName e : ExamsName.values())
				System.out.println("\t" + e.toString() + scores[e.ordinal()]);
			break;
		case "R":
			int rank = aGradeSystem.showRank(userID);
			System.out.println("Rank for " + userName + " : " + rank);
			break;
		case "A":
			break;
		case "W":
			break;
		case "E":
			return false;
		default: // invalid command
			throw new NoSuchCommandExceptions();
		}
		return true;
	}
	
	public boolean promptID() throws NoSuchIDExceptions{
		System.out.println("Enter your ID or 'Q' to quit:");
		String line = System.console().readLine();
		if (line == "Q") return false;
		else {
			int ID = Integer.parseInt(line);
			if (!checkID(ID)){
				throw new NoSuchIDExceptions();
			} else {
				userID = ID;
				showWelcomeMsg(ID);
			}
		}
		return true;
	}
	
	public void showFinishMsg() {
		
	}
	
	public void showWelcomeMsg(int ID) {
		userName = aGradeSystem.getName(ID);
		System.out.println("Welcome " + userName + " !");
	}
}
