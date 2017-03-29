package gradesystem;

@SuppressWarnings("serial")
/**
 * PURPOSE: Defines NoSuchIDExceptions, inherits from exceptions and is triggered when an ID that is not inside the GradeSystem is
 * 			input by user.
 */
public class NoSuchIDExceptions extends Exception {
	@Override
	public String toString(){
		return "No such ID in the system!";
	}
}
