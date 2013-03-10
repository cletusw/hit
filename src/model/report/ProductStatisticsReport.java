package model.report;

import model.ItemManager;
import model.ProductManager;
import model.report.builder.ReportBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ProductStatisticsReport extends Report {
	/**
	 * Set up an empty ProductStatisticsReport.
	 * 
	 * @param itemManager
	 *            Manager to use for gathering removed Item data
	 * @param productManager
	 *            Manager to use for gathering Product data
	 * 
	 * @pre true
	 * @post true
	 */
	public ProductStatisticsReport(ItemManager itemManager, ProductManager productManager) {
	}

	/**
	 * Construct a completed ProductStatisticsReport where the reporting period is the number
	 * of months specified
	 * 
	 * @param builder
	 *            ReportBuilder to use
	 * @param months
	 *            Reporting Period
	 * 
	 * @pre true
	 * @post (new Date()).getTime() - getLastRunTime().getTime() < 1000
	 */
	public void construct(ReportBuilder builder, int months) {
		throw new NotImplementedException();
	}
}
