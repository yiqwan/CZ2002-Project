package menu.menuitem;

/**
 * An entity class that stores information of a drink.
 * A subclass of <code>MenuItem</code>
 */
public class Drink extends MenuItem {
	/**
	 * Stores whether the drink is alcoholic
	 */
	private boolean isAlcoholic;
	/**
	 * Stores the volume of the drink
	 */
	private int volume;
	/**
	 * Stores whether the drink is hot
	 */
	private boolean isHot;
	/**
	 * A constructor that calls the parent class to initialise the attributes of drink
	 * @param name n of the drink
	 * @param description d  of the drink
	 * @param price p of the drink
	 */
	public Drink(String n, String d, double p) { // constructor
		super(n, d, p);
		isAlcoholic = false;
		volume = 200;
		isHot = false;
	}
	/**
	 * A constructor that calls the parent class to initialise the attributes of drink
	 * @param name n of the drink
	 * @param description d  of the drink
	 * @param price p of the drink
	 * @param isAlcoholic a, whether the drink is alcoholic
	 * @param volume v of the drink
	 * @param isHot h, whether the drink is hot
	 */
	public Drink(String n, String d, double p, boolean a, int v, boolean h) { // constructor
		super(n, d, p);
		isAlcoholic = a;
		volume = v;
		isHot = h;
	}
	/**
	 * Combines all the attributes like name, description, price, isAlcoholic, volume, isHot
	 * @return a string that contains all the information of the drink
	 */
	public String toString() {
		String result = name+", ";
		if (isHot) {
			result = result + "(Hot!)";
		}
		result = result + description;
		if (isAlcoholic) {
			result = result + "(Contains Alcohol)";
		}
		result = result + ": $"+String.valueOf(price);
		return result;
	}
}
