package model.visitor;

import model.Item;
import model.Product;
import model.ProductContainer;

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
	 * Gathers reporting data about a ProductContainer.
	 * 
	 * @param productContainer
	 *            ProductContainer to visit
	 * 
	 * @pre productContainer != null
	 * @post true
	 */
	public void visit(ProductContainer productContainer);
}
