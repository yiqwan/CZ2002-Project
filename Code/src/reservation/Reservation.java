package reservation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An entity that stores the reservation details from customers.
 */
public class Reservation implements Serializable {
	/**
	 * Inside the reservation class contains registered customer information. 
	 * It also contains a getter and setter method for each attribute.
	/**
	 * The number of customers that are going to arrive.
	 */
	private int pax;
	/**
	 * The table ID to which this reservation is booked to.
	 */
	private int tableID;
	/**
	 * The name of the customer to which this reservation is booked to.
	 */
	private String customerName;
	/**
	 * The contact telephone number of the customer to which this reservation is booked to.
	 */
	private String contact;
	/**
	 * The booking time to which this reservation is booked to. Note that this attribute is a LocalDateTime data type.
	 */
	private LocalDateTime bookingTime;
	/**
	 * The booking time to which this reservation is booked to. Note that this attribute is a string data type.
	 */
	private String formatBookingTime;
	/**
	 * The expiry time of this reservation. Note that this attribute is a LocalDateTime data type.
	 */
	private LocalDateTime expiryTime;// reservation will be removed 30min automatically
	/**
	 * The expiry time of this reservation. Note that this attribute is a string data type.
	 */
	private String formatExpiryTime;
	/**
	 * If the current clock of your operating system passes through this number of minutes of the reservation time, 
	 * then this reservation is going to be automatically deleted. 
	 * For instance, if the current time now is 11th November 2021, 11:30am, 
	 * and your reservation booking is set at 11th November 2021, 11:00am, and EXPIRYDURATION is 30, 
	 * since your have already passed 30 minutes after the reservation booking time, 
	 * this reservation is going to be automatically deleted.
	 */
	private int EXPIRYDURATION = 30;// in minutes
	/**
	 * The reservation ID of this reservation. Note that reservation ID is not going to be shown to users.
	 * It is an attribute used internally in the system to trace the identity of a reservation. 
	 * 
	 * Each reservation will have a distinct reservation ID. To compare whether two reservations
	 * are the same or different, our programme can just compare by the reservation ID.
	 */
	private int reservationID;

	/**
	 * Constructor method for reservations
	 * @param customerName	The name of the customer to which this reservation is booked to.
	 * @param contact		The contact telephone number of the customer to which this reservation is booked to.
	 * @param pax			The table ID to which this reservation is booked to.
	 * @param tableID		The name of the customer to which this reservation is booked to.
	 * @param bookingTime	The booking time to which this reservation is booked to (LocalDateTime data type)
	 * @param reservationID	The ID number of this reservation
	 */
	public Reservation(String customerName, String contact, int pax, int tableID, LocalDateTime bookingTime,
			int reservationID) {
		this.customerName = customerName;
		this.contact = contact;
		this.pax = pax;
		this.tableID = tableID;
		this.bookingTime = bookingTime;
		this.expiryTime = bookingTime.plusMinutes(EXPIRYDURATION);
		this.reservationID = reservationID;
	}

	/**
	 * The getter method to get the customer name of this reservation.
	 * @return	Customer name of this reservation
	 */
	public String getReservationCustomer() {
		return customerName;
	}

	/**
	 * The setter method to set the customer name of this reservation.
	 * @param customerName	Customer name of this reservation
	 */
	public void setReservationCustomer(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * The getter method to get the contact number of this reservation.
	 * @return	Customer contact number
	 */
	public String getContact() {
		return contact;
	}

	/** The setter method to set the customer contact number of this reservation.
	 * @param contact	Customer contact number of this reservation
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * The getter method to get the number of customers that are going to arrive for this reservation.
	 * @return	 The number of customers that are going to arrive
	 */
	public int getReservationPax() {
		return pax;
	}

	/** The setter method to set the number of customers that are going to arrive for this reservation.
	 * @param pax	 The number of customers that are going to arrive.
	 */
	public void setReservationPax(int pax) {
		this.pax = pax;
	}

	/**	The getter method to get the table ID to which this reservation is booked to.
	 * @return	The table ID to which this reservation is booked to
	 */
	public int getReservationTable() {
		return tableID;
	}

	/** The setter method to get the table ID to which this reservation is booked to.
	 * @param tableID	The table ID to which this reservation is booked to
	 */
	public void setReservationTable(int tableID) {
		this.tableID = tableID;
	}

	/**
	 * The getter method to get the booking time of this reservation
	 * @return	Booking time of this reservation in LocalDateTime data type
	 */
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	/** The setter method to get the booking time of this reservation
	 * @param bookingTime	Booking time of this reservation in LocalDateTime data type
	 */
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
		this.expiryTime = this.bookingTime.plusMinutes(EXPIRYDURATION);
	}

	/**
	 * Format the booking time from a LocalDateTime data type to a string data type
	 * @param bookingTime	Booking time of this reservation in LocalDateTime data type
	 * @return				Booking time of this reservation in string data type
	 */
	public String getFormatBookingTime(LocalDateTime bookingTime) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatBookingTime = bookingTime.format(format);
		return formatBookingTime;
	}

	/**
	 * The getter method to get the expiry time of this reservation.
	 * @return	Expiry time of this reservation in LocalDateTime data type
	 */
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}

	/**
	 * The setter method to set the expiry time of this reservation.
	 * @param expiryTime	Expiry time of this reservation in LocalDateTime data type
	 */
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}

	/**
	 * Format the expiry time from a LocalDateTime data type to a string data type
	 * @param expiryTime	Expiry time of this reservation in LocalDateTime data type
	 * @return				Expiry time of this reservation in string data type
	 */
	public String getFormatExpiryTime(LocalDateTime expiryTime) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatExpiryTime = expiryTime.format(format);
		return formatExpiryTime;
	}

	/**
	 * The setter method to set the reservation ID of this reservation
	 * @param reservationID	Reservation ID of this reservation
	 */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	/**
	 * The getter method to get the reservation ID of this reservation
	 * @return	Reservation ID of this reservation
	 */
	public int getReservationID() {
		return this.reservationID;
	}

}
