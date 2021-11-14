package reservation;

import io.ExceptionHandler;

//import jdk.vm.ci.code.site.ExceptionHandler;

/**
 * Acts as a class to take table-related inputs from users and print the outputs to show the users
 */
public class TableBoundary {
	public static final int TOTALTABLES = 20;

	/**
	 * Input method to ask for table ID
	 * @return table ID
	 */
	public static int askTableID() {
		System.out.println("Please enter the table ID:");
		int tableID = ExceptionHandler.scanIntRange(1, TOTALTABLES);
		return (tableID);
	}

	/**
	 * Input method to ask for the choice to print the status of either one table or all tables
	 * @return choice
	 */
	public static int askPrintOption() {
		System.out.println("Please select an option:");
		System.out.println("(1) Print one table's availability status");
		System.out.println("(2) Print all tables' availability status");
		int choice = ExceptionHandler.scanIntRange(1, 2);
		return choice;
	}

	/**
	 * @param tableID
	 * @param isAvailable
	 * Print the status of a single table
	 */
	public static void printSingleTableStatus(int tableID, boolean isAvailable) {
		if (isAvailable)
			System.out.println("Table " + tableID + " is currently available.");
		else
			System.out.println("Table " + tableID + " is currently occupied.");
	}

	/**
	 * The printing header for printing functions
	 */
	public static void printTableStatusHeader() {
		System.out.println("Table availability status:");
		System.out.println("---------------------------------------");
	}

	/**
	 * Print the status of the table
	 * the calling method will pass in the table ID and the method which returns the table status
   * @param tableID
	 * @param isAvailable
	 */
	public static void printThatTableStatus(int tableID, boolean isAvailable) {
		String s = String.valueOf(tableID);
		if (isAvailable)
			System.out.printf("Table %-2s availability status: Available\n", s);
		else
			System.out.printf("Table %-2s availability status: Occupied\n", s);
	}

}
