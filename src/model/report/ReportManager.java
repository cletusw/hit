package model.report;

import java.io.Serializable;

import model.ItemManager;
import model.ProductContainerManager;
import model.ProductManager;

/**
 * A class for managing persistent reports
 * 
 * @author Seth Stewart
 * 
 */
public class ReportManager implements Serializable {
	private final ExpiredItemsReport expiredItemsReport;
	private final NMonthSupplyReport nMonthSupplyReport;
	private final NoticesReport noticesReport;
	private final ProductStatisticsReport productStatisticsReport;
	private final RemovedItemsReport removedItemsReport;

	public ReportManager(ProductContainerManager productContainerManager,
			ProductManager productManager, ItemManager itemManager) {
		expiredItemsReport = new ExpiredItemsReport(productContainerManager);
		nMonthSupplyReport = new NMonthSupplyReport(productManager, productContainerManager);
		noticesReport = new NoticesReport(productContainerManager);
		productStatisticsReport = new ProductStatisticsReport(itemManager, productManager);
		removedItemsReport = new RemovedItemsReport(itemManager);
	}

	/**
	 * Getter for the ExpiredItemsReport
	 * 
	 * @return the ExpiredItemsReport
	 */
	public ExpiredItemsReport getExpiredItemsReport() {
		return expiredItemsReport;
	}

	/**
	 * Getter for the NMonthSupplyReport
	 * 
	 * @return the NMonthSupplyReport
	 */
	public NMonthSupplyReport getnMonthSupplyReport() {
		return nMonthSupplyReport;
	}

	/**
	 * Getter for the NoticesReport
	 * 
	 * @return the NoticesReport
	 */
	public NoticesReport getNoticesReport() {
		return noticesReport;
	}

	/**
	 * Getter for the ProductStatisticsReport
	 * 
	 * @return the ProductStatisticsReport
	 */
	public ProductStatisticsReport getProductStatisticsReport() {
		return productStatisticsReport;
	}

	/**
	 * Getter for the RemovedItemsReport
	 * 
	 * @return the RemovedItemsReport
	 */
	public RemovedItemsReport getRemovedItemsReport() {
		return removedItemsReport;
	}

}
