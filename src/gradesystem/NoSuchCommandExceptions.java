package gradesystem;

@SuppressWarnings("serial")
/**
 * PURPOSE: Defines NoSuchCommandExceptions, inherits from exceptions and is triggered when a non recognized command is
 * 			input by user.
 */
public class NoSuchCommandExceptions extends Exception {
	@Override
	public String toString(){
		return "Invalid command!";
	}
}
