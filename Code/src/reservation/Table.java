package reservation;

/**
 * An entity class that denotes an instance of a table. 
 */

public class Table {
	/**
	 * Contains integer attributes of the tableID and the seatCap, 
	 * as well as a boolean attribute of the availability status of the table.
	 * Also contains the getter and setter methods for each attribute.
	 */
	/**
	 * Table ID of the table
	 */
	private int tableID;
	/**
	 * Seat capacity of the table
	 */
	private int seatCap;
	/**
	 * A boolean, if true, it means the table is currently available (no order attached to it),
	 * if false, then it means the table is currently occupied (with an order attached to it).
	 */
	private boolean isAvailable;

	/**
	 * Constructor of a table object
	 * @param tableID	Table ID of the table
	 * @param seatCap	Seat capacity of the table
	 */
	/**
	 * @param tableID
	 * @param seatCap
	 */
	public Table(int tableID, int seatCap) {
		this.tableID = tableID;
		this.seatCap = seatCap;
		this.isAvailable = true;
	}

	/**
	 * A getter method to get the table ID of this table
	 * @return Table ID of this object
	 */
	public int getTableID() {
		return tableID;
	}

	/**
	 * A setter method to set the value of the table ID of this table
	 * @param tableID	The ID number that you want to set to this table
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	/**
	 * A getter method to get the seat capacity of this table
	 * @return	Seat capacity of this table
	 */
	public int getSeatCap() {
		return seatCap;
	}

	/** A setter method to set the seat capacity of this table
	 * @param seatCap	The seat capacity that you want to set to this table
	 */
	public void setSeatCap(int seatCap) {
		this.seatCap = seatCap;
	}

	/** A getter method to get the availability status of this table
	 * @return	Boolean value denoting the availability status of this table
	 */
	public boolean getStatus() {
		return this.isAvailable;
	}

	/** A setter method to set the availability status of this table
	 * @param status	Boolean value denoting the availability status that you want to set to this table
	 */
	public void setStatus(boolean status) {
		this.isAvailable = status;
	}

}
