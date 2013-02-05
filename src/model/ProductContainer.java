package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

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
public abstract class ProductContainer implements Comparable<ProductContainer> {
	protected String name;
	private Unit unit;

	// Data Structures
	private Collection<Item> items;
	private Collection<Product> products;
	private Collection<ProductGroup> pGroups;
	//TODO: Implement this map for all descendant nodes
	private Map<Product, Set<Item>> productsToItems; 
	
	/** Constructor 
	 * 
	 * @param pcName - the name of the Product Container
	 * @param unit - Unit for summing in this Product Container.
	 *   
	 * @pre name != null
	 * @pre !name.equals("")
	 * @post true
	 * 
	 */
	public ProductContainer(String name,Unit unit) {
		this.name = name;
		this.unit = unit;
		
		createDataStructures();
		productsToItems = new TreeMap<Product, Set<Item>>();
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
	
	/** Attribute getter - unit
	 * 
	 * @return The Unit of the ProductContainer
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public Unit getUnit() {
		return unit;
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
		total.add(new ProductQuantity(getItems(p.getBarcode()).size() * pSize.getQuantity(),pSize.getUnits()));
		
		return total;
	}
	
	/** Method that calculates and returns the amount of a product group in this container.
	 * 
	 * @return ProductQuantity - the current supply of this container
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public ProductQuantity getCurrentSupply() {
		Iterator<Product> it = products.iterator();
		ProductQuantity total = new ProductQuantity(0,unit);
		while(it.hasNext()) {
			try {
				ProductQuantity pSupply = getCurrentSupply(it.next());
				total.add(pSupply);
			} catch(IllegalArgumentException e) {
				continue;
			}
		}
		
		return total;
	}
	
	/** Method that adds an Item to the collection.
	 * 
	 * @param i - the Item object to add to the collection
	 * @return True if object was added successfully, false otherwise.
	 * 
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
	
	/** Removes the specified item from this ProductContainer.
	 * 
	 * @param item		the Item to be removed
	 * @param manager 	the ItemManager to notify of the removal
	 */
	public void remove(Item item, ItemManager manager) {
		items.remove(item);
		manager.unmanage(item);
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
		items.remove(item);
		destination.add(item);
	}
	
	/** Determines whether this Product Container contains a specific Item
	 * 
	 * @param item		the Item to check
	 * @return			true if this Product Container contains the Item, false otherwise
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public boolean contains(Item item) {
		return items.contains(item);
	}

	/** Removes a specified ProductContainer from this container and its children.
	 * 
	 * @param container 		The ProductContainer to remove
	 * 
	 * @pre container != null
	 * @post !contains(container)
	 */
	public void remove(ProductContainer container) {
		assert (container != null);
		
		for (ProductGroup child : pGroups) {
			if (child.equals(container)) {
				pGroups.remove(child);
				return;
			}
		}
	}
	
	/** Method that removes a Product object from the collection.
	 * 
	 * @param barcode - the String barcode of the Product object to be removed from the collection
	 *
	 * @pre product != null
	 * @post !products.contains(product)
	 * @throws IllegalStateException	if the product cannot be removed
	 * 
	 */
	public void remove(Product product) throws IllegalStateException {
		if (!canRemove(product))
			throw new IllegalStateException("Cannot remove product; product container still has items that refer to it");
		products.remove(product);
	}

	public boolean canRemove(Product product) {
		return (productsToItems.get(product).isEmpty());
	}

	/** Method that removes a ProductGroup object from the collection.
	 * 
	 * @param pgToFind - the String name of the ProductGroup object to be removed from the collection
	 * @return True if object was removed successfully, false otherwise.
	 * 
	 * @pre pgTofind != null
	 * @pre !pgToFind.equals("")
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
	 * @post items.size() == 0
	 * 
	 */
	public void clearAllItems() {
		initiateItems();
	}
	
	/** Method that clears the Products data structure
	 * 
	 * @pre true
	 * @post products.size() == 0
	 */
	public void clearAllProducts() {
		initiateProducts();
	}

	/** Method that clears the ProductGroups data structure.
	 * 
	 * @pre true
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
	 * @pre true
	 * @post true
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
		
	/* Method that traverses the Item data structure for an item with
	 * the given barcode.
	 * 
	 * @pre items != null, no references to null objects
	 * @post none
	 */
	/*private Item traverseItems(String barcode) {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item current = it.next();
			if(current.getBarcode().equals(barcode))
				return current;
		}
		
		return null;
	}*/
	
	/* Method that traverses the Product data structure for a Product
	 * object with the given barcode.
	 * 
	 * @pre products != null, no references to null objects
	 * @post none
	 * 
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
	
	/* Method that traverses the ProductGroup data structure for
	 * a ProductGroup with a name equivalent to pgName.
	 * 
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
