package model.report.builder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.NonEmptyString;

public class PdfBuilder extends FileBuilder {

	private Document document;
	private PdfWriter writer;
	private PdfPTable table;
	private Paragraph list;

	/**
	 * Constructs a new PdfBuilder writing to the specified filename.
	 * 
	 * @param file
	 *            NonEmptyString filename to write pdf content to
	 * @throws FileNotFoundException
	 *             thrown if unable to create a FileOutputStream using filename
	 * @throws DocumentException
	 *             thrown if unable to get PdfWriter for document.
	 */
	public PdfBuilder(NonEmptyString file) throws FileNotFoundException, DocumentException {
		super(file);
		document = new Document();
		writer = PdfWriter.getInstance(document, new FileOutputStream(filename.toString()));
		document.open();
	}

	@Override
	public void addDocumentTitle(String title) {
		if (list != null || table != null)
			throw new IllegalStateException("Must have all elements closed");

		Paragraph titleParagraph = new Paragraph();
		titleParagraph.setSpacingAfter(25);
		titleParagraph.setAlignment(Element.ALIGN_CENTER);
		titleParagraph.getFont().setSize(19);
		titleParagraph.getFont().setStyle(Font.BOLD);
		titleParagraph.add(new Chunk(title));
		try {
			document.add(titleParagraph);
		} catch (DocumentException e) {
			throw new IllegalStateException("Unable to add title to document");
		}
	}

	@Override
	public void addListItem(String content) {
		if (list == null)
			throw new IllegalStateException("List must be opened before adding content");

		Chunk newLine = new Chunk(" - " + content);
		list.add(newLine);
	}

	private void addRow(List<String> rowValues, int style, float fontSize) {
		if (table == null)
			throw new IllegalStateException("Cannot write row before opening table");
		if (list != null)
			throw new IllegalStateException("Must have all elements closed");
		for (String value : rowValues) {
			PdfPCell cell = new PdfPCell();
			Chunk content = new Chunk(value);
			content.getFont().setSize(fontSize);
			content.getFont().setStyle(style);
			cell.addElement(content);
			table.addCell(cell);
		}
	}

	@Override
	public void addSectionTitle(String title) {
		if (list != null || table != null)
			throw new IllegalStateException("Must have all elements closed");

		Paragraph titleParagraph = new Paragraph();
		titleParagraph.setSpacingAfter(10);
		titleParagraph.getFont().setSize(15);
		titleParagraph.add(new Chunk(title));
		try {
			document.add(titleParagraph);
		} catch (DocumentException e) {
			throw new IllegalStateException("Unable to add subtitle to document");
		}
	}

	@Override
	public void addTableRow(List<String> row) {
		addRow(row, Font.NORMAL, 8);
	}

	private boolean canStartNewElement() {
		return (list != null && table != null);
	}

	@Override
	public void close() {
		endTable();
		endList();
		document.close();
	}

	private void endList() {
		if (list != null)
			try {
				document.add(list);
			} catch (DocumentException e) {
				throw new IllegalStateException("Unable to end previous list");
			}
		list = null;
	}

	private void endTable() {
		if (table != null)
			try {
				document.add(table);
			} catch (DocumentException e) {
				throw new IllegalStateException("Unable to end table");
			}
		table = null;
	}

	@Override
	public void startList(String title) {
		if (list != null || table != null) {
			throw new IllegalStateException(
					"Must close previous table and list before starting new list");
		}
		list = new Paragraph();
		Chunk firstLine = new Chunk(title);
		list.add(firstLine);
	}

	@Override
	public void startTable(List<String> headers) {
		table = new PdfPTable(headers.size());
		table.setWidthPercentage(100);
		table.setSpacingAfter(20);
		addRow(headers, Font.BOLD, 10);
	}
}
