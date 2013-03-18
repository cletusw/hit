package model.report.builder;

import java.io.IOException;
import java.util.List;

public interface ReportBuilder {

	/**
	 * Create a document title on the document at the current location in the document with
	 * value title. Formatting is large, bold font with center alignment. This will close any
	 * open element (table, list, etc)
	 * 
	 * @param title
	 *            String to be printed on document.
	 * 
	 * @pre title != null
	 * @post true
	 */
	public void addDocumentTitle(String title);

	/**
	 * Adds an item to a currently open list. Formatting is slightly indented with a leading
	 * hyphen.
	 * 
	 * @param content
	 *            String to add to list
	 * 
	 * @pre list must be started
	 * @pre content != null
	 * 
	 * @post true
	 */
	public void addListItem(String content);

	/**
	 * Adds a section title at the current location in the document. Formatting is medium font,
	 * left justified. This will close any open element (table, list, etc)
	 * 
	 * @param title
	 *            the title to print on the document
	 * 
	 * @pre title != null
	 * @post true
	 */
	public void addSectionTitle(String title);

	/**
	 * Adds a new row to a table with the given values. There must be a table open in the
	 * builder and the row must have the correct number of values. This will close any
	 * previously open element.
	 * 
	 * @param row
	 *            String entries to add to the open table
	 * 
	 * @pre row != null
	 * @pre row.size() == table width in columns
	 * @pre builder has started table
	 * 
	 * @post current table has entries from row
	 * @post current table width = row.size()
	 */
	public void addTableRow(List<String> row);

	/**
	 * Closes any open elements in the document and prints to the given filename. The filename
	 * should not include an extension, the builder will create a file with the appropriate
	 * file extension.
	 * 
	 * @param filename
	 *            String filename to write to
	 * @throws IOException
	 *             If the file is not found, or cannot be written to
	 */
	public void print(String filename) throws IOException;

	/**
	 * Opens a new list with the first row equal to the value of title. This closes any
	 * previously opened elements.
	 * 
	 * @param title
	 *            String value of the first row in the list (not indented)
	 * 
	 * @pre title != null
	 * @post list currently open
	 */
	public void startList(String title);

	/**
	 * Starts new table with headers using the given List values as the header row for the new
	 * table. If a table is already started, it will close the current table and open a new
	 * one.
	 * 
	 * @param headers
	 *            first row of the table contianing description headers.
	 * 
	 * @pre headers != null
	 * 
	 * @post table open with width in columns = headers.size
	 */
	public void startTable(List<String> headers);
}
