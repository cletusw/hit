package model.report;

import model.Item;
import model.Product;
import model.ProductGroup;
import model.StorageUnit;
import model.visitor.InventoryVisitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class NoticesReport extends Report implements InventoryVisitor {

	/**
	 * Generate a new NoticesReport
	 * 
	 * @param format
	 *            ReportFormat to create.
	 * @pre true
	 * @post true
	 */
	public void run(ReportFormat format) {
		throw new NotImplementedException();
	}

	@Override
	public void visit(Item item) {
		throw new NotImplementedException();
	}

	@Override
	public void visit(Product product) {
		throw new NotImplementedException();
	}

	@Override
	public void visit(ProductGroup productGroup) {
		throw new NotImplementedException();
	}

	@Override
	public void visit(StorageUnit storageUnit) {
		throw new NotImplementedException();
	}
}
