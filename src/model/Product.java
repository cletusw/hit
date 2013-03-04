package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * A representation of a product within the Home Inventory System
 * 
 * @invariant barcode != null
 * @invariant description != null
 * @invariant creationDate != null
 * @invariant shelfLife >= 0
 * @invariant threeMonthSupply >= 0
 * @invariant size != null
 * @invariant productContainer != null
 */
@SuppressWarnings("serial")
public class Product implements Comparable<Object>, Serializable {
	/**
	 * Determines whether the given string is a valid barcode.
	 * 
	 * @param barcode
	 *            the string to test
	 * @return true if the barcode is valid, false otherwise.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public static boolean isValidBarcode(String barcode) {
		try {
			new NonEmptyString(barcode);
			return true;
		} catch (IllegalArgumentException ex) {
		}
		return false;
	}

	/**
	 * Determines whether the given string is a valid description
	 * 
	 * @param desc
	 *            the string to test
	 * @return true if the string is valid, false otherwise.
	 * 
	 * @pre true
	 * @post true
	 */
	public static boolean isValidDescription(String desc) {
		try {
			new NonEmptyString(desc);
			return true;
		} catch (IllegalArgumentException ex) {
		}
		return false;
	}

	/**
	 * Determines whether the specified product quantity is valid
	 * 
	 * @param pq
	 *            the ProductQuantity to check
	 * 
	 * @pre pq != null
	 * @post Returns true if pq is a valid Product Quantity for Products
	 */
	public static boolean isValidProductQuantity(ProductQuantity pq) {
		if (pq.getQuantity() <= 0)
			return false;
		if (pq.getUnits().equals(Unit.COUNT) && pq.getQuantity() != 1)
			return false;
		return true;
	}

	/**
	 * Determines whether the given integer is a valid shelf life.
	 * 
	 * @param sl
	 *            the integer to test
	 * @return true if the shelf life is valid, false otherwise.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public static boolean isValidShelfLife(int sl) {
		// From the Data Dictionary: Must be non-negative
		return sl >= 0;
	}

	/**
	 * Determines whether given integer is a valid three-month supply.
	 * 
	 * @param tms
	 *            the integer to check
	 * @return true if the integer is a valid three-month supply, false otherwise.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public static boolean isValidThreeMonthSupply(int tms) {
		// From the Data Dictionary: Must be non-negative
		return tms >= 0;
	}

	// member variables
	private NonEmptyString barcode;
	private NonEmptyString description;
	private Date creationDate;
	private int shelfLife;
	private int threeMonthSupply;
	private final Set<ProductContainer> productContainers;
	private final Set<Item> items;

	private ProductQuantity productQuantity;

	/**
	 * Constructs a Product using the given barcode, description, shelfLife, creationDate,
	 * manager describing the productManager that will manage this product.
	 * 
	 * @param barcode
	 *            NotNullString to identify this product
	 * @param description
	 *            string description of the product
	 * @param creationDate
	 *            Date this item was created, must not be in the future
	 * @param shelfLife
	 *            integer describing how long in months before the product expires
	 * @param tms
	 *            ThreeMonthSupply describing how many of this product are needed for three
	 *            months
	 * @param manager
	 *            the ProductManager that will manage this product
	 * 
	 * @pre Barcode.isValidBarcode(barcode)
	 * @pre description != null
	 * @pre description.length() > 0
	 * @pre creationDate != null
	 * @pre creationDate.before(now)
	 * @pre shelfLife >= 0
	 * @pre tms >= 0
	 * 
	 * @post this.barcode = barcode
	 * @post this.description.value = description
	 * @post this.creationDate = creationDate
	 * @post this.shelfLife = shelfLife
	 * @post this.threemonthSupply = tms
	 * @post this.size = pq
	 */
	public Product(String barcode, String description, Date creationDate, int shelfLife,
			int tms, ProductQuantity pq, ProductManager manager) {
		setBarcode(barcode);
		this.description = new NonEmptyString(description);
		setCreationDate(creationDate);
		setShelfLife(shelfLife);
		setThreeMonthSupply(tms);
		productContainers = new TreeSet<ProductContainer>();
		items = new TreeSet<Item>();
		setProductQuantity(pq);
		manager.manage(this);
	}

