package order;

import java.util.ArrayList;

import order.Staff.Gender;

/**
 * A control stereotype
 * Represents a staff manager in the system.
 * A staff manager can manage all the reservations for the restaurant
 */
public class StaffManager {
	
	/**
	 * An ArrayList stores all the staff
	 */
	private static ArrayList<Staff> staffList = new ArrayList<Staff>();

	/**
	 * An constructor to initialize all the staff information and add them into the staffList
	 */ 
	static {
		addStaff("Alice Ang", "Manager", Gender.FEMALE);
		addStaff("Bryan Baey", "Intern", Gender.MALE);
		addStaff("Charles Choo", "Full-Time Waiter", Gender.MALE);
		addStaff("Daniel Dao", "Full-Time Waiter", Gender.MALE);
		addStaff("Elaine Eng", "Full-Time Waiter", Gender.FEMALE);
		addStaff("Francis Fong", "Assistant Manager", Gender.MALE);
		addStaff("Gordon Goh", "Intern", Gender.MALE);
		addStaff("Helen Hui", "Intern", Gender.FEMALE);
		addStaff("Ivan Ip", "Part-Time Waiter", Gender.MALE);
		addStaff("Joshua Juan", "Assistant Manager", Gender.MALE);
	}

	/**
	 * Get the number of staff
	 * @return numStaff, number of staff
	 */
	public static int numStaff() {
		return staffList.size();
	}

	/**
	 * Print the staff information
	 */
	public static void printStaff() {
		for (int i = 0; i < numStaff(); i++) {
			System.out.println("-----Staff ID: " + i + "-----");
			staffList.get(i).print();
		}
	}

	/**
	 * @param staff ID
	 * @return the name of the staff
	 */
	public static String getName(int staffID) {
		return staffList.get(staffID).getName();
	}


	/**
	 * Add staff into the staffList
	 * @param name
	 * @param jobTitle
	 * @param gender
	 */
	public static void addStaff(String name, String jobTitle, Gender gender) {
		staffList.add(new Staff(name, jobTitle, gender));
	}
    

	/**
	 * Remove the staff from the staffList
	 * @param staff ID
	 * @return -1, if the input staff ID is invalid
	 * @return 0, if the input staff ID is valid
	 */
	public static int removeStaff(int staffID) {
		if (!isValidStaffID(staffID)) {
			return -1;
		}
		staffList.remove(staffID);
		return 0;
	}

	/**
	 * @param staffID
	 * @return true, if staff ID is within 1 and number of staff
	 * @return false, else
	 */
	private static boolean isValidStaffID(int staffID) {
		if ((staffID >= numStaff()) || (staffID < 0))
			return false;
		return true;
	}
}
