import io.ExceptionHandler;

/**
 * This class acts as the boundary between the <code>main</code> function in <code>RestaurantApp</code> and the user.
 * It is in charge of printing information to the console and getting user input.
 *
 */
public class RestaurantBoundary {
	
	/**
	 * Prints a splash message for when the app is started.
	 */
	public static void printSplash() {
		System.out.println("██████╗░██╗███████╗███████╗░█████╗░  ██╗░░██╗░█████╗░██╗░░░██╗░██████╗███████╗"); 
    System.out.println("██╔══██╗██║╚════██║╚════██║██╔══██╗  ██║░░██║██╔══██╗██║░░░██║██╔════╝██╔════╝");  
    System.out.println("██████╔╝██║░░███╔═╝░░███╔═╝███████║  ███████║██║░░██║██║░░░██║╚█████╗░█████╗░░");  
    System.out.println("██╔═══╝░██║██╔══╝░░██╔══╝░░██╔══██║  ██╔══██║██║░░██║██║░░░██║░╚═══██╗██╔══╝░░");  
    System.out.println("██║░░░░░██║███████╗███████╗██║░░██║  ██║░░██║╚█████╔╝╚██████╔╝██████╔╝███████╗");  
    System.out.println("╚═╝░░░░░╚═╝╚══════╝╚══════╝╚═╝░░╚═╝  ╚═╝░░╚═╝░╚════╝░░╚═════╝░╚═════╝░╚══════╝");   

		System.out.println("Enter (1) to proceed.");
		ExceptionHandler.scanIntRange(1, 1);
	}
	
	/**
	 * Prompts the user to continue.
	 * @return the user's choice
	 */
	public static int getContinue() {
		System.out.println("Task completed. Would you like to continue?");
		System.out.println("(0) Quit");
		System.out.println("(1) Proceed");
		return ExceptionHandler.scanIntRange(0, 1);
	}
	
	/**
	 * Prints a shutdown message to the console.
	 */
	public static void printExit() {
		System.out.println("Thank you for using the application.");
		System.out.println("Now shutting down. Bye bye!");
	}
	
	/**
	 * Prompts the user to choose among the list of tasks available.
	 * @return the user's choice
	 */
	public static int getTask() {
		System.out.println();
		System.out.println("Hi! Please choose an option to proceed.");
		System.out.println("(1)  Create menu/promo item");
		System.out.println("(2)  Update menu/promotion item");
		System.out.println("(3)  Remove menu/promotion item");
		System.out.println("(4)  Print menu");
		System.out.println("(5)  Create order");
		System.out.println("(6)  View order");
		System.out.println("(7)  Update order");
		System.out.println("(8)  Delete order");
		System.out.println("(9)  Create reservation");
		System.out.println("(10) Remove reservation");
		System.out.println("(11) Check reservation details");
		System.out.println("(12) Print all reservations");
		System.out.println("(13) Check table availability");
		System.out.println("(14) Print order invoice");
		System.out.println("(15) Print sale revenue report");
		System.out.println("(0)  Exit");
		return ExceptionHandler.scanIntRange(0, 15);
	}
	
	/**
	 * Prints an error message notifying the user of data importing problems.
	 */
	public static void importError() {
		System.out.println("Importing of data FAILED!");
		System.out.println("Please check that you have entered the correct file path to ./src/data/ in the PathHelper!");
	}
	
	/**
	 * Prints an error message notifying the user of data exporting problems.
	 */
	public static void exportError() {
		System.out.println("Exporting of data FAILED!");
		System.out.println("Please check that you have entered the correct file path to ./src/data/ in the PathHelper!");
	}

}
