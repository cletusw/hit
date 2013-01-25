package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/** Product Container class
 * 
 * @author Matt Hess
 * @version 1.0 - Snell 340 Group 4 Phase 1
 *
 */
public abstract class ProductContainer {
	private String name;

	// Data Structures
	private Collection<Item> items;
	private Collection<Product> products;
	private Collection<ProductGroup> pGroups;
	
	/** Constructor 
	 * 
	 * @param pcName - the name of the Product Container
	 *  
	 */
	public ProductContainer(String pcName) {
		name = pcName;
		
		createDataStructures();
	}
	
	/** Default Constructor 
	 *
	 */
	public ProductContainer() {
		name = "(no name)";
		
		createDataStructures();
	}
	
	/** Attribute getter - name
	 * 
	 * @return The String name of the ProductContainer
	 */
	public String getName() {
		return name;
	}
	
	/** Gets the size of the items collection.
	 * 
	 * @return int - the number of elements in the items collection.
	 */
	public int getItemsSize() {
		return items.size();
	}
	
	/** Gets the size of the products collection.
	 * 
	 * @return int - the number of elements in the products collection.
	 */
	public int getProductsSize() {
		return products.size();
	}
	
	/** Gets the size of the pGroups collection
	 * 
	 * @return int - the number of elements in the pGroups collection.
	 */
	public int getPGroupsSize() {
		return pGroups.size();
	}
	
	/** Finds and returns the requested Item object(s)
	 * 
	 * @param iToFind - the string name of the Item(s) to find. 
	 * @return LinkedList<Item> - the requested Item
	 */
	public LinkedList<Item> getItem(String iToFind) {
		LinkedList<Item> found = new LinkedList<Item>();
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item current = it.next();
			if(it.equals(current))
				found.add(current);
		}
		
