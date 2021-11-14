package order;

import io.ExceptionHandler;
import reservation.*;

/**
 * This class acts as a boundary between the user and the <code>OrderManager</code> class.
 *
 */
public class OrderBoundary {

	/**
	 * Prompts the user to input the ID of a table with a pending order.
	 * @return the tableID entered by the user
	 */
	public static int getPendingTableID() {
		int tableID;
		boolean flag;
		if (OrderManager.numOrders() == 0) {
			System.out.println("There are no pending orders!");
			return -1;
		}
		printPendingOrders();
		do {
			flag = false;
			System.out.println("Enter table ID (Enter 0 to return):");
			tableID = ExceptionHandler.scanIntRange(0, TableManager.getNumberOfTables());
			if (tableID == 0)
				return -1;
			if (OrderManager.getOrderID(tableID) == -1) {
				System.out.println("Table does not have a pending order!");
				flag = true;
			}
		} while (flag);
		return tableID;
	}

	/**
	 * Prompts the user to input the ID of an empty table that seats at least <code>pax</code> number of people.
	 * 
	 * @param pax the number of people to seat
	 * @return the tableID entered by the user
	 */
	public static int getEmptyTableID(int pax) {
		int tableID;
		boolean flag;
		do {
			flag = false;
			System.out.println("Enter the ID of an empty table (Enter 0 to return):");
			tableID = ExceptionHandler.scanIntRange(0, TableManager.getNumberOfTables());
			if (tableID == 0)
				return -1;
			if (!TableManager.getTableByID(tableID).getStatus()) {
				System.out.println("Table is already occupied!");
				flag = true;
			}
			if (pax > TableManager.getTableByID(tableID).getSeatCap()) {
				System.out.println("Table cannot contain " + pax + " people!");
				flag = true;
			}
		} while (flag);
		return tableID;
	}

	/**
	 * Prompts the user to input their staff ID.
	 * @return the staffID entered
	 */
	public static int getStaffID() {
		int staffID;
		System.out.println("Enter staff ID:");
		staffID = ExceptionHandler.scanIntRange(0, StaffManager.numStaff());
		return staffID;
	}

	/**
	 * Prompts the user to enter the number of people waiting to be seated.
	 * @return the number of people entered.
	 */
	public static int getPax() {
		int pax;
		System.out.println("Enter pax (Enter 0 to return):");
		pax = ExceptionHandler.scanIntRange(0, 10);
		if (pax == 0)
			return -1;
		return pax;
	}

	/**
	 * Notifies the user that order creation at table <code>tableID</code> by staff member <code>staffID</code> was successful.
	 * @param staffID the staffID of the creator of the order
	 * @param tableID the tableID of the order
	 */
	public static void newOrderSuccess(int staffID, int tableID) {
		System.out.println("New order for table " + tableID + " by staff " + StaffManager.getName(staffID)
				+ " successfully created.");
		return;
	}

	/**
	 * Prompts the user to confirm deletion of an order at <code>tableID</code>
	 * @param tableID the tableID of the order to be deleted
	 * @return the user's choice
	 */
	public static boolean confirmDelete(int tableID) {
		int choice;
		System.out.println("Are you sure you want to delete the order at table " + tableID + "? (1) Yes, (0) No");
		choice = ExceptionHandler.scanIntRange(0, 1);
		if (choice == 1)
			return true;
		return false;
	}

	/**
	 * Notifies the user that order deletion at table <code>tableID</code> was successful.
	 * @param tableID the tableID of deleted order
	 */
	public static void deleteOrderSuccess(int tableID) {
		System.out.println("Order at table " + tableID + " successfully deleted.");
		return;
	}

	/**
	 * Presents available options and prompts the user to choose which editing option they want.
	 * @return the user's choice
	 */
	public static int editChoice() {
		int choice;
		System.out.println("What would you like to do? (1) Add Items, (2) Remove Items, (0) Exit");
		choice = ExceptionHandler.scanIntRange(0, 2);
		return choice;
	}

	/**
	 * Prints out all pending orders by tableID.
	 * 
	 */
	public static void printPendingOrders() {
		System.out.println("Pending orders by table ID:");
		for (int i = 0; i < OrderManager.numOrders(); i++) {
			System.out.print(OrderManager.getTableID(i) + "  ");
		}
		System.out.println("");
	}

	/**
	 * Prompts the user to enter the index of the item they would like to add.
	 * @param menuSize the size of the menu offered
	 * @return the user's choice
	 */
	public static int addingItems(int menuSize) {
		int choice;
		System.out.println("What item would you like to add? Enter 0 to return.");
		choice = ExceptionHandler.scanIntRange(0, menuSize);
		if (choice == 0) {
			return -1;
		}
		return choice;
	}

	/**
	 * Prompts the user to enter the index of the item they would like to remove.
	 * @param orderSize the number of items ordered
	 * @return the user's choice
	 */
	public static int removingItems(int orderSize) {
		int choice;
		if (orderSize == 0) {
			System.out.println("The order is empty!");
			return -1;
		}
		System.out.println("What item would you like to remove? Enter 0 to return.");
		choice = ExceptionHandler.scanIntRange(0, orderSize);
		if (choice == 0) {
			return -1;
		}
		return choice;
	}

	/**
	 * Formats and prints the details of the order.
	 * @param orderID the order to be printed
	 */
	public static void printOrder(int orderID) {
		String[] orderInfo = OrderManager.getOrderInfo(orderID);
		System.out.println("-----Order for Table " + OrderManager.getTableID(orderID) + "-----");
		for (int i = 0; i < OrderManager.getOrderSize(orderID); i++) {
			System.out.print((i + 1) + ") ");
			System.out.println(orderInfo[i]);
		}
		System.out.println("-----End of Order-----");
	}

}
