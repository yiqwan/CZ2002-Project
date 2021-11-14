package order;

import java.util.ArrayList;
import menu.*;
import reservation.TableManager;

/**
 * This class performs logical operations on the collection of orders.
 * It gets user input by calling the <code>OrderBoundary</code> class.
 *
 */
public class OrderManager {
	/**
	 * The list of <code>Order</code> currently pending.
	 */
	private static ArrayList<Order> order_array = new ArrayList<Order>();
	/**
	 * The currently offered menu as a <code>Menu</code> class.
	 */
	private static Menu menu;

	/**
	 * Sets a new menu.
	 * @param newMenu the new menu to be set
	 */
	public static void setMenu(Menu newMenu) {
		menu = newMenu;
	}

	/**
	 * Gets the number of pending orders.
	 * @return the size of the <code>order_array</code>
	 */
	public static int numOrders() {
		return order_array.size();
	}

	/**
	 * Gets the size of an order specified by its orderID.
	 * @param orderID the index of the order desired
	 * @return the size of the order referenced
	 */
	public static int getOrderSize(int orderID) {
		return order_array.get(orderID).getSize();
	}

	/**
	 * Gets the corresponding tableID for an orderID.
	 * @param orderID the order that you wish to find the table for
	 * @return the ID of the table desired.
	 */
	public static int getTableID(int orderID) {
		return order_array.get(orderID).getTableID();
	}

	/**
	 * Gets the corresponding orderID for an tableID.
	 * @param tableID the table that you wish to find the order for
	 * @return the ID of the order desired.
	 */
	public static int getOrderID(int tableID) {
		for (int i = 0; i < order_array.size(); i++) {
			if (order_array.get(i).getTableID() == tableID) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Create a new order. 
	 */
	public static void newOrder() {
		int pax, tableID, staffID;
		pax = OrderBoundary.getPax();
		if (pax == -1)
			return;
		tableID = OrderBoundary.getEmptyTableID(pax);
		if (tableID == -1)
			return;
		staffID = OrderBoundary.getStaffID();
		order_array.add(new Order(staffID, tableID));
		TableManager.setTableAvailability(tableID, false);
		OrderBoundary.newOrderSuccess(staffID, tableID);
		return;
	}

	/**
	 * Delete an existing order.
	 */
	public static void deleteOrder() {
		int orderID, tableID;
		boolean confirm;
		tableID = OrderBoundary.getPendingTableID();
		if (tableID == -1)
			return;
		orderID = getOrderID(tableID);
		confirm = OrderBoundary.confirmDelete(tableID);
		if (confirm) {
			order_array.remove(orderID);
			TableManager.setTableAvailability(tableID, true);
			OrderBoundary.deleteOrderSuccess(tableID);
			return;
		}
		return;
	}

	/**
	 * Edit an order (add/delete items from the order).
	 */
	public static void editOrder() {
		int orderID, tableID;
		int choice;
		tableID = OrderBoundary.getPendingTableID();
		orderID = getOrderID(tableID);
		do {
			choice = OrderBoundary.editChoice();
			if (choice == 1) {
				addingItems(orderID);
			}
			if (choice == 2) {
				removingItems(orderID);
			}
		} while ((choice == 1) || (choice == 2));
		return;
	}

	/**
	 * Get the <code>Order</code> object at the specified index.
	 * @param orderID the ID of the order desired
	 * @return the <code>Order</code> object at the specified index
	 */
	public static Order getOrder(int orderID) {
		return order_array.get(orderID);
	}

	/**
	 * Get a <code>String[]</code> containing the details of the items ordered.
	 * @param orderID the ID of the order desired
	 * @return a <code>String[]</code> containing the desired info
	 */
	public static String[] getOrderInfo(int orderID) {
		String[] info = new String[getOrderSize(orderID)];
		for (int i = 0; i < getOrderSize(orderID); i++) {
			info[i] = order_array.get(orderID).getItem(i).getName() + ": $"
					+ String.valueOf(order_array.get(orderID).getItem(i).getPrice());
		}
		return info;
	}

	/**
	 * Call the <code>OrderBoundary</code> to print the details of an order.
	 */
	public static void viewOrder() {
		int tableID, orderID;
		tableID = OrderBoundary.getPendingTableID();
		if (tableID == -1)
			return;
		orderID = getOrderID(tableID);
		OrderBoundary.printOrder(orderID);
		return;
	}

	/**
	 * Pops an <code>Order</code> from the <code>order_array</code>. (removes it from the order array)
	 * @param orderID ID of the order to be popped
	 * @return the <code>Order</code> object popped.
	 */
	public static Order checkout(int orderID) {
		TableManager.setTableAvailability(getTableID(orderID), true);
		return order_array.remove(orderID);
	}

	/**
	 * A helper function that loops, adding items to a specified orderID
	 * @param orderID the ID of the order that you are adding to
	 */
	private static void addingItems(int orderID) {
		int item;
		menu.printMenu();
		do {
			item = OrderBoundary.addingItems(menu.getMenuSize());
			if (item != -1) {
				order_array.get(orderID).addItem(menu.getMenuItemByID(item));
			}
		} while (item != -1);
		return;
	}

	/**
	 * A helper function that loops to remove items from a specified orderID.
	 * @param orderID the ID of the order that you are removing from
	 */
	private static void removingItems(int orderID) {
		int item;
		do {
			OrderBoundary.printOrder(orderID);
			item = OrderBoundary.removingItems(getOrderSize(orderID));
			if (item != -1) {
				order_array.get(orderID).removeItem(item - 1);
			}
		} while (item != -1);
		return;
	}

}
