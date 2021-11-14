package reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;

/**
 * A control stereotype
 * Represents a reservation manager in the system.
 * A reservation manager can manage all the reservations for the restaurant
 */
public class ReservationManager {
	
	/**
	 * An static ArrayList to store all the reservations
	 */
	private static ArrayList<Reservation> reservations;
	
	/**
	 * A static HashMap stores ArrayLists of reservations bounded by the table
	 * table ID acts as the key of the HashMap
	 */
	private static HashMap<Integer, ArrayList<Reservation>> getResvByTableID;
	
	/**
	 * A static variable representing the least number of hours
	 * before booking time if a customer wishes to place a reservation
	 */
	private static final int ADVANCERESERVATION = 1;
	
	/**
	 * A static variable indicates the opening time of the restaurant
	 * which is 11am
	 */
	private static final int OPERATIONSTART = 11;
	
	/**
	 * A static variable indicates the ending time of the restaurant
	 * which is 9pm
	 */
	private static final int OPERATIONEND = 21;
	
	/**
	 * A static variable indicates the starting id assigned to each reservation
	 */
	private static int idAssigner = 1;

	/**
	 * A constructor to initialize and construct 
	 * reservation ArrayList and the HashMap
	 * Trigger the automaticRemoveReservations method
	 */
	public static void setReservationArray(ArrayList<Reservation> newReservationArray) {

		reservations = newReservationArray;
		for (int i = 0; i < reservations.size(); i++) {
			reservations.get(i).setReservationID(idAssigner++);
		}
		getResvByTableID = new HashMap<Integer, ArrayList<Reservation>>();
		for (int i = 1; i <= TableManager.getNumberOfTables(); i++) {
			getResvByTableID.put(i, new ArrayList<Reservation>());
		}
		int tableID;
		for (int i = 0; i < reservations.size(); i++) {
			tableID = reservations.get(i).getReservationTable();
			getResvByTableID.get(tableID).add(reservations.get(i));
		}
		automaticRemoveReservations();
	}
    
	/**
	 * An ArrayList to store all the reservations that a single table has
	 * @return reservations
	 */
	public static ArrayList<Reservation> getReservationArray() {
		return reservations;
	}
	
	/**
	 * Check if the reservation is available & assign it the best fit Table
	 * @param pax,number of seats in reservation bookingTime
	 * @param booking time
	 * @param minute difference, which is 59 in minutes
	 * @return bestTableID, best fit Table, if not available return 1
	 */
	public static int findAvailableTable(int pax, LocalDateTime bookingTime, int minutesDifference) {
		long minuteDifference = (long) minutesDifference;
		int size, i, j;
		int bestTableID = -1;
		int bestTableSize = 99999;
		boolean thisTable;
		LocalDateTime t;
		// Find the best fit table
		for (i = 0; i < TableManager.getNumberOfTables(); i++) {
			if (TableManager.getCapacityByID(i) >= pax && TableManager.getCapacityByID(i) < bestTableSize) {
				// If tableSize is < current table, check if the reservation is available
				int tableID = i + 1;
				size = getResvByTableID.get(tableID).size();
				j = 0;
				thisTable = true;
				while (j < size) {
					// If the booking time clash with existed reservation -> not choose this table
					t = getResvByTableID.get(tableID).get(j).getBookingTime();
					if (bookingTime.isAfter(t.minusMinutes(minuteDifference))
							&& bookingTime.isBefore(t.plusMinutes(minuteDifference))) {
						thisTable = false;
						break;
					}
					j++;
				}
				// Change the bestTableID & bestCapSize
				if (thisTable) {
					bestTableID = i + 1;
					bestTableSize = TableManager.getCapacityByID(i);
				}
			}
		}
		return bestTableID;
	}
	
	/**
	 * Check if an available table can be found for the reservation or not
	 * @return bestTableID, best fit Table
	 * @return 1, if not available
	 */
	public static void checkTableAvailability() {
		int pax = ReservationBoundary.askPax();
		LocalDateTime bookingTime = ReservationBoundary.askBookingTime();
		if (bookingTime.getHour() < OPERATIONSTART || bookingTime.getHour() > OPERATIONEND - 1) {
			ReservationBoundary.printBookingErrorTwo();
			return;
		}

		int tableID = findAvailableTable(pax, bookingTime, 59);
		if (tableID != -1) {
			ReservationBoundary.foundAvailableTable(tableID);
		} else {
			ReservationBoundary.noAvailableTable();
		}
	}
	/**
	 * A method to create a reservation if an available table is found
	 */
	public static void createReservation() {
		int pax = ReservationBoundary.askPax();
		LocalDateTime bookingTime;

		while (true) {
			bookingTime = ReservationBoundary.askBookingTime();

			if (bookingTime.isBefore(LocalDateTime.now().plusHours(ADVANCERESERVATION))) {
				ReservationBoundary.printBookingErrorOne(ADVANCERESERVATION);
			} else if (bookingTime.getHour() < 11 || bookingTime.getHour() > 20) {
				ReservationBoundary.printBookingErrorTwo();
			} else
				break;
		}

		int tableID = findAvailableTable(pax, bookingTime, 59);
		if (tableID != -1) {
			String CustomerName = ReservationBoundary.askCustomerName();
			String contact = ReservationBoundary.askCustomerContact();
			Reservation reservation = new Reservation(CustomerName, contact, pax, tableID, bookingTime, idAssigner++);
			getResvByTableID.get(tableID).add(reservation);
			reservations.add(reservation);
			ReservationBoundary.successfulBooking();
			ReservationBoundary.printHeader();
			ReservationBoundary.printReservationDetails(reservation, 1);
		} else {
			ReservationBoundary.failedBooking();
		}
	}


