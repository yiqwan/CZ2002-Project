package io;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains methods which ensures that only valid values are passed to the functions calling them.
 */
public class ExceptionHandler {
	/**
	 * Prompts the user until they input an integer.
	 * 
	 * @return the <code>int</code> taken from the user
	 */
	public static int scanInt() {
		Scanner sc = new Scanner(System.in);
		int integer_input;
		while (true) {
			try {
				integer_input = sc.nextInt(); // keeps scanning the remaining character
				return integer_input;
			} catch (Exception e) {
				System.out.println("Please enter a correct format(integer).");
			} finally {
				sc.nextLine(); // to take in the remaining
			}
		}
	}

	/**
	 * Prompts the user until they input a double.
	 *
	 * @return the <code>double</code> taken from the user
	 */
	public static double scanDouble() {
		Scanner sc = new Scanner(System.in);
		double double_input;
		while (true) {
			try {
				double_input = sc.nextDouble(); // keeps scanning the remaining character
				return double_input;
			} catch (Exception e) {
				System.out.println("Please enter a correct format(double).");
			} finally {
				sc.nextLine();
			}
		}
	}

	/**
	 * Prompts the user until they input the correct string format
	 * 
	 * @return the <code>String</code> taken from the user
	 */
	public static String scanString() {
		Scanner sc = new Scanner(System.in);
		String string_input;
		while (true) {
			try {
				string_input = sc.nextLine(); // keeps scanning the remaining character
				return string_input;
			} catch (Exception e) {
				System.out.println("Please enter a correct format(string).");
			}
		}
	}

	/**
	 * Prompts the user until they input an integer within the range defined by <code>(min, max)</code>.
	 * 
	 * @param min the minimum accepted <code>int</code> value
	 * @param max the maximum accepted <code>int</code> value
	 * @return the integer that is taken from the user
	 */
	
	public static int scanIntRange(int min, int max) {
		int integer_input;
		while (true) {
			integer_input = ExceptionHandler.scanInt();
			if (min <= integer_input && integer_input <= max) {
				return integer_input;
			} else {
				System.out.println("Please enter the correct range.");
			}

		}
	}

	/**
	 * Prompts the user until they enter a valid date and time in the displayed format.
	 * 
	 * @return the data and time entered, as a <code>LocalDateTime</code> format
	 */
	public static LocalDateTime scanLocalDateTime() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				return (LocalDateTime.parse(sc.nextLine(), format));
			} catch (Exception e) {
				System.out.println("Enter the date and time in the format: dd-mm-yyyy HH:mm");
			}
		}
	}

	/**
	 * Prompts the user until they enter a valid date in the displayed format.
	 * 
	 * @return the data entered, as a <code>LocalDate</code> format
	 */
	public static LocalDate scanDate() {
		while (true) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String text = ExceptionHandler.scanString();
				LocalDate localDate = LocalDate.parse(text, formatter);
				return (localDate);
			} catch (Exception e) {
				System.out.println("Enter the date in the format: dd-MM-yyyy");
			}
		}
	}
}
