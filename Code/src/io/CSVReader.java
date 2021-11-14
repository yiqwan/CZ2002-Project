package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class contains a static method that links the app to CSV files within the user's computer.
 * The intent is to read object data stored outside the app, to restore a previous state or to maintain archived data.
 * Class/object specific conversion methods are not defined here; This class is only for general purpose CSV reading.
 * Said conversion logic is implemented by IO classes packaged with their relevant data objects, realising the <code>Importable</code> interface.  
 *
 */

public class CSVReader {
	/**
	 * Reads a CSV file, and sends all lines in an <code>ArrayList\<String\></code> to an <code>Importable</code> object which is able to parse each line properly.
	 * 
	 * @param fileName the location of the csv file as a filepath (<code>String</code>) recognised by your operating system
	 * @param destination an <code>Importable</code> object to which the read data will be passed to.
	 */
	public static void importCSV(String fileName, Importable destination) {
		Path pathToFile = Paths.get(fileName);
		ArrayList<String> lines = new ArrayList<String>();
		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				lines.add(line);
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		destination.importData(lines);
	}

}
