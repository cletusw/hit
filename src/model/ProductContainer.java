package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/** Product Container class: Represents an object that can hold various types of items, products, and product groups. 
 * 
 * @author Matt Hess
 * @version 1.0 - Snell 340 Group 4 Phase 1
 *
 * @invariant name != null
 * @invariant !name.equals("")
 * @invariant items != null
 * @invariant products != null
 * @invariant pGroups != null
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
	 * @pre true
	 * @post name != null
	 * @post !name.equals("")
	 * @post items != null
	 * @post products != null
	 * @post pGroups != null 
	 * 
	 */
	public ProductContainer(String pcName) {
		name = pcName;
		
		createDataStructures();
	}
	
	/** Default Constructor 
	 *
	 * @pre true
	 * @post name != null
	 * @post !name.equals("")
	 * @post items != null
	 * @post products != null
	 * @post pGroups != null
	 * 
	 */
	public ProductContainer() {
		name = "(no name)";
		
		createDataStructures();
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
		return name;
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
	
	/** Gets the size of the pGroups collection
	 * 
	 * @return int - the number of elements in the pGroups collection.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public int getPGroupsSize() {
		return pGroups.size();
	}
	
	/** Finds and returns the requested Item object(s)
	 * 
	 * @param itemBarcode - the (String) Product barcode of the Item(s) to find. 
	 * @return Collection<Item> - the requested Items
	 * 
	 * @pre itemBarcode != null
	 * @post return (Collection<Item> != null)
	 * 
	 */
	public Collection<Item> getItems(String itemBarcode) {
		LinkedList<Item> found = new LinkedList<Item>();
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item current = it.next();
			if(current.getProductBarcode().equals(itemBarcode))
				found.add(current);
		}
		
		return found;
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
		return traverseProducts(barcode);
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
		return traverseProductGroups(pgToFind);
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
		ProductQuantity pSize = p.getSize();
		
		// Get sum of all pGroup product quantities
		Iterator<ProductGroup> it = pGroups.iterator();
		ProductQuantity total = new ProductQuantity(0,p.getSize().getUnits());
		while(it.hasNext()) {
			ProductGroup current = it.next();
			total.add(current.getCurrentSupply(p));
		}
		
		// add product quantity of items in this container
		total.add(new ProductQuantity(getItems(p.getBarcode()).size(),pSize.getUnits()));
		
		return total;
	}

	/** Method that calculates and returns the amount of a product group in this container.
	 * 
	 * @param pg - the ProductGroup to be found
	 * @return ProductQuantity - the current supply of the found product group, or null.
	 * 
	 * @pre pg != null
	 * @post
	 * 
	 */
	public ProductQuantity getCurrentSupply(ProductGroup pg) {
		System.out.println("Not yet implemented");
		return null;
	}
	
	/** Method that adds an Item to the collection.
	 * 
	 * @param i - the Item object to add to the collection
	 * @return True if object was added successfully, false otherwise.
	 * 
	 * @pre items != null
	 * @pre i != null
	 * @pre !items.contains(i)
	 * @post items.size() == items.size()@pre + 1
	 * @post items.contains(i)
	 * 
	 */
	public boolean add(Item i) {
		return items.add(i);
	}

	/** Method that adds a Product to the collection.
	 * 
	 * @param p - the Product object to add to the collection
	 * @return True if object was added successfully, false otherwise.
	 * 
	 * @pre products != null
	 * @pre p != null
	 * @pre !products.contains(p)
	 * @post products.size() == products.size()@pre + 1
	 * @post products.contains(p)
	 * 
	 */
	public boolean add(Product p) {	
		return products.add(p);
	}
	
	/** Method that adds a ProductGroup object to the collection.
	 * 
	 * @param pg - the ProductGroup object to add to the collection
	 * @return True if object was added successfully, false otherwise.
	 * 
	 * @pre pGroups != null
	 * @pre pg != null
	 * @pre !pGroups.contains(pg)
	 * @post pGroups.size() == pGroups.size()@pre + 1
	 * @post pGroups.contains(pg)
	 * 
	 */
	public boolean add(ProductGroup pg) {
		return pGroups.add(pg);
	}
	
	/** Determines whether the specified ProductGroup can be added to this ProductContainer.
	 * 
	 * @param pg - the ProductGroup to test
	 * @return true if the ProductGroup can safely be added, false otherwise.
	 * 
	 * @pre pGroups != null
	 * @pre pg != null
	 * @post pGroups.size()@pre == pGroups.size()
	 * 
	 */
	public boolean canAddProductGroup(ProductGroup pg) {
		// From the Data Dictionary:
		// Must be non-empty and unique within 
		// the parent Product Container. 

		return (traverseProductGroups(pg.getName()) == null);
	}

	/** Method that removes an Item object(s) from the collection.
	 * 
	 * @param barcode - the String barcode of the Item object(s) to be removed from the collection
	 *
	 * @pre items != null
	 * @pre barcode != null
	 * @pre items.size() >= 0
	 * @pre traverseItems(barcode).size() > 0
	 * @post items.size()@pre > items.size() >= 0
	 * 
	 */
	public void removeItems(String barcode) {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item current = it.next();
			if(current.getBarcode().equals(barcode)) {
				items.remove(current);
				it = items.iterator();
			}
		}
	}

	/** Method that removes a Product object from the collection.
	 * 
	 * @param barcode - the String barcode of the Product object to be removed from the collection
	 * @return True if object was removed successfully, false otherwise.
	 *
	 * @pre products != null
	 * @pre barcode != null
	 * @pre products.size() >= 0
	 * @pre traverseProducts(barcode) != null
	 * @post traverseProducts(barcode) == null
	 * @post products.size() == products.size()@pre - 1
	 * 
	 */
	public boolean removeProduct(String barcode) {
		return products.remove(traverseProducts(barcode));
	}

	/** Method that removes a ProductGroup object from the collection.
	 * 
	 * @param pgToFind - the String name of the ProductGroup object to be removed from the collection
	 * @return True if object was removed successfully, false otherwise.
	 * 
	 * @pre products != null
	 * @pre pgTofind != null
	 * @pre !pgToFind.equals("")
	 * @pre pGroups.size() >= 0
	 * @pre traverseProductGroups(pgToFind) != null
	 * @pre traverseProductGroups(pgToFind).containerEmpty()
	 * @post traverseProducts(pgToFind) == null
	 * @post products.size() == products.size()@pre - 1
	 * 
	 */
	public boolean removeProductGroup(String pgToFind) {
		return pGroups.remove(traverseProducts(pgToFind));
	}
	
	/** Method that clears the Items data structure.
	 * 
	 * @pre true
	 * @post items != null
	 * @post items.size() == 0
	 */
	public void clearAllItems() {
		initiateItems();
	}
	
	/** Method that clears the Products data structure
	 * 
	 * @pre true
	 * @post products != null
	 * @post products.size() == 0
	 */
	public void clearAllProducts() {
		initiateProducts();
	}

	/** Method that clears the ProductGroups data structure.
	 * 
	 * @pre true
	 * @post pGroups != null
	 * @post pGroups.size() == 0
	 */
	public void clearAllProductGroups() {
		initiateProductGroups();
	}
	
	/**
	 * Determines whether the specified string is a valid name for the ProductGroup
	 * 
	 * @param name the string to test
	 * @return true if the string is valid, false otherwise.
	 * 
	 * @pre true
	 * @post return (name != null && !name.equals(""))
	 */
	public static boolean isValidName(String name) {
		// From the Data Dictionary: Must be non-empty
		return ((name != null) && !name.equals(""));
	}
	
	/** Defines equality with another ProductContainer descendant.
	 * 
	 * @param o - the object to be compared to.
	 * @returns True if the objects are equal, false otherwise.
	 *  
	 * @pre (class invariants)
	 * @post (class invariants--no change)
	 * 
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
	 * @pre items != null, no references to null objects
	 * @post none
	 */
	private Item traverseItems(String barcode) {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item current = it.next();
			if(current.getBarcode().equals(barcode))
				return current;
		}
		
		return null;
	}
	
	/*
	 * @pre products != null, no references to null objects
	 * @post none
	 */
	private Product traverseProducts(String barcode) {
		Iterator<Product> it = products.iterator();
		while(it.hasNext()) {
			Product current = it.next();
			if(current.getBarcode().equals(barcode))
				return current;
		}
		
		return null;
	}
	
	/*
	 * @pre pGroups != null, no references to null objects
	 * @post none
	 */
	private ProductGroup traverseProductGroups(String pgName) {
		Iterator<ProductGroup> it = pGroups.iterator();
		while(it.hasNext()) {
			ProductGroup current = it.next();
			if(current.getName().equals(pgName))
				return current;
		}
		
		return null;
	}
}
