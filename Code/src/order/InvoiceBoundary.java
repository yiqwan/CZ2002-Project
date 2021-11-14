package order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import io.ExceptionHandler;
import menu.menuitem.MenuItem;
import menu.menuitem.PromotionalItem;

/**
 * Sets as a boundary between the control classes and user 
 * Prints the invoice and sales report in the console
 * Asks the date 
 */
public class InvoiceBoundary {
	/**
	 * Prints invalid operation
	 */
	public static void printInvalid() {
		System.out.println("Invalid operation.");
	}

	/**
	 * Asks whether the customer is a member
	 * @return true or false
	 */
	public static boolean askIsMember() {
		System.out.println("Do you have a member? (1) yes (0) no");
		int integer_input = ExceptionHandler.scanIntRange(0, 1);
		return integer_input == 1 ? true : false;
	}

	/**
	 * 
	 * Prints out the full format of an invoice in the console
	 * 
	 * @param invoice, the invoice object to be processed
	 * @param subtotal, the subtotal of the order
	 * @param total, the final amount to be paid by the customer
	 * @param discount, the amount of member discount 
	 * @param gst, the amount of gst calculated given the subtotal
	 */
	public static void printInvoice(Invoice invoice, double subtotal, double total, double discount, double gst) {

		System.out.println("---------------------------------------");
		System.out.printf("%25s\n", "Pizza House");
		System.out.println("Date: " + invoice.getPaymentTime().toLocalDate());
		System.out.printf("Time: %d:%02d\n", invoice.getPaymentTime().toLocalTime().getHour(),
				invoice.getPaymentTime().toLocalTime().getMinute());
		System.out.println("Table: " + invoice.getOrder().getTableID());
		System.out.println("Staff: " + StaffManager.getName(invoice.getOrder().getStaffID()));
		System.out.println("---------------------------------------");

		System.out.println();
		for (int i = 0; i < invoice.getOrder().getOrdered().size(); i++) {

			if (invoice.getOrder().getOrdered().get(i) instanceof PromotionalItem) {
				System.out.printf("%-30s %8.2f\n", invoice.getOrder().getOrdered().get(i).getName(),
						invoice.getOrder().getOrdered().get(i).getPrice());
				System.out.printf("  %s\n", invoice.getOrder().getOrdered().get(i).getDescription());
			} else {
				System.out.printf("%-30s %8.2f\n", invoice.getOrder().getOrdered().get(i).getName(),
						invoice.getOrder().getOrdered().get(i).getPrice());

			}
		}
		System.out.println();
		System.out.printf("%30s %8.2f\n", "Subtotal", subtotal);
		if (discount != 0)
			System.out.printf("%30s %8.2f\n", "Member discount", discount);
		System.out.printf("%30s %8.2f\n", "7% GST", gst);
		System.out.printf("%30s %8.2f\n", "Total", total);
		System.out.println();

		System.out.println("---------------------------------------");
		System.out.printf("%24s\n", "Thank you!");
		System.out.println("---------------------------------------");
	}

	/**
	 * Gets a starting date of the period of a sales report 
	 * @return a start date 
	 */
	public static LocalDate askDate1() {
		System.out.println("The report displays the sales within a period.");
		System.out.println("Please enter the start date in the format dd-MM-yyyy.");
		return (ExceptionHandler.scanDate());
	}

	/**
	 * Gets an end date of the period of a sales report 
	 * @return an end date
	 */
	public static LocalDate askDate2() {
		System.out.println("Please enter the end date in the format DD-MM-YYYY.");
		return (ExceptionHandler.scanDate());
	}

	/**
	 * 
	 * Prints out the full format of an invoice in the console
	 * 
	 * @param items
	 * @param menuItemSales
	 * @param date1
	 * @param date2
	 */
	public static void printSalesReport(ArrayList<MenuItem> items, Hashtable<String, Integer> menuItemSales,
			LocalDate date1, LocalDate date2) {
		Enumeration<String> e = menuItemSales.keys();
		MenuItem menuItem = null;
		String name;
		double total_sales = 0;

		System.out.println("----------------------------------------");
		System.out.printf("%25s\n", "Sales Report");
		System.out.println("From: " + date1);
		System.out.println("To: " + date2);
		System.out.println();
		System.out.printf("%-13s %-8s %-7s %-8s\n", "Item", "Price($)", "Number", "Total($)");
		System.out.println("----------------------------------------");
		if (menuItemSales.size() == 0) {
			System.out.println("No sales available");
			return;
		}

		while (e.hasMoreElements()) {
			name = e.nextElement();
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getName() == name) {
					menuItem = items.get(i);
					break;
				}
			}
			int total_item = (int) menuItemSales.get(name);
			total_sales += menuItem.getPrice() * total_item;
			System.out.printf("%-15s %-8.2f %-7d %7.2f \n", menuItem.getName(), menuItem.getPrice(), total_item,
					menuItem.getPrice() * total_item);

			// TODO: generate total
		}
		System.out.println("----------------------------------------");
		System.out.printf("%30s %8.2f\n", "Total sales", total_sales);
		System.out.println("----------------------------------------");
	}
}
