package gradesystem;

@SuppressWarnings("serial")
/**
 * NoSuchIDExceptions
 * 
 * When user enter an ID that is not in the system.
 */
public class NoSuchIDExceptions extends Exception {
	@Override
	public String toString(){
		return "No such ID in the system!";
	}
}
