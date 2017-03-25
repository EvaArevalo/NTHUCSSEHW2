package gradesystem;

@SuppressWarnings("serial")
/**
 * NoSuchCommandExceptions
 * 
 * When user enter an invalid command.
 */
public class NoSuchCommandExceptions extends Exception {
	@Override
	public String toString(){
		return "Invalid command!";
	}
}
