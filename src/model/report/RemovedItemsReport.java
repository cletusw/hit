package model.report;

import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RemovedItemsReport extends Report {

	/**
	 * Generate a new RemovedItemsReport, starting at the last time the report was run.
	 * 
	 * @param format
	 *            ReportFormat to create.
	 * 
	 * @pre true
	 * @post true
	 */
	public void run(ReportFormat format) {
		throw new NotImplementedException();
	}

	/**
	 * Generate a new RemovedItemsReport, starting from the given dateFilter
	 * 
	 * @param format
	 *            ReportFormat to create.
	 * @param dateFilter
	 *            threshold date for statistics
	 * 
	 * @pre true
	 * @post true
	 */
	public void run(ReportFormat format, Date dateFilter) {
		throw new NotImplementedException();
	}
}
