package model.report;

import java.util.Arrays;

import model.Item;
import model.Product;
import model.ProductContainerManager;
import model.ProductGroup;
import model.StorageUnit;
import model.report.builder.ReportBuilder;
import model.visitor.InventoryVisitor;

public class ExpiredItemsReport extends Report implements InventoryVisitor {
	/**
	 * Set up an empty ExpiredItemsReport.
	 * 
	 * @param productContainerManager
	 *            Manager to use for finding Items and their expiration dates
	 * 
	 * @pre true
	 * @post true
	 */
	public ExpiredItemsReport(ProductContainerManager productContainerManager) {
	}

	/**
	 * Construct a completed ExpiredItemsReport, showing all Items in the system that have
	 * passed their expiration date.
	 * 
	 * @param builder
	 *            ReportBuilder to use
	 * 
	 * @pre true
	 * @post (new Date()).getTime() - getLastRunTime().getTime() < 1000
	 */
	public void construct(ReportBuilder builder) {
		builder.addDocumentTitle("Expired Items");
		builder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
	}

	@Override
	public void visit(Item item) {
	}

	@Override
	public void visit(Product product) {
	}

	@Override
	public void visit(ProductGroup productGroup) {
	}

	@Override
	public void visit(StorageUnit storageUnit) {
	}
}
