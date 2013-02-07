package gui.item;

import gui.common.Tagable;

import java.util.Date;

/**
 * Display data class for items.
 */
public class ItemData extends Tagable {

	/**
	 * EntryDate attribute.
	 */
	private Date _entryDate;

	/**
	 * ExpirationDate attribute.
	 */
	private Date _expirationDate;

	/**
	 * Barcode attribute.
	 */
	private String _barcode;

	/**
	 * StorageUnit attribute.
	 */
	private String _storageUnit;

	/**
	 * ProductGroup attribute.
	 */
	private String _productGroup;

	/**
	 * Constructor.
	 * 
	 * {@pre None}
	 * 
	 * {@post getEntryDate() == current date/time}
	 * {@post getExpirationDate() == current date/time}
	 * {@post getBarcode() == ""}
	 * {@post getStorageUnit() == ""}
	 * {@post getProductGroup() == ""}
	 */
	public ItemData() {
		_entryDate = new Date();
		_expirationDate = new Date();
		_barcode = "";
		_storageUnit = "";
		_productGroup = "";
	}

	/**
	 * Returns the value of the Barcode attribute.
	 */
	public String getBarcode() {
		return _barcode;
	}

	/**
	 * Returns the value of the EntryDate attribute.
	 */
	public Date getEntryDate() {
		return _entryDate;
	}

	/**
	 * Returns the value of the ExpirationDate attribute.
	 */
	public Date getExpirationDate() {
		return _expirationDate;
	}

	/**
	 * Returns the value of the ProductGroup attribute.
	 */
	public String getProductGroup() {
		return _productGroup;
	}

	/**
	 * Returns the value of the StorageUnit attribute.
	 */
	public String getStorageUnit() {
		return _storageUnit;
	}

	/**
	 * Sets the value of the Barcode attribute.
	 * 
	 * @param barcode New Barcode value
	 * 
	 * {@pre barcode != null}
	 * 
	 * {@post getBarcode() == barcode}
	 */
	public void setBarcode(String barcode) {
		_barcode = barcode;
	}

	/**
	 * Sets the value of the EntryDate attribute.
	 * 
	 * @param entryDate New EntryDate value
	 * 
	 * {@pre entryDate != null}
	 * 
	 * {@post getEntryDate() == entryDate}
	 */
	public void setEntryDate(Date entryDate) {
		_entryDate = entryDate;
	}

	/**
	 * Sets the value of the ExpirationDate attribute.
	 * 
	 * @param expirationDate New ExpirationDate value
	 * 
	 * {@pre None}
	 * 
	 * {@post getExpirationDate() == expirationDate}
	 */
	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	/**
	 * Sets the value of the ProductGroup attribute.
	 * 
	 * @param productGroup New ProductGroup value
	 * 
	 * {@pre productGroup != null}
	 * 
	 * {@post getProductGroup() == productGroup}
	 */
	public void setProductGroup(String productGroup) {
		_productGroup = productGroup;
	}

	/**
	 * Sets the value of the StorageUnit attribute.
	 * 
	 * @param storageUnit New StorageUnit value
	 * 
	 * {@pre storageUnit != null}
	 * 
	 * {@post getStorageUnit() == storageUnit}
	 */
	public void setStorageUnit(String storageUnit) {
		_storageUnit = storageUnit;
	}

}

