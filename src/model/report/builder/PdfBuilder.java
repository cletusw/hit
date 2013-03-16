package model.report.builder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import common.NonEmptyString;

public class PdfBuilder extends FileBuilder {

	private Document document;
	private PdfWriter writer;
	private PdfContentByte cb;

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
		cb = writer.getDirectContent();
	}

	@Override
	public void addListItem(String content) {
	}

	@Override
	public void addRow(List<String> row) {
	}

	@Override
	public void addTitle(String title) {

	}

	@Override
	public void close() {
		document.close();
	}

	@Override
	public void endList() {
	}

	@Override
	public void endTable() {
	}

	@Override
	public void openFile() {
		// TODO Auto-generated method stub

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
