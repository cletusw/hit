package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains logic to generate a pdf of barcodes for a list of items
 * 
 * @author Matthew
 * 
 * @invariant itemsToPrint != null
 */
public class BarcodePrinter {

	private static BarcodePrinter _instance;

	/**
	 * Allows global access to singleton BarcodePrinter instance
	 * 
	 * @return singleton instance of BarcodePrinter
	 * 
	 * @pre true
	 * @post _instance != null
	 */
	public static BarcodePrinter getInstance() {
		if (_instance == null)
			_instance = new BarcodePrinter();

		return _instance;
	}

	private List<Item> itemsToPrint;

	private BarcodePrinter() {
		itemsToPrint = new ArrayList<Item>();
	}

	/**
	 * Adds an item to a list of items to be printed with the next printBatch()
	 * command
	 * 
	 * @param i
	 *            Item to be added to list
	 * 
	 * @pre i != null
	 * @post i in itemsToPrint
	 */
	public void addItemToBatch(Item i) {
		itemsToPrint.add(i);
	}

	/**
	 * Prints a pdf of Items in this batch (added by addItemToBatch()). Creates
	 * a unique filename to save this batch of barcodes to. Filename is of the
	 * format labels-(Date).pdf and is saved in the "Labels" directory. If the
	 * director does not exist, the directory will first be created.
	 * 
	 * @return NonEmptyString filename where the batch was saved
	 * 
	 * @pre true
	 * @post itemsToPrint.size() == 0
	 * @post new pdf saved to Labels directory
	 */
	public NonEmptyString printBatch() {
		// print barcodes -- see example @
		// http://itextpdf.com/examples/iia.php?id=297
		// pick new filename (labels-date.pdf)
		// save barcodes
		// empty list
		// return filename
		return new NonEmptyString("");
	}
}
