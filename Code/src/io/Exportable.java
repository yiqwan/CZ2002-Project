package io;

import java.util.ArrayList;

/**
 * This class defines an interface for wrappers to export object data via the <code>CSVWriter</code> class.
 * The classes/objects implementing this interface should be aware of the internal structure of the objects they aim to export.
 * This is done by how the wrapper implements the <code>exportData()</code> method defined here.
 */
public interface Exportable {

	/**
	 * The method in which the <code>CSVWriter</code> class gets object data from a wrapper object.
	 * Implementations of this method are expected to format each object as a single line in CSV format.
	 * An <code>ArrayList</code> of <code>String</code> representing the aggregated object data is returned.
	 * The format in which this method uses to encode data should be consistent with its sister method <code>importData()</code> within the <code>Importable</code> class.
	 * 
	 * @return the aggregation of <code>String</code> data, each element represents a single object which will correspond to a single line in the final CSV.
	 */
	public ArrayList<String> exportData();
}
