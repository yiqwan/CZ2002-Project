package menu.menuitem;

/**
 * An entity class that stores information of a promotional item
 * A subclass of <code>MenuItem</code>
 */
public class PromotionalItem extends MenuItem {
	/**
	 * Stores whether the promotional item is limited
	 */
	private boolean isLimited;
	/**
	 * Stores the number of servings of the promotional item
	 */
	private int numServings;

	/**
	 * A constructor that calls the parent class to initialise the attributes of promotional item
	 * @param name n of the promotional item
	 * @param description d  of the promotional item
	 * @param price p of the promotional item
	 */
	public PromotionalItem(String n, String d, double p) { // constructor
		super(n, d, p);
		isLimited = false;
		numServings = 1;
	}
	
	/**
	 * A constructor that calls the parent class to initialise the attributes of promotional item
	 * @param name n of the promotional item
	 * @param description d  of the promotional item
	 * @param price p of the promotional item
	 * @param isLimited l, whether the promotional item is limited
	 * @param numServings s of the promotional item
	 */
	public PromotionalItem(String n, String d, double p, boolean l, int s) {
		super(n, d, p);
		isLimited = l;
		numServings = s;
	}
	
	/**
	 * Combines all the attributes like name, description, price, isLimited, numServings
	 * @return a string that contains all the information of the main course
	 */
	public String toString(){
		String result = new String();
		if (isLimited) {
			result = result + "(Limited!) ";
		}
		result = result+name+", ";
		result = result + description;
		if (numServings > 1) {
			result = result + "(Serves "+String.valueOf(numServings)+")";
		}
		result = result + ": $" + String.valueOf(price);
		return result;
	}
	
	
}
