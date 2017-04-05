package gradesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gradesystem.GradeSystems.ExamsName;

/**
 * CLASS PURPOSE: UI class handles user input and calls the appropriate methods from the GradeSystem class
 * 				  according to the commands input by the user.
 * 
 **/
public class UI {

	BufferedReader keyReader = new BufferedReader(new InputStreamReader(
			System.in));
	
	GradeSystems aGradeSystem;
	int userID;
	String userName;
	
	/**
	 * TIME COMPLEXITY: N/A (UI)
	 * PURPOSE:	Class Constructor. Runs the runPromptID() function.
	 * 
	 * @exception IOException
	 */
	public UI() throws IOException {
		aGradeSystem = new GradeSystems();
		runPromptID();
	}
	
	/**
	 * TIME COMPLEXITY: N/A (UI)
	 * PURPOSE:	Wrapper function for the promptID() function. Prompts the user to enter ID
	 * 			and handles user input in this state of the system of waiting for ID to query.
	 * 
	 * @exception IOException
	 */
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
	
	/**
	 * TIME COMPLEXITY: N/A (UI)
	 * PURPOSE:	Wrapper function for the promptCommand() function. Prompts the user to enter 
	 * 			a command after entering an ID and handles user input in this state of the system 
	 * 			of querying a student ID.
	 * 
	 * @exception IOException
	 */
	public void runPromptCommand() throws IOException {
		try {
			while(promptCommand()) {
				
			}
		} catch(NoSuchCommandExceptions e){
			System.out.println(e);
			runPromptCommand();
		} 	
	}
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE:	Checks whether an ID is in the GradeSystem. Calls the containsID from the
	 * 			aGradeSystem GradeSystem instance.
	 * 
	 * @return True/False	boolean	 true if the ID is in sList, false otherwise
	 */
	public boolean checkID(int ID) {
		return aGradeSystem.containsID(ID);
	}
	
	/**
	 * TIME COMPLEXITY: N/A (UI)
	 * PURPOSE:	Function for prompting user to input a command after inputing an ID and 
	 * 			handling user input during this stage of querying ID. Does the following:
	 * 			[1] Prints the list of possible commands.
	 * 			[2] Decodes the commands, by user input:
	 * 				["G"] Handles the Grade command, prints the grades for each of the 5 
	 * 					  exams and the final grade of the student. Calls the showGrade(ID)
	 * 					  method from the GradeSystem class. 
	 * 				["R"] Handles the Rank command, prints the rank of the student in the 
	 * 					  GradeSystem. Calls the showRank(ID) method from the GradeSystem
	 * 					  class.
	 * 				["A"] Handles the Average command, prints the average for the scores of 
	 *					  all students for each one of the 5 exams and the total average 
	 *					  score. Calls the showAvg() method from the GradeSystem class.
	 * 				["W"] Handles the Weights command.Does the following:
	 * 					  [a] Prints the list of the weights currently in the system for
	 * 						  each exam.
	 * 					  [b] Prompts user to input a new weight for each exam
	 * 					  [c] Prints the list of the new weights input by user
	 * 					  [d] Prompts the user to confirm or deny the change ("Y" or "N")
	 * 					  [e] Changes the weights in the GradeSystem by calling the 
	 * 						  method updateWeights() with the new weights list as arguments
	 * 				["E"] Handles the Exit command. Exits the ID querying state and returns
	 * 					  to the Waiting input for ID to query state.
	 * 				["DEFAULT"] Any other command, throw a NoSuchCommandException
	 * 
	 * @exception NoSuchCommandExceptions, IOException
	 */
	public boolean promptCommand() throws NoSuchCommandExceptions, IOException  {
		System.out.println("Enter Command");
		System.out.println("\t(G)rade");
		System.out.println("\t(R)ank");
		System.out.println("\t(A)verage");
		System.out.println("\t(W)eights Update");
		System.out.println("\t(E)xit:");
		String line = keyReader.readLine();
		System.out.println("CMD:"+line);
		switch (line) {
		case "G": // show grades of the user
			int[] scores = aGradeSystem.showGrade(userID);
			System.out.println("Grades for " + userName + ":");
			for (ExamsName e : ExamsName.values())
				if (scores[e.getCode()]<60)
					System.out.println("\t" + e.toString() + ": " + scores[e.getCode()] +" (!)");
				else
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
					System.out.print("\t" + e.toString() + ": ");
					newWeights[e.getCode()] = (float) (Integer.parseInt(keyReader.readLine())) / 100;
				}
			System.out.println("Confirm the new weights:");
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
	
	/**
	 * TIME COMPLEXITY: N/A (UI)
	 * PURPOSE:	Function for prompting user to input an ID and handling user input during this
	 * 			state of the system of waiting for ID to query. Does the following:
	 * 			[1] Prints the message "Enter your ID or 'Q' to quit:"
	 * 			[2] If the line is the letter "Q", it quits the system by calling the 
	 * 				showWelcomeMessage() function
	 * 			[3] Otherwise we check if the line is a valid line, if not, we throw a
	 * 				NoSuchIDException
	 * 			[4] If the ID is valid, we show the welcome message by calling the 
	 * 				showWelcomeMsg(ID) message
	 * 
	 * @exception NoSuchIDExceptions, IOException
	 */
	public boolean promptID() throws NoSuchIDExceptions, IOException {
		System.out.println("Enter your ID or 'Q' to quit:");
		String line = keyReader.readLine();
		System.out.println("ID:"+line);
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
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE:	Prints the goodbye message "Goodbye".
	 */
	public void showFinishMsg() {
		System.out.println("Goodbye!");
	}
	
	/**
	 * TIME COMPLEXITY: O(1)
	 * PURPOSE:	Gets the name of the user identified with the most recently inputed ID
	 * 			and outputs the "Welcome username!" message.
	 * 
	 * @param  ID		int 	 ID of the student whose records we want to query
	 */
	public void showWelcomeMsg(int ID) {
		userName = aGradeSystem.getName(ID);
		System.out.println("Welcome " + userName + " !");
	}
}
