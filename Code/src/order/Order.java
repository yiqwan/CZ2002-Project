package order;

import java.util.ArrayList;
import menu.menuitem.*;

/**
 * This class contains data representing an order.
 *
 */
public class Order {
	/**
	 * The ID of the staff member who created the order.
	 */
	private int staffID;
	/**
	 * The ID of the table that the order is for.
	 */
	private int tableID;
	/**
	 * A list of the <code>MenuItem</code>s ordered.
	 */
	private ArrayList<MenuItem> ordered;

	/**
	 * Constructor.
	 * @param staffID ID of the creator of the order
	 * @param tableID ID of the table this order is for
	 */
	public Order(int staffID, int tableID) {
		this.staffID = staffID;
		this.tableID = tableID;
		ordered = new ArrayList<MenuItem>();
	}

	/**
	 * Calculates the total cost of items within the <code>ordered</code> array.
	 * @return the total cost of ordered items
	 */
	public double getSubTotal() {
		double subtotal = 0;
		for (int i = 0; i < ordered.size(); i++) {
			subtotal += ordered.get(i).getPrice();
		}
		return subtotal;
	}

	/**
	 * Gets the tableID.
	 * @return tableID
	 */
	public int getTableID() {
		return tableID;
	}

	/**
	 * Gets the staffID
	 * @return staffID
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * Gets the array of ordered items.
	 * @return an <code>ArrayList</code> of <code>MenuItem</code>s ordered
	 */
	public ArrayList<MenuItem> getOrdered() {
		return ordered;
	}

	/**
	 * Gets the item at the index specified within <code>ordered</code>
	 * @param index the index of the item desired
	 * @return a <code>MenuItem</code> of the item at the index specified
	 */
	public MenuItem getItem(int index) {
		return ordered.get(index);
	}

	/**
	 * Gets the number of items ordered.
	 * @return the size of the <code>ordered</code> array
	 */
	public int getSize() {
		return ordered.size();
	}

	/**
	 * Sets the tableID.
	 * @param tableID the ID to change to
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	/**
	 * Sets the staffID.
	 * @param staffID the ID to change to 
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	/**
	 * Adds a <code>MenuItem</code> to the <code>ordered</code> array.
	 * @param item the <code>MenuItem</code> to be ordered
	 */
	public void addItem(MenuItem item) {
		ordered.add(item);
	}

	/**
	 * Remove the item from the <code>ordered</code> array at the index provided
	 * @param index
	 */
	public void removeItem(int index) {
		ordered.remove(index);
	}
}
