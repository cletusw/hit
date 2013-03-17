package model.report.builder;

import java.io.File;
import java.io.IOException;

import common.NonEmptyString;

public abstract class FileBuilder implements ReportBuilder {

	String filename;

	/**
	 * Constructs a new FileBuilder writing to the specified filename. Each fileBuilder may
	 * define its own extension or filetype, so the filename should not include an extension.
	 * 
	 * @param filename
	 *            NonEmptyString filename to write content to
	 */
	public FileBuilder(NonEmptyString filename) {
		this.filename = filename.toString();
	}

	/**
	 * Closes this builder's file resource, signaling that building is complete. Should finish
	 * any incomplete sections and close the file.
	 */
	public abstract void close();

	/**
	 * Opens the file built by this builder.
	 * 
	 * @throws IOException
	 */
	public void openFile() throws IOException {
		java.awt.Desktop.getDesktop().open(new File(filename));
	}

}
