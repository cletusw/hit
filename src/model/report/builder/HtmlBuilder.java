package model.report.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HtmlBuilder implements ReportBuilder {
	private String EXTENSION = ".html";

	private boolean openTable = false;
	private int tableColumns = -1;

	private String openHTML = "<html><head><style>";
	private String openBody = "</style></head><body>";
	private String closeBody = "</body></html>";
	private List<String> styles;
	private StringBuilder content;

	private String documentTitleClass = "class=\"documentTitle\"";
	private String sectionTitleClass = "class=\"sectionTitle\"";
	private String listTitleClass = "class=\"listTitle\"";
	private String listItemClass = "class=\"listItem\"";

	public HtmlBuilder() {
		styles = new ArrayList<String>();

		// add styles
		styles.add("\nbody{font-family:calibri;}");
		styles.add("\ntable{border-collapse:collapse;width:70%;"
				+ "border:1.25px solid #000;margin:10px auto;}");
		styles.add("\ntable th{font-weight:bold;font-size:16px;border:1.25px solid #000;"
				+ "text-align: left;padding:5px 5px 0px 2px;}");
		styles.add("\ntable td{border:1.25px solid #000;padding:5px 5px 0px 2px;}");
		styles.add("\n.documentTitle{text-align:center;font-size: 30px;font-weight: bold;"
				+ "margin-bottom:20px;margin-top:20px;}");
		styles.add("\n.sectionTitle{text-align:left;font-size:22px;font-weight:normal;"
				+ "width:70%;margin: 15px auto;}");
		styles.add("\n.listTitle{font-size:18px;margin: 20px auto 0px auto;width:70%;}");
		styles.add("\n.listItem{font-size:18px;margin: 1px auto;width:70%;}");

		content = new StringBuilder();
	}

	@Override
	public void addDocumentTitle(String title) {
		endPreviousElement();
		content.append("<div " + documentTitleClass + ">" + title + "</div>");
	}

	@Override
	public void addListItem(String item) {
		endTable();
		content.append("<div " + listItemClass + ">" + item + "</div>");
	}

	@Override
	public void addSectionTitle(String title) {
		endPreviousElement();
		content.append("<div " + sectionTitleClass + ">" + title + "</div>");
	}

	@Override
	public void addTableRow(List<String> row) {
		if (!openTable)
			throw new IllegalStateException("Cannot write row before opening table");
		if (row.size() != tableColumns)
			throw new IllegalArgumentException(
					"Number of strings in list must be equal to number of columns in table");

		content.append("<tr>");
		for (String cell : row) {
			content.append("<td>" + cell + "</td>");
		}
		content.append("</tr>");

	}

	private void endPreviousElement() {
		endTable();
	}

	private void endTable() {
		if (openTable)
			content.append("</table>");
		openTable = false;
		tableColumns = -1;
	}

	@Override
	public File print(String filename) throws IOException {
		filename += EXTENSION;
		FileWriter file = new FileWriter(filename);
		PrintWriter writer = new PrintWriter(file);

		// start document
		writer.write(openHTML);
		for (String style : styles) {
			writer.write(style);
		}

		writer.write(openBody);
		writer.write(content.toString());
		writer.write(closeBody);
		writer.close();
		return new File(filename);
	}

	@Override
	public void startList(String title) {
		endPreviousElement();
		content.append("<div " + listTitleClass + ">" + title + "</div>");
	}

	@Override
	public void startTable(List<String> headers) {
		content.append("<table>");
		content.append("<tr>");
		for (String cell : headers) {
			content.append("<th>" + cell + "</th>");
		}
		content.append("</tr>");
		openTable = true;
		tableColumns = headers.size();
	}
}
