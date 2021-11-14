package io;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * This class contains a static method that links the app to CSV files within the user's computer.
 * The intent is to store object data long-term after the app is closed for archival purposes or for later retrieval.
 * Class/object specific conversion methods are not defined here; This class is only for general purpose CSV writing.
 * Said conversion logic is implemented by IO classes packaged with their relevant data objects, realising the <code>Exportable</code> interface.  
 *
 */
public class CSVWriter {

	/**
	 * Exports formatted object data from an <code>Exportable</code> object via its export method to a CSV file.
	 * 
	 * @param fileName the location of the destination CSV as a filepath (<code>String</code>) recognised by your operating system
	 * @param source an <code>Exportable</code> object from which data is passed from
	 * @throws <code>IOException</code> thrown when the named file from <code>fileName</code> is not found
	 */
	public static void exportCSV(String fileName, Exportable source) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		ArrayList<String> temp = source.exportData();
		for (int i = 0; i < temp.size(); i++) {
			printWriter.print(temp.get(i) + "\n");
		}
		printWriter.close();
	}

}