	/**
	 * A runnable task to check if each reservation is expired or not
	 * if expired, remove the reservation
	 */
	private static void automaticRemoveReservationsHelper() {
		for (int i = 0; i < reservations.size(); i++) {
			boolean expired = true; 
			if (expired == (LocalDateTime.now()).isAfter(reservations.get(i).getExpiryTime())) {
				int tableID = reservations.get(i).getReservationTable();
				int size = getResvByTableID.get(tableID).size();
				int index = -1;
				for (int j = 0; j < size; j++) {
					if (getResvByTableID.get(tableID).get(j).getReservationID() == reservations.get(i).getReservationID())
						index = j;
				}
				getResvByTableID.get(tableID).remove(index);
				reservations.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * A method to set a scheduled task to be implemented every 60 seconds
	 */
	public static void automaticRemoveReservations() {
		Runnable automaticRemoveReservationsHelper = () -> automaticRemoveReservationsHelper();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(automaticRemoveReservationsHelper, 0, 60, TimeUnit.SECONDS);
	}

	/**
	 * Get reservation details from the customer's contact number
	 * @param contact
	 * @return reservation details
	 */
	public static ArrayList<Reservation> getReservationByContact(String contact) {
		ArrayList<Reservation> getReservationByContact = new ArrayList<Reservation>();
		for (int i = 0; i < reservations.size(); i++) {
			if (contact.equals(reservations.get(i).getContact())) {
				getReservationByContact.add(reservations.get(i));
			}
		}
		return getReservationByContact;
	}

	/**
	 * Print reservation details from the customer's contact number
	 * @param contact
	 * @return reservation details
	 */
	public static void printReservationByContact() {
		String contact = ReservationBoundary.askCustomerContact();
		ArrayList<Reservation> getReservationByContactArrayList = getReservationByContact(contact);
		if (getReservationByContactArrayList.size() == 0)
			ReservationBoundary.bookingNotFound();
	
		else {
			ReservationBoundary.bookingFound();
			ReservationBoundary.printHeader();
			for (int i = 0; i < getReservationByContactArrayList.size(); i++) {
				// ReservationBoundary.printMultipleReservations(getReservationByContactArrayList.get(i),
				// i+1);

				ReservationBoundary.printReservationDetails(getReservationByContactArrayList.get(i), i + 1);
			}
		}
	}
    
	/**
	 * Print all the reservations details from the customer's contact number
	 */
	public static void printAllReservation() {
		if (reservations.size() == 0)
			ReservationBoundary.emptyReservation();
		else {
			ReservationBoundary.printHeader();
			for (int i = 0; i < reservations.size(); i++) {
				// printMultipleReservations(reservations.get(i), i+1);
				ReservationBoundary.printReservationDetails(reservations.get(i), i + 1);
			}
		}
	}
	
	/**
	 * Remove that specific reservation from the customer's contact number
	 */
	public static void removeReservationByContact() {
		String contact = ReservationBoundary.askCustomerContact();
		ArrayList<Reservation> getReservationByContactArrayList = getReservationByContact(contact);
		int choice = 1;
		if (getReservationByContactArrayList.size() == 0) {
			ReservationBoundary.bookingNotFound();
			return;
		}

		else {
			ReservationBoundary.bookingFound();
			ReservationBoundary.printHeader();

			for (int i = 0; i < getReservationByContactArrayList.size(); i++) {
				ReservationBoundary.printReservationDetails(getReservationByContactArrayList.get(i), i + 1);
			}
			if (getReservationByContactArrayList.size() > 1)
				choice = ReservationBoundary.removeChoice(getReservationByContactArrayList.size()); // Interface
			// trigger remove
			int i, j;
			for (i = 0; i < reservations.size(); i++) {
				if (getReservationByContactArrayList.get(choice - 1).getReservationID() == reservations.get(i)
						.getReservationID())
					break;
			}
			int tableID = reservations.get(i).getReservationTable();
			int size = getResvByTableID.get(tableID).size();
			for (j = 0; j < size; j++) {
				if (getResvByTableID.get(tableID).get(j).getReservationID() == reservations.get(i).getReservationID()) {
					getResvByTableID.get(tableID).remove(j);
					reservations.remove(i);
					ReservationBoundary.removeTriggered(); // Interface
					return;
				}
			}
		}
	}
}

