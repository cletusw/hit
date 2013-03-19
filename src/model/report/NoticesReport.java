package model.report;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;
import model.ProductQuantity;
import model.StorageUnit;
import model.Unit;
import model.report.builder.ReportBuilder;
import model.visitor.InventoryVisitor;

@SuppressWarnings("serial")
public class NoticesReport extends Report implements InventoryVisitor {
	private ReportBuilder builder;
	private ProductContainerManager pcManager;

	/**
	 * Set up an empty NoticesReport.
	 * 
	 * @param productContainerManager
	 *            Manager to use for finding notices
	 * 
	 * @pre true
	 * @post true
	 */
	public NoticesReport(ProductContainerManager productContainerManager) {
		pcManager = productContainerManager;
		reportName = "notices";
	}

	/**
	 * Construct a completed NoticesReport
	 * 
	 * @param builder
	 *            ReportBuilder to use
	 * 
	 * @pre true
	 * @post (new Date()).getTime() - getLastRunTime().getTime() < 1000
	 */
	public void construct(ReportBuilder builder) {
		updateLastRunTime();

		// Store the builder for use by the Visitor pattern
		this.builder = builder;

		builder.addDocumentTitle("Notices");
		builder.addSectionTitle("3-Month Supply Warnings");

		for (StorageUnit su : pcManager.getStorageUnits()) {
			su.accept(this);
		}

		try {
			File file = builder.print(getFileName());
			java.awt.Desktop.getDesktop().open(file);
		} catch (IOException e) {
			System.out.println("Not able to open!! " + getFileName());
		}

		this.builder = null;
	}

	@Override
	public void visit(Item item) {
	}

	@Override
	public void visit(Product product) {
	}

	@Override
	public void visit(ProductContainer productContainer) {
		if (productContainer instanceof ProductGroup) {
			ProductGroup group = (ProductGroup) productContainer;
			ProductQuantity groupSupply = group.getThreeMonthSupply();
			Collection<Product> products = group.getProducts();
			Map<String, Product> inconsistencies = new TreeMap<String, Product>();
			for (Product product : products) {
				if (Unit.typeMap.get(product.getSize().getUnits()) != Unit.typeMap
						.get(groupSupply.getUnits())) {
					inconsistencies.put(product.getDescription(), product);
				}
			}

			if (inconsistencies.size() > 0) {
				builder.startList("Product Group " + group.getRoot().getName() + "::"
						+ group.getName() + " Has a 3-month supply (" + groupSupply
						+ ") that is inconsistent with the following products:");
				Collection<String> descriptions = inconsistencies.keySet();
				for (String d : descriptions) {
					Product p = inconsistencies.get(d);
					builder.addListItem(group.getName() + "::" + p.getDescription()
							+ "(size: " + p.getSize().toString() + ")");
				}

			}
		}

	}
}
