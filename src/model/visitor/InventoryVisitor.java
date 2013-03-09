package model.visitor;

import model.Item;
import model.Product;
import model.ProductGroup;
import model.StorageUnit;

public interface InventoryVisitor {

	/**
	 * Gathers reporting data about an Item.
	 * 
	 * @param item
	 *            Item to visit
	 * 
	 * @pre item != null
	 * @post true
	 */
	public void visit(Item item);

	/**
	 * Gathers reporting data about a Product.
	 * 
	 * @param product
	 *            Product to visit
	 * 
	 * @pre product != null
	 * @post true
	 */
	public void visit(Product product);

	/**
	 * Gathers reporting data about a ProductGroup.
	 * 
	 * @param productGroup
	 *            ProductGroup to visit
	 * 
	 * @pre productGroup != null
	 * @post true
	 */
	public void visit(ProductGroup productGroup);

	/**
	 * Gathers reporting data about a StorageUnit.
	 * 
	 * @param storageUnit
	 *            StorageUnit to visit
	 * 
	 * @pre StorageUnit != null
	 * @post true
	 */
	public void visit(StorageUnit storageUnit);
}
