package model.report;

import java.util.Arrays;
import java.util.Date;

import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;
import model.StorageUnit;
import model.report.builder.ReportBuilder;
import model.visitor.InventoryVisitor;

public class ExpiredItemsReport extends Report implements InventoryVisitor {
	private ReportBuilder builder;
	private ProductContainerManager productContainerManager;

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
		this.productContainerManager = productContainerManager;
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
		this.builder = builder;

		builder.addDocumentTitle("Expired Items");
		builder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		for (StorageUnit su : productContainerManager.getStorageUnits()) {
			su.accept(this);
		}
	}

	/**
	 * @pre builder != null
	 */
	@Override
	public void visit(Item item) {
		ProductContainer itemContainer = item.getContainer();
		String storageUnit = (itemContainer instanceof StorageUnit) ? itemContainer.getName()
				: productContainerManager.getRootStorageUnitForChild(itemContainer).getName();
		String productGroup = (itemContainer instanceof ProductGroup) ? itemContainer
				.getName() : "";
		if (item.getExpirationDate().before(new Date())) {
			builder.addTableRow(Arrays.asList(item.getProduct().getDescription(), storageUnit,
					productGroup, item.getEntryDate().toString(), item.getExpirationDate()
							.toString(), item.getBarcode()));
		}
	}

	@Override
	public void visit(Product product) {
		// Do nothing
	}

	@Override
	public void visit(ProductContainer productContainer) {
		for (Product product : productContainer.getProducts()) {
			for (Item item : productContainer.getItemsForProduct(product)) {
				item.accept(this);
			}
		}
	}
}
