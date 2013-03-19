package model.report;

import java.util.Date;

import model.ItemManager;
import model.report.builder.ReportBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RemovedItemsReport extends Report {
	/**
	 * Set up an empty RemovedItemsReport.
	 * 
	 * @param itemManager
	 *            Manager to use for gathering removed Item data
	 * 
	 * @pre true
	 * @post true
	 */
	public RemovedItemsReport(ItemManager itemManager) {
	}

	/**
	 * Construct a completed RemovedItemsReport, starting from the last time the report was
	 * run.
	 * 
	 * @param builder
	 *            ReportBuilder to use
	 * 
	 * @pre true
	 * @post (new Date()).getTime() - getLastRunTime().getTime() < 1000
	 */
	public void construct(ReportBuilder builder) {
		construct(builder, getLastRunTime());
		updateLastRunTime();
	}

	/**
	 * Construct a completed RemovedItemsReport, starting from the given startDate.
	 * 
	 * @param builder
	 *            ReportBuilder to use
	 * @param startDate
	 *            Start date for reporting period
	 * 
	 * @pre true
	 * @post (new Date()).getTime() - getLastRunTime().getTime() < 1000
	 */
	public void construct(ReportBuilder builder, Date startDate) {
		updateLastRunTime();
		throw new NotImplementedException();
	}
}
