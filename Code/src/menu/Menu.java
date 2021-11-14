package menu;

import java.util.ArrayList;
import menu.menuitem.Dessert;
import menu.menuitem.Drink;
import menu.menuitem.MainCourse;
import menu.menuitem.MenuItem;
import menu.menuitem.PromotionalItem;

/**
 * Manages the actions that can be done on a menu
 * A menu consists of a list of menu items
 */
public class Menu { // no instances
	/**
	 * Stores a list of menu items
	 */
	private ArrayList<MenuItem> menu_item_array;

	/**
	 * Initialises the attributes
	 */
	public Menu() {
		menu_item_array = new ArrayList<MenuItem>();
	}
	
	/**
	 * Initialises the new menu object
	 * @param newMenuItemArray
	 */
	public Menu(ArrayList<MenuItem> newMenuItemArray) {
		menu_item_array = newMenuItemArray;
	}

	/**
	 * Gets a menu ID from the user
	 * Creates a new menu item
	 * Initialise its name, description and price
	 */
	public void createMenuItem() {
		int choice = 1;
		String name, description;
		double price;
		MenuItem menuItem = new MenuItem();

		choice = MenuBoundary.askMenuItemCategory();
		name = MenuBoundary.askName();
		description = MenuBoundary.askDescription();
		price = MenuBoundary.askPrice();
		switch (choice) {
		case (1): 
			menuItem = new MainCourse(name, description, price);
			break;
		case (2): 
			menuItem = new Drink(name, description, price);
			break;
		case (3):
			menuItem = new Dessert(name, description, price);
			break;
		case (4):
			menuItem = new PromotionalItem(name, description, price);
			break;
		default:
			break;
		}
		if (choice >= 1 && choice <= 4)
			menu_item_array.add(menuItem);
		System.out.println("Item added.");
		// }

	}

	/**
	 * Gets a menu ID from the user
	 * Updates the name, description or price of the menu item
	 */
	public void updateMenuItem() {
		int choice, menuID;
		String name, description;
		double price;

		// call boundary to get user data
		this.printMenu();
		menuID = MenuBoundary.askMenuItemID(getMenuSize());
		if (menuID == -1) {
			System.out.println("Operation invalid");
			return;
		}
		// check through the array size
		menuID -= 1;
		choice = MenuBoundary.askEditID();
		switch (choice) {
		case (1): // main course
			name = MenuBoundary.askName();
			menu_item_array.get(menuID).setName(name);
			break;
		case (2): // drink
			description = MenuBoundary.askDescription();
			menu_item_array.get(menuID).setDescription(description);
			break;
		case (3):
			price = MenuBoundary.askPrice();
			menu_item_array.get(menuID).setPrice(price);
			break;
		default:
			break;
		}
		System.out.println("Item edited.");

	}

	/**
	 * Gets a menu ID from the user
	 * Removes a menu item that matches the menuID
	 */
	public void removeMenuItem() {
		int menuID = 0;
		// call boundary to get user data
		this.printMenu();
		menuID = MenuBoundary.askMenuItemID(menu_item_array.size());
		if (menuID != -1) {
			menuID -= 1;
			this.menu_item_array.remove(menuID);
			System.out.println("Item removed.");
		} else
			System.out.println("Invalid operation.");
	}

	/**
	 * Prints out all the menu items, description and price
	 */
	public void printMenu() {
		if (getMenuSize() == 0) {
			System.out.println("No menu items.");
			return;
		}
		System.out.println("~~~~~~~~~~~~~~Menu items~~~~~~~~~~~~~~");
		for (int i = 0; i < getMenuSize(); i++) {
			System.out.printf("%-5s", "(" + (i + 1) + ")");
			System.out.printf("%-25s %-30s %s%6.2f\n", menu_item_array.get(i).getName(),
					menu_item_array.get(i).getDescription(), "$", menu_item_array.get(i).getPrice());

		}
		System.out.println();
	}

	/**
	 * @return menu_item_array
	 */
	public ArrayList<MenuItem> getMenuItemArray(){
		return menu_item_array;
	}
	
	/**
	 * @param ID
	 * @return the menu item given by the ID
	 */
	public MenuItem getMenuItemByID(int ID) {
		return menu_item_array.get(ID - 1);
	}

	/**
	 * @return the size of the menu_item_array
	 */
	public int getMenuSize() {
		return menu_item_array.size();
	}

	/**
	 * Adds a menu item into the menu_item_array
	 * @param item
	 */
	public void addItem(MenuItem item) {
		menu_item_array.add(item);
	}

}
