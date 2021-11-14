package reservation;

import io.ExceptionHandler;
import java.time.LocalDateTime;

/**
 * Acts as a class to take reservation-related inputs from users and print the outputs to show the users
 */
public class ReservationBoundary {

	/**
	 * Input method to ask for the name of customer
	 * @return customer name
	 */
	public static String askCustomerName() {
		System.out.println("Please enter the customer name:");
		String name = ExceptionHandler.scanString();
		return (name);
	}

	/**
	 * Input method to ask for the contact number of customer
	 * @return the contact number of the customer
	 */
	public static String askCustomerContact() {
		System.out.println("Please enter the customer contact:");
		String contact = ExceptionHandler.scanString();
		return (contact);
	}

	/**
	 * Input method to ask for the number of customer
	 * @return the number of customers for a single reservation
	 */
	public static int askPax() {
		System.out.println("Please input the number of customers:");
		int pax = ExceptionHandler.scanIntRange(1, 10);
		return (pax);
	}

	/**
	 * Input method to ask for which reservation the customer would like to remove
	 * @return the choice on which reservation the customer would like to remove
	 */
	public static int removeChoice(int max) {
		System.out.println("Which reservation would you like to remove?");
		int choice = ExceptionHandler.scanIntRange(1, max);
		return choice;
	}

	/**
	 * Input method to ask for the booking
	 * @return the booking time
	 */
	public static LocalDateTime askBookingTime() {
		System.out.print("Please enter the booking time: ");
		System.out.println("Enter the date and time in the format: dd-mm-yyyy HH:mm");
		LocalDateTime localDateTime = ExceptionHandler.scanLocalDateTime();
		return localDateTime;
	}

	/**
	 * Print a warning statement if the calling in time to make reservation 
	 * is not at least 1 hour before the booking time
	 * @param advancedReservation, the minimum number of hours before the booking time
	 * if a customer wishes to place a reservation
	 */
	public static void printBookingErrorOne(int advancedReservation) {
		System.out.println("Reservations can only be made at least " + advancedReservation + " hours before.");
		System.out.println("Please try again.");
	}
	
	/**
	 * Print a warning statement if the booking time input
	 * is not within restaurant's operation time
	 */
	public static void printBookingErrorTwo() {
		System.out.println(
				"Our restaurant's operation time is from 11:00 to 21:00. Booking time must be within 11:00 and 20:00.");
		System.out.println("Please try again.");
	}
	
	/**
	 * Print a successful reservation statement
	 */
	public static void successfulBooking() {
		System.out.println("Reservation is made successfully!");
	}
	
	/**
	 * Print a failed reservation statement since there is no available tables at the point of time
	 */
	public static void failedBooking() {
		System.out.println("No available tables for this time slot.");
	}
	
	/**
	 * Print a statement to show that the reservation is found successfully
	 */
	public static void bookingFound() {
		System.out.println("The reservation is found.");
	}
	
	/**
	 * Print a statement to show that the reservation is not found
	 */
	public static void bookingNotFound() {
		System.out.println("The reservation cannot be found.");
	}
	
	/**
	 * Print a statement to show that the reservation is removed successfully
	 */
	public static void removeTriggered() {
		System.out.println("The reservation is removed successfully.");
	}
	
	/**
	 * Print a statement to show that an available table has been found
	 * along with the table ID
	 * @para table ID
	 */
	public static void foundAvailableTable(int tableID) {
		System.out.println("Table " + tableID + " is available at your preferred timeslot.");
	}
	
	/**
	 * Print a statement to show that no available tables can be found
	 */
	public static void noAvailableTable() {
		System.out.println("No table is available at your preferred timeslot.");
	}
	
	/**
	 * Print a formatted header for the printing reservation functions
	 */
	public static void printHeader() {
		System.out.println("Reservation Details are shown below\n"
				+ "________________________________________________________________________________");
		System.out.printf("%-5s %-24s %-15s %-15s %-10s %-10s\n", "No.", "Booking Time", "Name", "Contact", "Pax",
				"TableID");
		System.out.println("________________________________________________________________________________");
	}
	
	/**
	 * Print reservation details
	 * @para reservation
	 * @para i, index to iterate through the reservations
	 */
	public static void printReservationDetails(Reservation reservation, int i) {
		System.out.printf("%-5d %-24s %-15s %-15s %-10d %-10d\n", i,
				reservation.getFormatBookingTime(reservation.getBookingTime()), reservation.getReservationCustomer(),
				reservation.getContact(), reservation.getReservationPax(), reservation.getReservationTable());
	}
	
	/**
	 * Print a statement to show that there is no reservations at the point of time
	 */
	public static void emptyReservation() {
		System.out.println("There are no reservations right now.");
	}

}
