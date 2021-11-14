package order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

import menu.menuitem.MenuItem;
import reservation.TableManager;

/**
 * Stores all the invoice Connects invoice boundary to print invoice Initialise
 * an invoice manager in the main app
 */
public class InvoiceManager {

	private static ArrayList<Invoice> invoice_array;

	static {
		invoice_array = new ArrayList<Invoice>();
	}

	/**
	 * @return an array of invoice object
	 */
	public static ArrayList<Invoice> getInvoiceArray() {
		return invoice_array;
	}

	/**
	 * Sets the invoice array to be the new array
	 * @param newArray which is an array of invoice object
	 */
	public static void setInvoiceArray(ArrayList<Invoice> newArray) {
		invoice_array = newArray;
	}

	/**
	 * Removes the order when the customer checks out
	 * Creates a new invoice and add into the invoice array
	 * Calculates the total of the bill
	 */
	public static void printInvoice() {
		int tableID = OrderBoundary.getPendingTableID();
		if (tableID == -1) {
			InvoiceBoundary.printInvalid();
			return;
		}
		int orderID = OrderManager.getOrderID(tableID);
		Order curr_order = OrderManager.checkout(orderID);
		TableManager.setTableAvailability(tableID, true);
		boolean isMember = InvoiceBoundary.askIsMember();
		Invoice invoice = new Invoice(LocalDateTime.now(), isMember, curr_order);
		invoice_array.add(invoice);
		double subtotal = curr_order.getSubTotal(), discount = 0, discounted_price, total, gst_price;
		if (isMember) {
			discount = subtotal * Invoice.discount_percent / 100;
			discounted_price = subtotal - discount;
		} else {
			discount = 0;
			discounted_price = subtotal;
		}
		gst_price = discounted_price * Invoice.gst_percent / 100;
		total = discounted_price + gst_price;
		InvoiceBoundary.printInvoice(invoice, subtotal, total, discount, gst_price);
	}

	/**
	 * Gets the start date and end date of a period to generate sales report
	 * Checks which invoice is within the period
	 * Calculates the total items sold and the total sales generated for each menu item
	 * Derive the total revenue
	 */
	public static void createSalesReport() {

		Hashtable<String, Integer> menuItemSales = new Hashtable<String, Integer>();
		LocalDate date1 = InvoiceBoundary.askDate1();
		LocalDate date2 = InvoiceBoundary.askDate2();

		LocalDate dateTime;
		MenuItem menuItem;
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		for (int i = 0; i < invoice_array.size(); i++) { // loop through array of invoices
			dateTime = invoice_array.get(i).getPaymentTime().toLocalDate();
			// iterate through the items inside the invoice
			for (int j = 0; j < invoice_array.get(i).getOrder().getSize(); j++) { // loop through the items ordered
				menuItem = invoice_array.get(i).getOrder().getItem(j);
				// if not in the specified period
				if (dateTime.isBefore(date1) || dateTime.isAfter(date2)) {
					break;
				}
				// increase sales for the menu item or create the menu item
				if (menuItemSales.containsKey(menuItem.getName())) {
					menuItemSales.put(menuItem.getName(), menuItemSales.get(menuItem.getName()) + 1);
					System.out.println(menuItemSales.get(menuItem.getName()));
				} else {
					menuItemSales.put(menuItem.getName(), 1);
					items.add(menuItem);
				}
			}
		}
		InvoiceBoundary.printSalesReport(items, menuItemSales, date1, date2);
	}

}
