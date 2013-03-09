package model.report.builder;

import java.util.List;

public interface ReportBuilder {

	/**
	 * Adds a new row to a table with the given values. There must be a table open in the
	 * builder and the row must have the correct number of values.
	 * 
	 * @param row
	 *            String entries to add to the open table
	 * 
	 * @pre row != null
	 * @pre row.size() == table width in columns
	 * @pre builder has started table
	 * 
	 * @post current table has entries from row
	 */
	public void addRow(List<String> row);

	/**
	 * Creates a file containing the content added in the file filename. If the given filename
	 * already exists, no report will be created.
	 * 
	 * @param filename
	 *            Location to create the report
	 * 
	 * @pre ReportBuilder has content added
	 * @pre filename is not already in use
	 * 
	 * @post A new file is created at filename
	 */
	public void print(String filename);

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

	/**
	 * Starts new table with headers using the given List values as the header row for the new
	 * table, and the title placed above the table. If a table is already started, it will
	 * close the current table and open a new one.
	 * 
	 * @param title
	 *            Title to be printed as name for the table
	 * @param headers
	 *            first row of the table contianing description headers.
	 * 
	 * @pre headers != null
	 * @pre title != null
	 * 
	 * @post table open with width in columns = headers.size
	 */
	public void startTable(String title, List<String> headers);
}
