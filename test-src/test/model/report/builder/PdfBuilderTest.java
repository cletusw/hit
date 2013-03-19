package test.model.report.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.report.builder.PdfBuilder;
import model.report.builder.ReportBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PdfBuilderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ReportBuilder builder = new PdfBuilder();
		builder.addDocumentTitle("Items Removed Since 12/26/2011 02:45PM");

		List<String> headers = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			headers.add("header " + i);
		}

		builder.startTable(headers);

		for (int i = 0; i < 20; i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < 5; j++) {
				row.add("Body Cell " + j);
			}
			builder.addTableRow(row);
		}

		builder.addSectionTitle("New Section");
		headers.clear();
		for (int i = 0; i < 4; i++) {
			headers.add("header " + i);
		}
		builder.startTable(headers);
		for (int i = 0; i < 20; i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < 4; j++) {
				row.add("Body Cell " + j);
			}
			builder.addTableRow(row);
		}

		builder.addSectionTitle("List");
		builder.startList("Product group Basement Closet:: Toothpaste has a 3-month supply (50 gallons) that is inconsistent with the following products:");
		for (int i = 0; i < 10; i++) {
			builder.addListItem("Colgate::Colgate Toothpaste - Total Whitening (size: 6 ounces)");
			builder.addListItem("Crest::Crest Extra Whitening Toothpaste (size: 6.2 ounces)");
		}

		try {
			builder.print("helloworld");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
