package model.report;

import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductManager;
import model.report.builder.ReportBuilder;
import model.visitor.InventoryVisitor;

@SuppressWarnings("serial")
public class NMonthSupplyReport extends Report implements InventoryVisitor {
	/**
	 * Set up an empty NMonthSupplyReport.
	 * 
	 * @param productManager
	 *            Manager to use for finding Product n-month supplies
	 * @param productContainerManager
	 *            Manager to use for finding ProductGroup n-month supplies
	 * 
	 * @pre true
	 * @post true
	 */
	public NMonthSupplyReport(ProductManager productManager,
			ProductContainerManager productContainerManager) {
	}

	/**
	 * Construct a completed NMonthSupplyReport, where months is the desired number of months
	 * of supply to have on hand.
	 * 
	 * @param builder
	 *            ReportBuilder to use
	 * @param months
	 *            Desired number of months of supply to have on hand
	 * 
	 * @pre true
	 * @post (new Date()).getTime() - getLastRunTime().getTime() < 1000
	 */
	public void construct(ReportBuilder builder, int months) {
		updateLastRunTime();
	}

	@Override
	public void visit(Item item) {
	}

	@Override
	public void visit(Product product) {
	}

	@Override
	public void visit(ProductContainer productContainer) {
	}
}
