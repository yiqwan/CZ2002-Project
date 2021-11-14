package menu;

import io.Exportable;
import io.Importable;
import java.util.ArrayList;
import menu.menuitem.*;

/**
 * This class serves as a wrapper and translator for an <code>ArrayList</code> of <code>MenuItem</code> objects for import and export from and to a CSV file.
 * 
 */
public class MenuIO implements Importable, Exportable {

	/**
	 * The list of <code>MenuItem</code> to be imported/exported
	 */
	private ArrayList<MenuItem> menu_item_array;

	/**
	 * Constructor for the class. <code>menu_item)array</code> is empty by default.
	 */
	public MenuIO() {
		this.menu_item_array = new ArrayList<MenuItem>();
	}

	/**
	 * Sets the <code>ArrayList</code> of <code>MenuItem</code>s to be imported/exported.
	 * @param newMenu a <code>Menu</code> object from which the <code>ArrayList</code> of <code>MenuItem</code>s are taken from.
	 */
	public void setMenuItemArray(Menu newMenu) {
		this.menu_item_array = newMenu.getMenuItemArray();
	}

	/**
   * Gets the <code>ArrayList</code> of <code>MenuItem</code>s stored.
	 * @return the <code>ArrayList</code> of <code>MenuItem</code>s stored in the instance
	 */
	public ArrayList<MenuItem> getMenuItemArray() {
		return menu_item_array;
	}
	
	/**
	 * Parses an array of string and parses the group of strings into menu item objects
	 * Adds the created menu item objects into <code>menu_item_array</code>
	 * @param an <code>ArrayList</code> of <code>String</code>
	 */
	@Override
	public void importData(ArrayList<String> data) {
		for (int i=0; i<data.size(); i++) {
			menu_item_array.add(parseMenuItem(data.get(i)));
		}
	}

	/**
	 * Loops through the <code>menu_item_array</code> and parse each <code>menuItem</code> object into a string representation and return
	 * @return the array list of strings that each correspond to a parsed <code>MenuItem</code> object
	 */
	@Override
	public ArrayList<String> exportData() {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < menu_item_array.size(); i++) {
			result.add(formatMenuItem(menu_item_array.get(i)));
		}
		return result;
	}
	
	/**
	 * Parses an input string and returns a menu object that the string represennts
	 * @param input string to be parsed
	 * @return the <code>MenuItem</code> object from the parsed input
	 */
	private static MenuItem parseMenuItem(String input) {
		String[] metadata = input.split("#");
		String name = metadata[1];
		String description = metadata[2];
		double price = Double.parseDouble(metadata[3]);
		if (metadata[0].equals("MainCourse")) {
			return new MainCourse(name, description, price);
		} else if (metadata[0].equals("Drink")) {
			return new Drink(name, description, price);
		} else if (metadata[0].equals("Dessert")) {
			return new Dessert(name, description, price);
		} else if (metadata[0].equals("PromotionalItem")) {
			return new PromotionalItem(name, description, price);
		} else {
			System.out.println("Error!");
			return null;
		}
	}

	/**
	 * Formats the <code>MenuItem</code> object into a string that contains all details of the object
	 * @param <code>MenuItem</code> object called item
	 * @return String <code>result</code> that contains all the information of the <code>MenuItem</code> object
	 */
	private static String formatMenuItem(MenuItem item) {
		String[] temp = new String[4];
		String result;
		temp[0] = item.getClass().getName();
		temp[0] = temp[0].split("menu.menuitem.", 2)[1];
		temp[1] = item.getName();
		temp[2] = item.getDescription();
		temp[3] = String.valueOf(item.getPrice());
		result = temp[0];
		for (int i = 1; i <= 3; i++) {
			result = result + "#" + temp[i];
		}

		return result;
	}
	
	

}