	/**
	 * Constructs a Product using the given barcode, description, shelfLife, manager describing
	 * the productManager that will manage this product. The creationDate is set to now.
	 * 
	 * @param barcode
	 *            NotNullString to identify this product
	 * @param description
	 *            string description of the product
	 * @param shelfLife
	 *            integer describing how long in months before the product expires
	 * @param tms
	 *            ThreeMonthSupply describing how many of this product are needed for three
	 *            months
	 * @param manager
	 *            the ProductManager that will manage this product
	 * 
	 * @pre Barcode.isValidBarcode(barcode)
	 * @pre description != null
	 * @pre description.length() > 0
	 * @pre shelfLife >= 0
	 * @pre tms >= 0
	 * 
	 * @post this.barcode = barcode
	 * @post this.description.value = description
	 * @post this.creationDate != null
	 * @post this.creationDate == now (time of instantiation)
	 * @post this.shelfLife = shelfLife
	 * @post this.threemonthSupply = tms
	 * @post this.size = pq
	 */
	public Product(String barcode, String description, int shelfLife, int tms,
			ProductQuantity pq, ProductManager manager) {
		this(barcode, description, new Date(), shelfLife, tms, pq, manager);
	}

	/**
	 * Add a ProductContainer to this Product's set of containers.
	 * 
	 * @param pc
	 *            ProductContainer to add.
	 * 
	 * @pre pc != null
	 * @post productContainers.contains(pc)
	 * 
	 */
	public void addContainer(ProductContainer pc) {
		if (pc == null) {
			throw new NullPointerException("Null ProductContainer pc");
		}
		if (!pc.canAddProduct(getBarcode()))
			throw new IllegalArgumentException(
					"Specified product container cannot contain this product");
		productContainers.add(pc);
	}

	/**
	 * Add an Item to this Product's set of items.
	 * 
	 * @param item
	 *            Item to add.
	 * 
	 * @pre item != null
	 * @post items.contains(item)
	 * 
	 */
	public void addItem(Item item) {
		if (item == null) {
			throw new NullPointerException("Null Item item");
		}

		items.add(item);
	}

	/**
	 * Determines if this Product can be removed from the system.
	 * 
	 * @return true, if this Product contains no items. false, otherwise.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public boolean canRemove() {
		return items.isEmpty();
	}

	/**
	 * Compares this Product to another Object.
	 * 
	 * @param o
	 *            the other object to be compared to
	 * 
	 * @pre o != null
	 * @pre o instanceof Product || o instanceof NonEmptyString
	 * @post true
	 * 
	 */
	@Override
	public int compareTo(Object o) {
		if (o == null) {
			throw new NullPointerException("Null Object o");
		}
		if (!(o instanceof Product) && !(o instanceof NonEmptyString)) {
			throw new ClassCastException("Invalid class for Object o");
		}

		NonEmptyString otherBarcode;

		if (o instanceof Product) {
			Product p = (Product) o;
			otherBarcode = ((Product) o).barcode;
			int compare = description.compareTo(p.description);
			if (compare != 0)
				return compare;
		} else {
			otherBarcode = (NonEmptyString) o;
		}

		return barcode.toString().compareToIgnoreCase(otherBarcode.toString());
	}

	/**
	 * Compare 2 Products to see if their barcodes are equal. Uses String.equals() on the
	 * product barcodes.
	 * 
	 * @param o
	 *            Product to compare
	 * @return true if this.barcode.equals(p.barcode), false otherwise
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o instanceof Product) {
			Product other = (Product) o;
			return barcode.toString().equals(other.barcode.toString());
		} else {
			return super.equals(o);
		}
	}

	/**
	 * Gets this Product's barcode
	 * 
	 * @return this Product's barcode
	 */
	public String getBarcode() {
		return barcode.toString();
	}

	/**
	 * Gets this Product's creation date
	 * 
	 * @return this Product's creation date
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Gets this Product's description
	 * 
	 * @return this Product's description
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public String getDescription() {
		return description.toString();
	}

	/**
	 * Returns the number of items associated with this Product.
	 * 
	 * @pre true
	 * @post true
	 */
	public int getItemCount() {
		return items.size();
	}

