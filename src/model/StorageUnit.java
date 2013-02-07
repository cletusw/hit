package model;

/**
 * Storage Unit class. Inherits from Product Container.
 * 
 * @author - Matt Hess
 * @version 1.0 - Snell 340 Group 4 Phase 1
 * 
 */

@SuppressWarnings("serial")
public class StorageUnit extends ProductContainer {

	/**
	 * Constructor
	 * 
	 * @param pcName
	 *            - String name of the storage unit.
	 * @param unit
	 *            - Unit for counting in this storage unit.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public StorageUnit(String pcName) {
		super(pcName);
	}

	/**
	 * Method that adds an Item to the collection.
	 * 
	 * @param i
	 *            - the Item object to add to the collection
	 * @return true if the item was added to this container or one of its
	 *         children, false otherwise.
	 * @pre i != null
	 * @post items.size() == items.size()@pre + 1
	 * @post items.contains(i)
	 * 
	 */
	public boolean add(Item i) {
		assert (i != null);
		if (items.containsKey(i.getBarcode()))
			throw new IllegalStateException(
					"Cannot have two items with same barcode");

		// A new item is added to the same Product Container that contains the
		// Item's Product
		// within the target Storage Unit
		if (!contains(i.getProduct())) {
			for (ProductGroup productGroup : productGroups.values()) {
				if (productGroup.contains(i.getProduct())) {
					productGroup.registerItem(i);
					return true;
				}
			}
		} else {
			// This container contains the Item's Product; add it here.
			registerItem(i);
			return true;
		}
		// Product not found anywhere else; add Item here
		add(i.getProduct());
		registerItem(i);
		return true;
	}

	/**
	 * Determines whether or not the specified Product Group can be added to
	 * this Storage Unit.
	 * 
	 * @param pGroup
	 *            the Product Group to test
	 * @return true if able to add, false otherwise
	 * 
	 * @pre pGroup != null
	 * @post true
	 */
	@Override
	public boolean canAddProductGroup(ProductGroup pGroup) {
		// From the Data Dictionary: A Storage Unit cannot have two top-level
		// Product Groups of the same name.
		assert (pGroup != null);

		return (getProductGroup(pGroup.getName()) == null);
	}

}
