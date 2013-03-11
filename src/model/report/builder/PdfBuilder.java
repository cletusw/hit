package model.report.builder;

import java.util.List;

public class PdfBuilder implements ReportBuilder {

	@Override
	public void addListItem(String content) {
	}

	@Override
	public void addRow(List<String> row) {
	}

	@Override
	public void endList() {
	}

	@Override
	public void endTable() {
	}

	/**
	 * Creates a file containing the content added in the file filename. If the given filename
	 * already exists, no report will be created.
	 * 
	 * @param filename
	 *            Location to create the report
	 * 
	 * @pre PdfBuilder has content added
	 * @pre filename is not already in use
	 * 
	 * @post A new file is created at filename
	 */
	public void printToFile(String filename) {
	}

	@Override
	public void setTitle(String title) {
	}

	@Override
	public void startList(String title) {
	}

	@Override
	public void startTable(List<String> headers) {
	}

	@Override
	public void startTable(String title, List<String> headers) {
	}

}
