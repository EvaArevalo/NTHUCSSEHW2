package gradesystem;

public class Main {

	/**
	 * method main
	 * 
	 * Construct aUI.
	 * 
	 * @param args
	 * @exception NoSuchIDExceptions, NoSuchCommandExceptions
	 */
	public static void main(String[] args) {
		// try { call UI() 建構 aUI } end try
		// catch (NoSuchIDExceptions      e1) {print msg1} //ex ID錯了!
		// catch (NoSuchCommandExceptions e2) {print msg2}//ex指令錯了!

	}
	
	/**
	 * NoSuchCommandExceptions
	 * 
	 * When user enter an invalid command.
	 */
	public class NoSuchCommandExceptions extends Exception {

	}
	
	/**
	 * NoSuchIDExceptions
	 * 
	 * When user enter an ID that is not in the system.
	 */
	public class NoSuchIDExceptions extends Exception {
		
	}
	
}