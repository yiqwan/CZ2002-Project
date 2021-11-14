package order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import io.Exportable;
import io.Importable;
import menu.menuitem.Dessert;
import menu.menuitem.Drink;
import menu.menuitem.MainCourse;
import menu.menuitem.MenuItem;
import menu.menuitem.PromotionalItem;

/**
 * This class serves as a wrapper and translator for an <code>ArrayList</code> of <code>Invoice</code> objects for import and export from and to a CSV file.
 *
 */
public class InvoiceIO implements Exportable, Importable {

	/**
	 * The list of <code>Invoice</code> to be imported/exported
	 */
	private ArrayList<Invoice> invoice_array;

	/**
	 * Constructor for the class. <code>menu_item_array</code> is empty by default.
	 */
	public InvoiceIO() {
		this.invoice_array = new ArrayList<Invoice>();
	}

	/**
	 * Sets the <code>ArrayList</code> of <code>Invoice</code>s to be imported/exported.
	 * @param newInvoiceArray the <code>ArrayList</code> of <code>Invoice</code> objects to be set.
	 */
	public void setInvoiceArray(ArrayList<Invoice> newInvoiceArray) {
		this.invoice_array = newInvoiceArray;
	}

	/**
	 * Gets the <code>ArrayList</code> of <code>Invoice</code>s stored.
	 * @return the <code>ArrayList</code> of <code>Invoice</code>s stored in the instance
	 */
	public ArrayList<Invoice> getInvoiceArray() {
		return invoice_array;
	}

	/**
	 * Parses an array of string and parses the group of strings into <code>Invoice</code> objects
	 * Adds the created <code>Invoice</code> objects into <code>invoice_array</code>
	 * @param an <code>ArrayList</code> of <code>String</code>
	 */
	@Override
	public void importData(ArrayList<String> data) {
		for (int i = 0; i < data.size(); i++) {
			invoice_array.add(parseInvoice(data.get(i)));
		}
	}

	/**
	 * Loops through the <code>invoice_array</code> and parse each <code>Invoice</code> object into a string representation and return
	 * @return the array list of strings that each correspond to a parsed <code>Invoice</code> object
	 */
	@Override
	public ArrayList<String> exportData() {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < invoice_array.size(); i++) {
			result.add(formatInvoice(invoice_array.get(i)));
		}
		return result;
	}

	/**
	 * Parses an input string and returns an <code>Invoice</code> object that the string represents
	 * @param input string to be parsed
	 * @return the <code>Invoice</code> object from the parsed input
	 * 
	 */
	private static Invoice parseInvoice(String input) {
		String[] metadata = input.split("!");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime paymentTime = LocalDateTime.parse(metadata[0], formatter);
		boolean isMember = Boolean.parseBoolean(metadata[1]);
		Order order = parseOrder(metadata[2]);
		return new Invoice(paymentTime, isMember, order);
	}

	/**
	 * Parses an input string and returns am <code>Order</code> object that the string represents
	 * @param input string to be parsed
	 * @return the <code>Order</code> object from the parsed input
	 */
	private static Order parseOrder(String input) {
		String[] metadata = input.split("@");
		int staffID = Integer.parseInt(metadata[0]);
		int tableID = Integer.parseInt(metadata[1]);
		Order order = new Order(staffID, tableID);
		for (int i = 2; i < metadata.length; i++) {
			order.addItem(parseMenuItem(metadata[i]));
		}
		return order;
	}

	/**
	 * Parses an input string and returns a menu object that the string represents
	 * @param input string to be parsed
	 * @return the <code>MenuItem</code> object from the parsed input
	 */
	private static MenuItem parseMenuItem(String input) {
		String[] metadata = input.split("#");
		String name = metadata[1];
		String description = metadata[2];
		double price = Double.parseDouble(metadata[3]);
		if (metadata[0].equals("MainCourse")) {
			return new MainCourse(name, description, price);
		} else if (metadata[0].equals("Drink")) {
			return new Drink(name, description, price);
		} else if (metadata[0].equals("Dessert")) {
			return new Dessert(name, description, price);
		} else if (metadata[0].equals("PromotionalItem")) {
			return new PromotionalItem(name, description, price);
		} else {
			System.out.println("Error!");
			return null;
		}
	}

	/**
	 * Formats the <code>Invoice</code> object into a string that contains all details of the object
	 * @param invoice <code>Invoice</code> object to be formatted
	 * @return String <code>result</code> that contains all the information of the <code>Invoice</code> object
	 */
	private static String formatInvoice(Invoice invoice) {
		ArrayList<String> temp = new ArrayList<String>();
		String result;
		temp.add(formatTime(invoice.getPaymentTime()));
		temp.add(String.valueOf(invoice.isMember()));
		temp.add(formatOrder(invoice.getOrder()));
		result = temp.get(0);
		for (int i = 1; i < temp.size(); i++) {
			result = result + "!" + temp.get(i);
		}

		return result;
	}

	/**
	 * This method converts a <code>LocalDateTime</code> to a string.
	 * @param time the time to be formatted
	 * @return the time as a string
	 */
	private static String formatTime(LocalDateTime time) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatExpiryTime = time.format(format);
		return formatExpiryTime;
	}

	/**
	 * Formats the <code>Order</code> object into a string that contains all details of the object
	 * @param item <code>Order</code> object to be formatted
	 * @return String <code>result</code> that contains all the information of the <code>Order</code> object
	 */
	private static String formatOrder(Order order) {
		ArrayList<String> temp = new ArrayList<String>();
		String result;
		temp.add(String.valueOf(order.getTableID()));
		temp.add(String.valueOf(order.getStaffID()));
		for (int i = 0; i < order.getSize(); i++) {
			temp.add(formatMenuItem(order.getItem(i)));
		}
		result = temp.get(0);
		for (int i = 1; i < temp.size(); i++) {
			result = result + "@" + temp.get(i);
		}

		return result;
	}

	/**
	 * Formats the <code>MenuItem</code> object into a string that contains all details of the object
	 * @param item <code>MenuItem</code> object to be formatted
	 * @return String <code>result</code> that contains all the information of the <code>MenuItem</code> object
	 */
	private static String formatMenuItem(MenuItem item) {
		String[] temp = new String[4];
		String result;
		temp[0] = item.getClass().getName();
		temp[0] = temp[0].split("menu.menuitem.", 2)[1];
		temp[1] = item.getName();
		temp[2] = item.getDescription();
		temp[3] = String.valueOf(item.getPrice());
		result = temp[0];
		for (int i = 1; i <= 3; i++) {
			result = result + "#" + temp[i];
		}

		return result;
	}

}
