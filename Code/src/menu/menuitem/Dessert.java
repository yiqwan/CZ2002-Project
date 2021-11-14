package menu.menuitem;

/**
 * An entity class that stores information of a dessert.
 * A subclass of <code>MenuItem</code>
 */
public class Dessert extends MenuItem {
	/**
	 * Stores a boolean type of whether the dessert is alcoholic
	 */
	private boolean isAlcoholic;
	/**
	 * A constructor that calls the parent class to initialise the attributes of dessert
	 * @param name n of the dessert
	 * @param description d  of the dessert
	 * @param price p of the dessert
	 */
	public Dessert(String n, String d, double p) { // constructor
		super(n, d, p);
	}
	
	/**
	 * Combines all the attributes like name, description, price, isAlcoholic
	 * @return a string that contains all the information of the dessert
	 */
	public String toString() {
		String result = name + ", " + description;
		if (isAlcoholic) {
			result = result + "(Contains Alcohol)";
		}
		result = result + ": $"+String.valueOf(price);
		return result;
	}
	
}
