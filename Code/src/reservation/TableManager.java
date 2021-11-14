package reservation;

import java.util.ArrayList;

/**
 * Represents a table manager in the system.
 * A table manager can manage all the tables in the restaurant
 */

public class TableManager {
	/**
	 * An ArrayList that holds all the tables
	 */
	private static ArrayList<Table> tables;
	/**
	 * A static variable representing the total number of tables
	 */
	private static final int TOTALTABLES = 20;// assume to be multiple of 5
	/**
	 * Constructor to create 20 tables
	 * tables with different seat capacity have the same quantity
	 * table ID is from 1 to 20
	 */
	static {
		if (TableManager.tables == null) {
			TableManager.tables = new ArrayList<Table>();
			int j = 1;
			for (int i = 2; i <= 10; i += 2) {
				for (int k = 1; k <= TOTALTABLES / 5; k++) {
					tables.add(new Table(j, i));
					j++;
				}
			}
		}
	}
	

	/**
	 * @return total number of tables
	 */
	public static int getNumberOfTables() {
		return TOTALTABLES;
	}

	/**
	 * @param id
	 * @return table instance by ID
	 */
	public static Table getTableByID(int id) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getTableID() == id) {
				return tables.get(i);
			}
		}
		return null;
	}

	/**
	 * @param index
	 * @return table capacity by table ID
	 */
	public static int getCapacityByID(int index) {
		return tables.get(index).getSeatCap();
	}

	/**
	 * Set the status of the table instance from its table ID
	 * @param tableID
	 * @param isAvailable
	 */
	public static void setTableAvailability(int tableID, boolean isAvailable) {
		tables.get(tableID - 1).setStatus(isAvailable);
	}

	/**
	 * Print out status for either a single table or all the tables
	 */
	public static void printTableAvailability() {
		int choice = TableBoundary.askPrintOption();
		if (choice == 1) {
			int tableID = TableBoundary.askTableID();
			TableBoundary.printSingleTableStatus(tableID, tables.get(tableID - 1).getStatus());
		} else {
			TableBoundary.printTableStatusHeader();
			for (int tableID = 1; tableID <= TOTALTABLES; tableID++) {
				TableBoundary.printThatTableStatus(tableID, tables.get(tableID - 1).getStatus());
			}
		}
	}

}
