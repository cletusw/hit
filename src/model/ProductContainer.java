package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

/** Product Container class: Represents an object that can hold various 
 *    types of items, products, and product groups. 
 * 
 * @author Matt Hess
 * @version 1.0 - Snell 340 Group 4 Phase 1
 *
 * @invariant name != null
 * @invariant !name.equals("")
 * @invariant items != null
 * @invariant products != null
 * @invariant productGroups != null
 */
@SuppressWarnings("serial")
public abstract class ProductContainer implements Comparable<ProductContainer>, Serializable {
	protected NonNullString name;

	// Data Structures
	private Map<String, Item> items;
	private Map<String, Product> products;
	private Map<String, ProductGroup> productGroups;
	//TODO: Implement this map for all descendant nodes
	private Map<Product, Set<Item>> productsToItems; 
	
	/** Constructor 
	 * 
	 * @param name - the name of the Product Container
	 *   
	 * @pre name != null
	 * @pre !name.equals("")
	 * @post true
	 * 
	 */
	public ProductContainer(String name) {
		assert(name != null);

		this.name = new NonNullString(name);
		items = new TreeMap<String, Item>();
		productGroups = new TreeMap<String, ProductGroup>();
		products = new TreeMap<String, Product>();
		productsToItems = new TreeMap<Product, Set<Item>>();
	}
	
	/**
	 * Determines if this ProductContainer can be removed from the system.
	 * 
	 * @return true if this ProductContainer can be removed, false otherwise.
	 */
	public boolean canRemove() {
		if (!items.isEmpty()) {
			return false;
		}
		
		for (Product product : products.values()) {
			if (!product.canRemove()) {
				return false;
			}
		}
		
		for (ProductGroup productGroup : productGroups.values()) {
			if (!productGroup.canRemove()) {
				return false;
			}
		}
		
		return true;
	}
	
	/** Attribute getter - name
	 * 
	 * @return The String name of the ProductContainer
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public String getName() {
		return name.getValue();
	}
	
	/** Sets this Container's name
	 * 
	 * @param name Name to set to
	 * 
	 * @pre isValidName(name)
	 * @post getName() == name
	 */
	public void setName(String name) {
		assert(isValidName(name));
		
		this.name = name;
	}
	
	/** Gets the size of the items collection.
	 * 
	 * @return int - the number of elements in the items collection.
	 * 
	 * @pre true
	 * @post true 
	 * 
	 */
	public int getItemsSize() {
		return items.size();
	}
	
	/** Gets the size of the products collection.
	 * 
	 * @return int - the number of elements in the products collection.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public int getProductsSize() {
		return products.size();
	}
	
	/** Gets the size of the productGroups collection
	 * 
	 * @return int - the number of elements in the pGroups collection.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public int getProductGroupsSize() {
		return productGroups.size();
	}
	
	/** Gets an iterator over this Container's Products
	 * 
	 * @return Iterator<Product> this Container's Products iterator
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public Iterator<Product> getProductsIterator() {
		return products.values().iterator();
	}
	
	/** Finds and returns the requested Product object
	 * 
	 * @param barcode - the String barcode of the product to find
	 * @return Product - the requested Product
	 * 
	 * @pre barcode != null
	 * @post true  
	 * 
	 */
	public Product getProduct(String barcode) {
		assert (barcode != null);
		
		return products.get(barcode);
	}
	
	/** Finds and returns the requested ProductGroup object
	 * 
	 * @param pgToFind - the name of the ProductGroup to find
	 * @return ProductGroup - the requested ProductGroup
	 * 
	 * @pre pgToFind != null
	 * @post true
	 */
	public ProductGroup getProductGroup(String pgToFind) {
		assert(pgToFind != null);
		
		return productGroups.get(pgToFind);
	}


	/** Method that calculates and returns the amount of a product in this container.
	 * 
	 * @param p - the Product to be found
	 * @return ProductQuantity - the current supply of the found product, or null.
	 * 
	 * @pre p != null
	 * @pre p.getBarcode() != null
	 * @pre p.getSize() != null
	 * @post true
	 * 
	 */
	public ProductQuantity getCurrentSupply(Product p) {
		assert(p != null);
		assert(p.getBarcode() != null);
		assert(p.getSize() != null);
		
		ProductQuantity pSize = p.getSize();
		
		// Get sum of all pGroup product quantities
		Iterator<ProductGroup> it = productGroups.values().iterator();
		ProductQuantity total = new ProductQuantity(0,p.getSize().getUnits());
		while(it.hasNext()) {
			ProductGroup current = it.next();
			total.add(current.getCurrentSupply(p));
		}
		
		// add product quantity of items in this container
		if (productsToItems.containsKey(p)) {
			for (Item item : productsToItems.get(p)) {
				total.add(new ProductQuantity(pSize.getQuantity(),pSize.getUnits()));			
			}
		}
		
		return total;
	}
	
