import io.*;
import menu.*;
import order.*;
import reservation.*;

/**
 * This is the class containing the main method.
 * It is responsible for calling the various Manager classes to perform tasks.
 * It also handles the calling/instantiation of IO classes/objects to import/export data from CSV files.
 * It relies on the <code>RestaurantBoundary</code> class to communicate with a user.
 *
 */
public class RestaurantApp {
	/**
	 * The entry point into the application.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		MenuIO menuIO = new MenuIO();
		ReservationIO reservationIO = new ReservationIO();
		InvoiceIO invoiceIO = new InvoiceIO();
		try {
			CSVReader.importCSV(PathHelper.path() + "menu.txt", menuIO);
			CSVReader.importCSV(PathHelper.path() + "reservation.txt", reservationIO);
			ReservationManager.setReservationArray(reservationIO.getReservationArray());
			CSVReader.importCSV(PathHelper.path() + "invoice.txt", invoiceIO);
			InvoiceManager.setInvoiceArray(invoiceIO.getInvoiceArray());
		} catch (Exception e) {
			RestaurantBoundary.importError();
		}

		// Initialize Managers
		Menu menu = new Menu(menuIO.getMenuItemArray());
		OrderManager.setMenu(menu);

		RestaurantBoundary.printSplash();

		int flag = 1;
		int choice;

		do {
			choice = RestaurantBoundary.getTask();

			switch (choice) {
			case (1):
				menu.createMenuItem();
				break;
			case (2):
				menu.updateMenuItem();
				break;
			case (3):
				menu.removeMenuItem();
				break;
			case (4):
				menu.printMenu();
				break;
			case (5):
				OrderManager.newOrder();
				break;
			case (6):
				OrderManager.viewOrder();
				break;
			case (7):
				OrderManager.editOrder();
				break;
			case (8):
				OrderManager.deleteOrder();
				break;
			case (9):
				// create reservation
				ReservationManager.createReservation();
				break;
			case (10):
				// remove reservation by contact
				ReservationManager.removeReservationByContact();
				break;
			case (11):
				// print reservation by contact
				ReservationManager.printReservationByContact();
				break;
			case (12):
				// print all reservations
				ReservationManager.printAllReservation();
				break;
			case (13):
				TableManager.printTableAvailability();
				break;
			case (14):
				// checkout an order and print an invoice
				InvoiceManager.printInvoice();
				break;
			case (15):
				InvoiceManager.createSalesReport();
				break;
			case (0):
				flag = 0;
			default:
				break;
			}
			if (flag != 0) flag = RestaurantBoundary.getContinue();
		} while (flag != 0);
		RestaurantBoundary.printExit();
		try {
			CSVWriter.exportCSV(PathHelper.path() + "menu.txt", menuIO);
			reservationIO.setReservationArray(ReservationManager.getReservationArray());
			CSVWriter.exportCSV(PathHelper.path() + "reservation.txt", reservationIO);
			invoiceIO.setInvoiceArray(InvoiceManager.getInvoiceArray());
			CSVWriter.exportCSV(PathHelper.path() + "invoice.txt", invoiceIO);
		} catch (Exception e) {
			RestaurantBoundary.exportError();
		}
		
		System.exit(0);
	}

}