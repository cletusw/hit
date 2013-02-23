package gui.product;

import gui.common.Tagable;
import model.Product;

/**
 * Display data class for products.
 */
public class ProductData extends Tagable {

	/**
	 * Description attribute.
	 */
	private String _description;

	/**
	 * Size attribute.
	 */
	private String _size;

	/**
	 * Count attribute.
	 */
	private String _count;

	/**
	 * Shelf Life attribute
	 */
	private String _shelfLife;

	/**
	 * Supply attribute.
	 */
	private String _supply;

	/**
	 * Barcode attribute.
	 */
	private String _barcode;

	/**
	 * Constructor.
	 * 
	 * {@pre None}
	 * 
	 * {@post getDescription() == ""} {@post getSize() == ""} {@post getCount() == ""} {@post
	 * getShelfLife() == ""} {@post getSupply() == ""} {@post getBarcode() == ""}
	 */
	public ProductData() {
		_description = "";
		_size = "";
		_count = "";
		_shelfLife = "";
		_supply = "";
		_barcode = "";
	}

	/**
	 * Constructor with a Product
	 */
	public ProductData(Product product) {
		_description = product.getDescription();
		_size = "" + product.getSize().getQuantity();
		_count = "" + 0;
		_shelfLife = "" + product.getShelfLife();
		_supply = "" + product.getThreeMonthSupply();
		_barcode = "" + product.getBarcode();
		setTag(product);
	}

	/**
	 * Returns the value of the Barcode attribute.
	 */
	public String getBarcode() {
		return _barcode;
	}

	/**
	 * Returns the value of the Count attribute.
	 */
	public String getCount() {
		return _count;
	}

	/**
	 * Returns the value of the Description value.
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * Returns the value of the Shelf Life attribute.
	 */
	public String getShelfLife() {
		return _shelfLife;
	}

	/**
	 * Returns the value of the Size attribute.
	 */
	public String getSize() {
		return _size;
	}

	/**
	 * Returns the value of the Supply attribute.
	 */
	public String getSupply() {
		return _supply;
	}

	/**
	 * Sets the value of the Barcode attribute.
	 * 
	 * @param barcode
	 *            New Barcode value
	 * 
	 *            {@pre barcode != null}
	 * 
	 *            {@post getBarcode() == barcode}
	 */
	public void setBarcode(String barcode) {
		_barcode = barcode;
	}

	/**
	 * Sets the value of the Count attribute.
	 * 
	 * @param count
	 *            New Count value
	 * 
	 *            {@pre count != null}
	 * 
	 *            {@post getCount() == count}
	 */
	public void setCount(String count) {
		_count = count;
	}

	/**
	 * Sets the value of the Description value.
	 * 
	 * @param description
	 *            New Description value
	 * 
	 *            {@pre description != null}
	 * 
	 *            {@post getDescription() == description}
	 */
	public void setDescription(String description) {
		_description = description;
	}

	/**
	 * Sets the value of the Shelf Life attribute.
	 * 
	 * @param shelfLife
	 *            New Shelf Life value
	 * 
	 *            {@pre shelfLife != null}
	 * 
	 *            {@post getShelfLife() == shelfLife}
	 */
	public void setShelfLife(String shelfLife) {
		_shelfLife = shelfLife;
	}

	/**
	 * Sets the value of the Size attribute.
	 * 
	 * @param size
	 *            New Size value
	 * 
	 *            {@pre size != null}
	 * 
	 *            {@post getSize() == size}
	 */
	public void setSize(String size) {
		_size = size;
	}

	/**
	 * Sets the value of the Supply attribute.
	 * 
	 * @param supply
	 *            New Supply value
	 * 
	 *            {@pre supply != null}
	 * 
	 *            {@post getSupply() == supply}
	 */
	public void setSupply(String supply) {
		_supply = supply;
	}

}