	/** Method that adds an Item to the collection.
	 * 
	 * @param i - the Item object to add to the collection
	 * 
	 * @pre i != null
	 * @post items.size() == items.size()@pre + 1
	 * @post items.contains(i)
	 * 
	 */
	public void add(Item i) {
		assert (i != null);
		if (items.containsKey(i.getBarcode()))
			throw new IllegalStateException("Cannot have two items with same barcode");
		items.put(i.getBarcode(),i);
		Set<Item> newItemsForProduct;
		if (productsToItems.containsKey(i.getProduct())) {
			newItemsForProduct = productsToItems.get(i.getProduct());
		}
		else {
			newItemsForProduct = new TreeSet<Item>();
		}
		newItemsForProduct.add(i);
		productsToItems.put(i.getProduct(), newItemsForProduct);
	}

	/** Method that adds a Product to the collection.
	 * 
	 * @param p - the Product object to add to the collection
	 * 
	 * @pre p != null
	 * @post products.size() == products.size()@pre + 1
	 * @post products.contains(p)
	 * 
	 */
	public void add(Product p) {
		assert (p != null);
		
		if (products.containsKey(p.getBarcode()))
			throw new IllegalStateException(
					"Cannot add two products of the same name into a single parent container");
		products.put(p.getBarcode(), p);
		productsToItems.put(p, new TreeSet<Item>());
	}
	
	/** Method that adds a ProductGroup object to the collection.
	 * 
	 * @param productGroup - the ProductGroup object to add to the collection
	 * 
	 * @pre productGroup != null
	 * @post pGroups.size() == pGroups.size()@pre + 1
	 * @post pGroups.contains(productGroup)
	 * 
	 */
	public void add(ProductGroup productGroup) {
		assert(productGroup != null);
		
		if (!canAddProductGroup(productGroup.getName()))
			throw new IllegalStateException(
				"Cannot add two product groups of the same name into a single parent container");
		productGroups.put(productGroup.getName(), productGroup);
	}
	
	/** Determines whether the specified ProductGroup can be added to this ProductContainer.
	 * 
	 * @param productGroupName - the ProductGroup name to test
	 * @return true if the ProductGroup can safely be added, false otherwise.
	 * 
	 * @pre productGroupName != null
	 * @post true
	 */
	public boolean canAddProductGroup(String productGroupName) {
		assert(productGroupName != null);
		// From the Data Dictionary: Must�be�non-empty�and�unique�within�
		//   the�parent�Product�Container.�
		return !productGroupName.equals("") && !containsProductGroup(productGroupName);
	}
	
	/** Determines whether the specified ProductGroup can be added to this ProductContainer.
	 * 
	 * @param productGroup - the ProductGroup to test
	 * @return true if the ProductGroup can safely be added, false otherwise.
	 * 
	 * @pre productGroup != null
	 * @post productGroups.size()@pre == productGroups.size()
	 */
	public boolean canAddProductGroup(ProductGroup productGroup) {
		assert(productGroup != null);
		return canAddProductGroup(productGroup.getName());
	}
	
	/** Determines whether the specified ProductGroup is contained in this ProductContainer.
	 * 
	 * @param productGroupName - the ProductGroup to test
	 * @return true if the ProductGroup exists in this ProductContainer, false otherwise.
	 * 
	 * @pre productGroupName != null 
	 * @post true
	 */
	public boolean containsProductGroup(String productGroupName) {
		assert(productGroupName != null);
		return productGroups.containsKey(productGroupName);
	}
	
	/** Determines whether the specified ProductGroup is contained in this ProductContainer.
	 * 
	 * @param productGroupName - the ProductGroup to test
	 * @return true if the ProductGroup exists in this ProductContainer, false otherwise.
	 * 
	 * @pre productGroup != null 
	 * @post true
	 */
	public boolean contains(ProductGroup productGroup) {
		assert(productGroup != null);
		return productGroups.containsKey(productGroup.getName());
	}
	
	/** Removes the specified item from this ProductContainer.
	 * 
	 * @param item		the Item to be removed
	 * @param manager 	the ItemManager to notify of the removal. If null, no manager is notified.
	 * 
	 * @return the removed item
	 * 
	 * @pre item != null
	 * @pre manager != null
	 * @pre productsToItems.containsKey(item.getProduct())
	 * @post !containsItem(item.getBarcode())
	 */
	public Item remove(Item item, ItemManager manager) {
		assert(item != null);
		assert(manager != null);
		assert(productsToItems.containsKey(item.getProduct()));
		
		manager.unmanage(item);
		Set<Item> newItemsForProduct = productsToItems.get(item.getProduct());
		newItemsForProduct.remove(item);
		productsToItems.put(item.getProduct(), newItemsForProduct);
		return items.remove(item.getBarcode());
	}
	
