package menu.menuitem;

/**
 * An entity class that stores information of a main course
 * A subclass of <code>MenuItem</code>
 */
public class MainCourse extends MenuItem {
	/**
	 * Stores whether the main course is vegetarian
	 */
	private boolean isVegetarian;

	/**
	 * A constructor that calls the parent class to initialise the attributes of main course
	 * @param name n of the main course
	 * @param description d  of the main course
	 * @param price p of the main course
	 */
	public MainCourse(String n, String d, double p) { // constructor
		super(n, d, p);
		isVegetarian = false;
	}

	/**
	 * A constructor that calls the parent class to initialise the attributes of main course
	 * @param name n of the main course
	 * @param description d  of the main course
	 * @param price p of the main course
	 * @param isVegetarian v of the main course
	 */
	public MainCourse(String n, String d, double p, boolean v) {
		super(n, d, p);
		isVegetarian = v;
	}

	/**
	 * Combines all the attributes like name, description, price, isVegetarian
	 * @return a string that contains all the information of the main course
	 */
	public String toString() {
		String result = new String();
		if (isVegetarian) {
			result = result + "(Vegetarian) ";
		}
		result = result + name + ", " + description + ": $" + String.valueOf(price);
		return result;
	}
}