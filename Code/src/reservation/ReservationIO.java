package reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import io.Exportable;
import io.Importable;

/**
 * This class serves as a wrapper and translator for an <code>ArrayList</code> of <code>Invoice</code> objects for import and export from and to a CSV file.
 *
 */
public class ReservationIO implements Exportable, Importable {

	/**
	 * The list of <code>Reservation</code> to be imported/exported
	 */
	private ArrayList<Reservation> reservation_array;

	/**
	 * Constructor for the class. <code>reservation_array</code> is empty by default.
	 */
	public ReservationIO() {
		this.reservation_array = new ArrayList<Reservation>();
	}

	/**
	 * Sets the <code>ArrayList</code> of <code>Reservation</code>s to be imported/exported.
	 * @param newReservationArray the <code>ArrayList</code> of <code>Reservation</code> objects to be set.
	 */
	public void setReservationArray(ArrayList<Reservation> newReservationArray) {
		this.reservation_array = newReservationArray;
	}

	/**
	 * Gets the <code>ArrayList</code> of <code>Reservation</code>s stored.
	 * @return the <code>ArrayList</code> of <code>Reservation</code>s stored in the instance
	 */
	public ArrayList<Reservation> getReservationArray() {
		return this.reservation_array;
	}
	
	/**
	 * Parses an array of string and parses the group of strings into <code>Reservation</code> objects
	 * Adds the created <code>Reservation</code> objects into <code>reservation_array</code>
	 * @param an <code>ArrayList</code> of <code>String</code>
	 */
	@Override
	public void importData(ArrayList<String> data) {
		for (int i=0; i<data.size(); i++) {
			reservation_array.add(parseReservation(data.get(i)));
		}
	}

	/**
	 * Loops through the <code>reservation_array</code> and parse each <code>Reservation</code> object into a string representation and return
	 * @return the array list of strings that each correspond to a parsed <code>Reservation</code> object
	 */
	@Override
	public ArrayList<String> exportData() {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < reservation_array.size(); i++) {
			result.add(formatReservation(reservation_array.get(i)));
		}
		return result;
	}
	
	/**
	 * Parses an input string and returns an <code>Reservation</code> object that the string represents
	 * @param input string to be parsed
	 * @return the <code>Reservation</code> object from the parsed input
	 * 
	 */
	private static Reservation parseReservation(String input) {
		String[] metadata = input.split("#");
		int pax = Integer.parseInt(metadata[0]);
		int tableID = Integer.parseInt(metadata[1]);
		String customerName = metadata[2];
		String contact = metadata[3];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime bookingTime = LocalDateTime.parse(metadata[4], formatter);
		return new Reservation(customerName, contact, pax, tableID, bookingTime, 0);
	}
	
	/**
	 * Formats the <code>Reservation</code> object into a string that contains all details of the object
	 * @param reservation <code>Reservation</code> object to be formatted
	 * @return String <code>result</code> that contains all the information of the <code>Reservation</code> object
	 */
	private static String formatReservation(Reservation reservation) {
		String[] temp = new String[5];
		String result;
		temp[0] = String.valueOf(reservation.getReservationPax());
		temp[1] = String.valueOf(reservation.getReservationTable());
		temp[2] = reservation.getReservationCustomer();
		temp[3] = reservation.getContact();
		temp[4] = formatTime(reservation.getBookingTime());
		result = temp[0];
		for (int i=1; i<=4; i++) {
			result = result+"#"+temp[i];
		}
		return result;
	}
	
	/**
	 * This method converts a <code>LocalDateTime</code> to a string.
	 * @param time the time to be formatted
	 * @return the time as a string
	 */
	private static String formatTime(LocalDateTime time)
	{
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatExpiryTime = time.format(format); 
		return formatExpiryTime; 
	}

}
