package model.report.builder;

import common.NonEmptyString;

public abstract class FileBuilder implements ReportBuilder {

	String filename;

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
	 */
	public abstract void openFile();

}
