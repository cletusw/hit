package model.report;

import java.io.Serializable;

import model.ItemManager;
import model.ProductContainerManager;
import model.ProductManager;
import model.persistence.InventoryDao;

/**
 * A class for managing persistent reports
 * 
 * @author Seth Stewart
 * 
 */
@SuppressWarnings("serial")
public class ReportManager implements Serializable {
	private ExpiredItemsReport expiredItemsReport;
	private NMonthSupplyReport nMonthSupplyReport;
	private NoticesReport noticesReport;
	private ProductStatisticsReport productStatisticsReport;
	private RemovedItemsReport removedItemsReport;

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

	/**
	 * 
	 * @param r
	 * @param dao
	 * @throws IllegalAccessException
	 */
	public void setExpiredItemsReport(ExpiredItemsReport r, InventoryDao dao)
			throws IllegalAccessException {
		if (dao == null)
			throw new IllegalAccessException("Must be accessed by valid dao object");

		expiredItemsReport = r;
	}

	/**
	 * 
	 * @param r
	 * @param dao
	 * @throws IllegalAccessException
	 */
	public void setNMonthSupplyReport(NMonthSupplyReport r, InventoryDao dao)
			throws IllegalAccessException {
		if (dao == null)
			throw new IllegalAccessException("Must be accessed by valid dao object");

		nMonthSupplyReport = r;
	}

	/**
	 * 
	 * @param r
	 * @param dao
	 * @throws IllegalAccessException
	 */
	public void setNoticesReport(NoticesReport r, InventoryDao dao)
			throws IllegalAccessException {
		if (dao == null)
			throw new IllegalAccessException("Must be accessed by valid dao object");

		noticesReport = r;
	}

	/**
	 * 
	 * @param r
	 * @param dao
	 * @throws IllegalAccessException
	 */
	public void setProductStatiscticsReport(ProductStatisticsReport r, InventoryDao dao)
			throws IllegalAccessException {
		if (dao == null)
			throw new IllegalAccessException("Must be accessed by valid dao object");

		productStatisticsReport = r;
	}

	/**
	 * 
	 * @param r
	 * @param dao
	 * @throws IllegalAccessException
	 */
	public void setRemovedItemsReport(RemovedItemsReport r, InventoryDao dao)
			throws IllegalAccessException {
		if (dao == null)
			throw new IllegalAccessException("Must be accessed by valid dao object");

		removedItemsReport = r;
	}
}
