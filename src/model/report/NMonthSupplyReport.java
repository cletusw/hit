package model.report;

import model.Item;
import model.Product;
import model.ProductGroup;
import model.StorageUnit;
import model.visitor.InventoryVisitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class NMonthSupplyReport extends Report implements InventoryVisitor {

	/**
	 * Generate a new NMonthSupplyReport where the reporting period is the number of months
	 * specified
	 * 
	 * @param format
	 *            ReportFormat to create.
	 * @param months
	 *            Reporting Period
	 * @pre true
	 * @post true
	 */
	public void run(ReportFormat format, int months) {
		throw new NotImplementedException();
	}

	@Override
	public void visit(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ProductGroup productGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StorageUnit storageUnit) {
		// TODO Auto-generated method stub

	}

}
