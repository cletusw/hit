package model.report;

import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductContainerManager;
import model.report.builder.ReportBuilder;
import model.visitor.InventoryVisitor;

@SuppressWarnings("serial")
public class NoticesReport extends Report implements InventoryVisitor {
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
