package model.report.builder;

import java.util.List;

public class HtmlBuilder implements ReportBuilder {

	@Override
	public void addDocumentTitle(String title) {
	}

	@Override
	public void addListItem(String content) {
	}

	@Override
	public void addSectionTitle(String title) {
	}

	@Override
	public void addTableRow(List<String> row) {
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

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
	public void startList(String title) {
	}

	@Override
	public void startTable(List<String> headers) {
	}
}
