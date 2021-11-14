package menu;

import io.ExceptionHandler;

/**
 * Prints commands to show the user and take inputs from the user
 */
public class MenuBoundary {
	/**
	 * Prompts user to enter a choice of category
	 * @return the category
	 */
	public static int askMenuItemCategory() {

		// ask the menu item category
		System.out.println("Please enter a number to choose a category.");
		System.out.println("(1) Main course");
		System.out.println("(2) Drink");
		System.out.println("(3) Dessert");
		System.out.println("(4) Promotional item");
		return (ExceptionHandler.scanIntRange(1, 4));
	}

	/**
	 * Prompts user to enter a choice of attribute to edit 
	 * @return the choice number
	 */
	public static int askEditID() {
		System.out.println("Please enter a number edit the attribute.");
		System.out.println("(1) Name");
		System.out.println("(2) Description");
		System.out.println("(3) Price");
		return (ExceptionHandler.scanIntRange(1, 3));
	}

	/**
	 * Prompts user to enter an ID of a menu item
	 * @param menuSize
	 * @return the ID of a menu item
	 */
	public static int askMenuItemID(int menuSize) {
		if (menuSize == 0) {
			System.out.println("No menu item available.");
			return -1;
		}
		System.out.println("Please enter the ID of the menu item.");
		// check the input given the menuSize
		return (ExceptionHandler.scanIntRange(1, menuSize));
	}

	/**
	 * Prints commands on the console and gets the name of a menu item
	 * @return the name of the menu item
	 */
	public static String askName() {
		System.out.println("Input the name of the menu item.");
		return (ExceptionHandler.scanString());
	}

	/**
	 * Prints commands on the console and gets the description of a menu item
	 * @return the description of the menu item
	 */
	public static String askDescription() {
		System.out.println("Input the description of the menu item.");
		return (ExceptionHandler.scanString());
	}

	/**
	 * Prints commands on the console and gets the price of a menu item
	 * @return the price of the menu item
	 */
	public static double askPrice() {
		System.out.println("Input the price of the menu item.");
		return (ExceptionHandler.scanDouble());
	}
}