	/** Removes the specified item from this ProductContainer.
	 *  Use this only 
	 * 
	 * @param item			the Item to be moved
	 * @param destination 	the ProductContainer to move the item to
	 * 
	 * @pre item != null
	 * @pre destination != null
	 * @pre items.contains(item)
	 * @post !items.contains(item)
	 * @post destination.contains(item)
	 * 
	 */
	public void moveIntoContainer(Item item, ProductContainer destination) {
		assert(item != null);
		assert(destination != null);
		assert(items.containsKey(item.getBarcode()));
		
		if (destination.contains(item)) {
			throw new IllegalStateException(
					"Destination container already contains the item to be moved");
		}
		if (!contains(item)) {
			throw new IllegalStateException("Item does not exist in this container; cannot move");
		}
		
		items.remove(item.getBarcode());
		destination.add(item);
	}
	
	/** Determines whether this Product Container contains a specific Item
	 * 
	 * @param barcode		the barcode of the Item to check
	 * @return				true if this Product Container contains the Item, false otherwise
	 * 
	 * @pre barcode != null
	 * @post true
	 * 
	 */
	public boolean containsItem(String barcode) {
		assert (barcode != null);
		return items.containsKey(barcode);
	}
	
	/** Determines whether this Product Container contains a specific Item
	 * 
	 * @param item			the Item to check
	 * @return				true if this Product Container contains the Item, false otherwise
	 * 
	 * @pre item != null
	 * @post true
	 */
	public boolean contains(Item item) {
		assert(item != null);
		return items.containsKey(item.getBarcode());
	}

	/** Determines whether this Product Container contains a specific Product
	 * 
	 * @param productBarcode	the Product's barcode to check
	 * @return					true if this Product Container contains the Product, false otherwise
	 * 
	 * @pre productBarcode != null
	 * @post true
	 * 
	 */
	public boolean containsProduct(String productBarcode) {
		assert(productBarcode != null);
		return products.containsKey(productBarcode);
	}
	
	/** Determines whether this Product Container contains a specific Product
	 * 
	 * @param product			the Product to check
	 * @return					true if this Product Container contains the Product, false otherwise
	 * 
	 * @pre product != null
	 * @post true
	 * 
	 */
	public boolean contains(Product product) {
		assert(product != null);
		return products.containsKey(product.getBarcode());
	}
	
	/** Removes a specified ProductGroup from this container.
	 * 
	 * @param productGroup 		The ProductGroup to remove
	 * 
	 * @pre productGroup != null
	 * @post !contains(productGroup)
	 * @throws IllegalStateException	if the ProductGroup cannot be removed
	 */
	public void remove(ProductGroup productGroup) {
		assert (productGroup != null);
		
		if (!productGroup.canRemove()) {
			throw new IllegalStateException("Cannot remove child product group");
		}
		productGroups.remove(productGroup.getName());
	}
	
	/** Method that removes a Product object from the collection.
	 * 
	 * @param barcode - the String barcode of the Product object to be removed from the collection
	 * @return the removed Product
	 * @pre product != null
	 * @post !products.contains(product)
	 * @throws IllegalStateException	if the product cannot be removed
	 * 
	 */
	public Product remove(Product product) throws IllegalStateException {
		assert(product != null);
		if (!canRemove(product))
			throw new IllegalStateException(
					"Cannot remove product; product container still has items that refer to it");
		productsToItems.remove(product);
		return products.remove(product.getBarcode());
	}

	/** Determines whether we can remove a given Product from this ProductContainer.
	 * 
	 * @param product - the Product object to be removed from the container
	 *
	 * @pre product != null
	 * @post true
	 */
	public boolean canRemove(Product product) {
		assert(product != null);
		if (productsToItems.get(product) == null)
			return true;
		return (productsToItems.get(product).isEmpty());
	}
	
	/** Defines equality with another ProductContainer descendant.
	 * 
	 * @param o - the object to be compared to.
	 * @returns True if the objects are equal, false otherwise.
	 * 
	 * @pre o != null
	 * @post true
	 */
	public boolean equals(Object o) {
		assert (o != null);
		
		String otherName;
		
		if (o instanceof ProductContainer) {
			otherName = ((ProductContainer) o).getName();
		}
		else {
			otherName = o.toString();
		}
		
		return getName().equals(otherName);
	}
	
	/** Compares this ProductContainer to another
	 * 
	 * @param su
	 * @return		0 if equal, some other integer representing the comparison otherwise
	 * @pre pc != null
	 * @post true
	 */
	@Override
	public int compareTo(ProductContainer pc) {
		assert(pc != null);
		return name.compareTo(pc.name);
	}
}
