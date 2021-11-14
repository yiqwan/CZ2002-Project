package menu.menuitem;

/**
 * An entity class that stores information of a menu item
 */
public class MenuItem {
	/**
	 * Stores the name of the menu item
	 */
	protected String name;
	/**
	 * Stores the description of the menu item
	 */
	protected String description;
	/**
	 * Stores the price of the menu item
	 */
	protected double price;

	/**
	 * Stores the name of the menu item
	 */
	public MenuItem() {
	}

	/**
	 * A constructor that initialises the attributes of menu item
	 * @param name n of the promotional item
	 * @param description d  of the promotional item
	 * @param price p of the promotional item
	 */
	public MenuItem(String n, String d, double p) { // constructor
		name = n;
		description = d;
		price = p;
	}

	/**
	 * @return the name of the menu item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the menu item to the input string name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description of the menu item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the menu item to the input string description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price of the menu item
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the menu item to the input string price
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Combines all the attributes like name, description, price
	 * @return a string that contains all the information of the menu item
	 */
	public String toString() {
		return name+", "+description+": $"+String.valueOf(price);
	}
}