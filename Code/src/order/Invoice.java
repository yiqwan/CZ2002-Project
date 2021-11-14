package order;

import java.time.LocalDateTime;

/**
 * Stores the information when a customer pays a bill
 * 
 */
public class Invoice {
	/**
	 * Stores the payment time of the invoice
	 */
	private LocalDateTime paymentTime;
	/**
	 * Stores whether a customer is a member
	 */
	private boolean isMember;
	/**
	 * Stores the order object that relates to the invoice
	 */
	private Order order; // Invoice has order
	/**
	 * Stores the percentage of gst
	 */
	public static final double gst_percent = 7;
	/**
	 * Stores the percentage of member discount
	 */
	public static final double discount_percent = 5;

	/**
	 * A constructor that initialises the attributes of invoice
	 * @param paymentTime when the customer pays
	 * @param isMember, whether the customer is a member
	 * @param order, the order object
	 */
	public Invoice(LocalDateTime paymentTime, boolean isMember, Order order) {
		this.paymentTime = paymentTime;
		this.isMember = isMember;
		this.order = order;
	}

	/**
	 * @return whether the customer is a member
	 */
	public boolean isMember() {
		return isMember;
	}

	/**
	 * Sets to whether the customer is a member
	 * @param isMember
	 */
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}

	/**
	 * @return the payment time of the customer
	 */
	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	/**
	 * Sets the payment time of the customer
	 * @param paymentTime
	 */
	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	/**
	 * @return the order object that associates with invoice
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the order object that associates with invoice
	 * @param order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
}