		return found;
	}
	
	/** Finds and returns the requested Product object
	 * 
	 * @param barcode - the barcode of the product to find
	 * @return Product - the requested Product
	 */
	public Product getProduct(String barcode) {
		Iterator<Product> it = products.iterator();
		while(it.hasNext()) {
			Product current = it.next();
			if(current.getBarcode().equals(barcode))
				return current;
		}
		
		return null;
	}
	
	/** Finds and returns the requested ProductGroup object
	 * 
	 * @param pgToFind - the name of the ProductGroup to find
	 * @return ProductGroup - the requested ProductGroup
	 */
	public ProductGroup getProductGroup(String pgToFind) {
		Iterator<ProductGroup> it = pGroups.iterator();
		while(it.hasNext()) {
			ProductGroup current = it.next();
			if(current.getName().equals(pgToFind))
				return current;
			
		}
		
		return null;
	}
	
	/** Method that calculates and returns the amount of a product in this container.
	 * 
	 * @param p - the Product to be found
	 * @return ProductQuantity - the current supply of the found product, or null.
	 */
	public ProductQuantity getCurrentSupply(Product p) {
		System.out.println("Not yet implemented");
		return null;
	}

	/** Method that calculates and returns the amount of a product group in this container.
	 * 
	 * @param pg - the ProductGroup to be found
	 * @return ProductQuantity - the current supply of the found product group, or null.
	 */
	public ProductQuantity getCurrentSupply(ProductGroup pg) {
		System.out.println("Not yet implemented");
		return null;
	}
	
	/** Method that adds an Item to the collection.
	 * 
	 * @param i - the Item object to add to the collection
	 * @return True if object was added successfully, false otherwise.
	 */
	public boolean add(Item i) {
		if(i == null)
			return false;
		
		return items.add(i);
	}

	/** Method that adds a Product to the collection.
	 * 
	 * @param p - the Product object to add to the collection
	 * @return True if object was added successfully, false otherwise.
	 */
	public boolean add(Product p) {	
		return products.add(p);
	}
	
	/** Method that adds a ProductGroup object to the collection.
	 * 
	 * @param pg - the ProductGroup object to add to the collection
	 * @return True if object was added successfully, false otherwise.
	 */
	public boolean add(ProductGroup pg) {
		return pGroups.add(pg);
	}

	/** Method that removes an Item object(s) from the collection.
	 * 
	 * @param iToFind - the String name of the Item object(s) to be removed from the collection
	 */
	public void removeItems(String iToFind) {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item current = it.next();
			if(current.getName().equals(iToFind)) {
				items.remove(current);
				it = items.iterator();
			}
		}
	}

	/** Method that removes a Product object from the collection.
	 * 
	 * @param barcode - the barcode of the Product object to be removed from the collection
	 * @return True if object was removed successfully, false otherwise.
	 */
	public boolean removeProduct(String barcode) {
		Iterator<Product> it = products.iterator();
		while(it.hasNext()) {
			Product current = it.next();
			if(current.getBarcode().equals(barcode)) {
				return products.remove(current);
			}
		}
		
		return false;
	}

	/** Method that removes a ProductGroup object from the collection.
	 * 
	 * @param pgToFind - the String name of the ProductGroup object to be removed from the collection
	 * @return True if object was removed successfully, false otherwise.
	 */
	public boolean removeProductGroup(String pgToFind) {
		Iterator<ProductGroup> it = pGroups.iterator();
		while(it.hasNext()) {
			ProductGroup current = it.next();
			if(current.getName().equals(pgToFind))
				return pGroups.remove(current);
		}

		return false;
	}
	
	/** Method that clears the Items data structure.
	 * 
	 */
	public void clearAllItems() {
		initiateItems();
	}
	
	/** Method that clears the Products data structure.
	 * 
	 */
	public void clearAllProducts() {
		initiateProducts();
	}

	/** Method that clears the ProductGroups data structure.
	 * 
	 */
	public void clearAllProductGroups() {
		initiateProductGroups();
	}
	
	/** Abstract method to be defined in each child; typical equals.
	 * 
	 * @param o - the object to be compared to.
	 * @returns True if the objects are equal, false otherwise. 
	 */
	public abstract boolean equals(Object o);
	
	/*
	 * Method that initializes the ProductContainer's data structures.
	 *  
	 */
	private void createDataStructures() {
		initiateItems();
		initiateProducts();
		initiateProductGroups();
	}
	
	/*
	 * Method that initializes the Items data structure.
	 */
	private void initiateItems() {
		items = new LinkedList<Item>();
	}
	
	/*
	 * Method that initializes the Products data structure.
	 */
	private void initiateProductGroups() {
		pGroups = new TreeSet<ProductGroup>();
	}
	
	/*
	 * Method that initializes the ProductGroups data structure.
	 */
	private void initiateProducts() {
		products = new TreeSet<Product>();
	}
	
	/*
	 * Method that returns the collection associated with the collection type
	 * 
	 */
	/*private Collection determineCollection(collectionType ct) {
		if(ct == collectionType.ITEMS)
			return items;
		else if(ct == collectionType.PRODUCTS)
			return products;
		else if(ct == collectionType.P_GROUPS)
			return pGroups;
		
		return null;
	}*/
	
	/*
	 * Method that determines which type of object should be returned. 
	 */
	/*private Object determineObjectType(collectionType ct,Object o) {
		if(ct == collectionType.ITEMS)
			return (Item) o;
		else if(ct == collectionType.PRODUCTS)
			return (Product) o;
		else if(ct == collectionType.P_GROUPS)
			return (ProductGroup) o;
		
		return null;
	}*/
	
	/*private Object traverseCollection(collectionType ct,Object needle) {
		Collection c = determineCollection(ct);
		if(c == null)
			return null;
		
		Iterator it = c.iterator();
		while(it.hasNext()) {
			if(it.equals(needle))
				return needle;
			
			it.next();
		}
		
		return null;
	}*/
	
	
}
