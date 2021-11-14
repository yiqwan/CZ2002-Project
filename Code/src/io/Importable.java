package io;

import java.util.ArrayList;

/**
 * This class defines an interface for wrappers to import object data via the <code>CSVReader</code> class.
 * The classes/objects implementing this interface should be aware of the internal structure of the objects they aim to import, as well as the format used within each line of the CSV.
 * This is done by how the wrapper implements the <code>importData()</code> method defined here.
 */
public interface Importable {

	/**
	 * The method in which the <code>CSVReader</code> class gets object data from a wrapper object.
	 * Implementations of this method are expected to parse each element of the <code>ArrayList</code> into a single object.
	 * The way in which this method parses data should be consistent with its sister method <code>exportData()</code> within the <code>Exportable</code> class.
	 * 
	 */
	public void importData(ArrayList<String> data);
}