	/**
	 * Returns an iterator over items that refer to this Product
	 * 
	 * @return
	 */
	public Iterator<Item> getItemsIterator() {
		return items.iterator();
	}

	/**
	 * Gets the ProductQuantity of this Product.
	 * 
	 * @return the Product Quantity of this Product.
	 * @pre true
	 * @post true
	 */
	public ProductQuantity getProductQuantity() {
		return productQuantity;
	}

	/**
	 * Gets this Product's shelf life
	 * 
	 * @return this Product's shelf life
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public int getShelfLife() {
		return shelfLife;
	}

	/**
	 * Gets this Product's size
	 * 
	 * @return this Product's size
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public ProductQuantity getSize() {
		return productQuantity;
	}

	/**
	 * Gets this Product's three-month supply.
	 * 
	 * @return this Product's three-month supply.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public int getThreeMonthSupply() {
		return threeMonthSupply;
	}

	/**
	 * Determines whether this Product has the specified ProductContainer as a parent.
	 * 
	 * @param container
	 * @return
	 */
	public boolean hasContainer(ProductContainer container) {
		return productContainers.contains(container);
	}

	/**
	 * Add a ProductContainer to this Product's set of containers.
	 * 
	 * @param pc
	 *            ProductContainer to add.
	 * 
	 * @pre pc != null
	 * @post productContainers.contains(pc)
	 * 
	 */
	public void removeContainer(ProductContainer pc) {
		if (pc == null) {
			throw new NullPointerException("Null ProductContainer pc");
		}

		productContainers.remove(pc);
	}

	/**
	 * Removes an Item from this Product's set of items.
	 * 
	 * @param item
	 *            Item to remove.
	 * 
	 * @pre item != null
	 * @post !items.contains(item)
	 * 
	 */
	public void removeItem(Item item) {
		if (item == null) {
			throw new NullPointerException("Null Item item");
		}
		if (!items.contains(item)) {
			throw new IllegalArgumentException("Product does not contain item");
		}

		items.remove(item);
	}

	/**
	 * Sets the description for this product object.
	 * 
	 * @param descr
	 *            The (String) description to apply to this product.
	 * 
	 * @pre descr != null
	 * @pre !descr.isEmpty("")
	 * @post true
	 * 
	 */
	public void setDescription(String descr) {
		description = new NonEmptyString(descr);
	}

	/**
	 * Sets the ProductQuantity of this Product.
	 * 
	 * @param pq
	 *            the ProductQuantity to set
	 * @throws IllegalStateException
	 *             if the ProductQuantity is invalid
	 */
	public void setProductQuantity(ProductQuantity pq) {
		if (!isValidProductQuantity(pq)) {
			throw new IllegalArgumentException("Product quantity for product is invalid");
		}
		productQuantity = pq;
	}

	/**
	 * Sets this products shelf life
	 * 
	 * @param shelfLife
	 *            to set
	 * @pre shelfLife >= 0
	 * @post this.shelfLife = shelfLife
	 */
	public void setShelfLife(int shelfLife) {
		if (shelfLife < 0)
			throw new IllegalArgumentException("ShelfLife must be non-negative");

		this.shelfLife = shelfLife;
	}

	/**
	 * Sets this products three month supply
	 * 
	 * @param threeMonthSupply
	 *            to set
	 * @pre threeMonthSupply >= 0
	 * @post this.threeMonthSupply = threeMonthSupply
	 */
	public void setThreeMonthSupply(int threeMonthSupply) {
		if (threeMonthSupply < 0)
			throw new IllegalArgumentException("Three Month Supply must be non-negative");

		this.threeMonthSupply = threeMonthSupply;
	}

	private void setBarcode(String barcode) {
		this.barcode = new NonEmptyString(barcode);
	}

	private void setCreationDate(Date date) {
		Date now = new Date();
		if (!date.after(now)) {
			creationDate = date;
		} else {
			throw new IllegalArgumentException("CreationDate cannot be in the future");
		}
	}
}
