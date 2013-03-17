package model.report.builder;

import java.util.List;

public interface ReportBuilder {

	public void addDocumentTitle(String title);

	public void addListItem(String content);

	public void addSectionTitle(String title);

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
	public void addTableRow(List<String> row);

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
