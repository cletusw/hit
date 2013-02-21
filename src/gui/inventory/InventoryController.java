package gui.inventory;

import gui.common.Controller;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import model.ConcreteItemManager;
import model.ConcreteProductContainerManager;
import model.ConcreteProductManager;
import model.ItemManager;
import model.ProductContainerManager;
import model.ProductManager;

/**
 * Controller class for inventory view.
 * 
 * @invariant itemManager != null
 * @invariant productManager != null
 * @invariant productContainerManager != null
 */
public class InventoryController extends Controller implements IInventoryController {
	private ItemManager itemManager;
	private ProductManager productManager;
	private ProductContainerManager productContainerManager;
	private Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the inventory view
	 * 
	 * @pre view != null
	 * @post true
	 */
	public InventoryController(IInventoryView view) {
		super(view);
		itemManager = new ConcreteItemManager();
		productManager = new ConcreteProductManager();
		productContainerManager = new ConcreteProductContainerManager();
		construct();
	}

	/**
	 * This method is called when the user selects the "Add Items" menu item.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public void addItems() {
		getView().displayAddItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Add Product Group" menu item.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public void addProductGroup() {
		getView().displayAddProductGroupView();
	}

	//
	// IInventoryController overrides
	//

	/**
	 * This method is called when the user drags a product into a product container.
	 * 
	 * @param productData
	 *            Product dragged into the target product container
	 * @param containerData
	 *            Target product container
	 * @pre productData != null
	 * @pre containerData != null
	 * @post containerData.getChildCount() == old(getChildCount()) + 1
	 */
	@Override
	public void addProductToContainer(ProductData productData,
			ProductContainerData containerData) {
	}

	/**
	 * This method is called when the user selects the "Add Storage Unit" menu item.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public void addStorageUnit() {
		getView().displayAddStorageUnitView();
	}

	/**
	 * Returns true if and only if the "Add Items" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canAddItems() {
		// Always enabled per Functional Spec p17
		return true;
	}

	/**
	 * Returns true if and only if the "Add Product Group" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canAddProductGroup() {
		// Always enabled per Functional Spec p15
		return true;
	}

	/**
	 * Returns true if and only if the "Add Storage Unit" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canAddStorageUnit() {
		// Always enabled per Functional Spec p14
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Product" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canDeleteProduct() {
		// TODO: 3 cases depending on getView().getSelectedProductContainer().
		// See Functional Spec p21-22
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Product Group" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canDeleteProductGroup() {
		// TODO: Enabled only if getView().getSelectedProductContainer() does not contain any
		// items (including it's sub Product Groups)
		// See Functional Spec p17
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Storage Unit" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if there are no Items in the selected StorageUnit
	 */
	@Override
	public boolean canDeleteStorageUnit() {
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Item" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if view.getSelectedItem() != null
	 */
	@Override
	public boolean canEditItem() {
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Product" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if view.getSelectedProduct() != null
	 */
	@Override
	public boolean canEditProduct() {
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Product Group" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if view.getSelectedProductContainer() != null &&
	 *       view.getSelectedProductContainer().getTag() instanceof ProductGroup
	 */
	@Override
	public boolean canEditProductGroup() {
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Storage Unit" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if view.getSelectedProductContainer() != null &&
	 *       view.getSelectedProductContainer().getTag() instanceof StorageUnit
	 */
	@Override
	public boolean canEditStorageUnit() {
		return true;
	}

	/**
	 * Returns true if and only if the "Remove Item" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if view.getSelectedItem() != null
	 */
	@Override
	public boolean canRemoveItem() {
		return true;
	}

	/**
	 * Returns true if and only if the "Remove Items" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if view.getSelectedProductContainer() == null
	 */
	@Override
	public boolean canRemoveItems() {
		return true;
	}

	/**
	 * Returns true if and only if the "Transfer Items" menu item should be enabled.
	 * 
	 * @pre true
	 * @post Returns true if view.getSelectedProductContainer() != null &&
	 *       view.getSelectedProductContainer().getTag() instanceof StorageUnit
	 */
	@Override
	public boolean canTransferItems() {
		return true;
	}

	/**
	 * This method is called when the user selects the "Delete Product" menu item.
	 * 
	 * @pre canDeleteProduct()
	 * @post !productManager.contains(old(view.getSelectedProduct().getTag()));
	 */
	@Override
	public void deleteProduct() {
	}

	/**
	 * This method is called when the user selects the "Delete Product Group" menu item.
	 * 
	 * @pre canDeleteProductGroup()
	 * @post 
	 *       !productContainerManager.contains(old(view.getSelectedProductContainer().getTag()))
	 *       ;
	 */
	@Override
	public void deleteProductGroup() {
	}

	/**
	 * This method is called when the user selects the "Delete Storage Unit" menu item.
	 * 
	 * @pre canDeleteStorageUnit()
	 * @post 
	 *       !productContainerManager.contains(old(view.getSelectedProductContainer().getTag()))
	 *       ;
	 */
	@Override
	public void deleteStorageUnit() {
	}

	/**
	 * This method is called when the user selects the "Edit Item" menu item.
	 * 
	 * @pre canEditItem()
	 * @post itemManager.contains(old(view.getSelectedItem().getTag()))
	 */
	@Override
	public void editItem() {
		getView().displayEditItemView();
	}

	/**
	 * This method is called when the user selects the "Edit Product" menu item.
	 * 
	 * @pre canEditProduct()
	 * @post productManager.contains(old(view.getSelectedProduct().getTag()))
	 */
	@Override
	public void editProduct() {
		getView().displayEditProductView();
	}

	/**
	 * This method is called when the user selects the "Edit Product Group" menu item.
	 * 
	 * @pre canEditProductGroup()
	 * @post productContainerManager.contains(old(view.getSelectedProductContainer().getTag()))
	 */
	@Override
	public void editProductGroup() {
		getView().displayEditProductGroupView();
	}

	/**
	 * This method is called when the user selects the "Edit Storage Unit" menu item.
	 * 
	 * @pre canEditStorageUnit()
	 * @post productContainerManager.contains(old(view.getSelectedProductContainer().getTag()))
	 */
	@Override
	public void editStorageUnit() {
		getView().displayEditStorageUnitView();
	}

	/**
	 * This method is called when the selected item changes.
	 * 
	 * @pre true
	 * @post view.getSelectedItem() != old(view.getSelectedItem())
	 */
	@Override
	public void itemSelectionChanged() {
		return;
	}

	/**
	 * This method is called when the user drags an item into a product container.
	 * 
	 * @param itemData
	 *            Item dragged into the target product container
	 * @param containerData
	 *            Target product container
	 * @pre view.getSelectedProductContainer() != null
	 * @pre itemData != null
	 * @pre containerData != null
	 * @post !old(view.getSelectedProductContainer().getTag().contains(itemData.getTag()))
	 * @post containerData.getTag().contains(itemData.getTag())
	 */
	@Override
	public void moveItemToContainer(ItemData itemData, ProductContainerData containerData) {
	}

	/**
	 * This method is called when the selected item container changes.
	 * 
	 * @pre true
	 * @post old(view.getSelectedProductContainer()) != view.getSelectedProductContainer()
	 */
	@Override
	public void productContainerSelectionChanged() {
		List<ProductData> productDataList = new ArrayList<ProductData>();
		ProductContainerData selectedContainer = getView().getSelectedProductContainer();
		if (selectedContainer != null) {
			int productCount = rand.nextInt(20) + 1;
			for (int i = 1; i <= productCount; ++i) {
				ProductData productData = new ProductData();
				productData.setBarcode(getRandomBarcode());
				int itemCount = rand.nextInt(25) + 1;
				productData.setCount(Integer.toString(itemCount));
				productData.setDescription("Item " + i);
				productData.setShelfLife("3 months");
				productData.setSize("1 pounds");
				productData.setSupply("10 count");

				productDataList.add(productData);
			}
		}
		getView().setProducts(productDataList.toArray(new ProductData[0]));

		getView().setItems(new ItemData[0]);
	}

	/**
	 * This method is called when the selected item changes.
	 * 
	 * @pre true
	 * @post old(view.getSelectedProduct()) != view.getSelectedProduct()
	 */
	@Override
	public void productSelectionChanged() {
		List<ItemData> itemDataList = new ArrayList<ItemData>();
		ProductData selectedProduct = getView().getSelectedProduct();
		if (selectedProduct != null) {
			Date now = new Date();
			GregorianCalendar cal = new GregorianCalendar();
			int itemCount = Integer.parseInt(selectedProduct.getCount());
			for (int i = 1; i <= itemCount; ++i) {
				cal.setTime(now);
				ItemData itemData = new ItemData();
				itemData.setBarcode(getRandomBarcode());
				cal.add(Calendar.MONTH, -rand.nextInt(12));
				itemData.setEntryDate(cal.getTime());
				cal.add(Calendar.MONTH, 3);
				itemData.setExpirationDate(cal.getTime());
				itemData.setProductGroup("Some Group");
				itemData.setStorageUnit("Some Unit");

				itemDataList.add(itemData);
			}
		}
		getView().setItems(itemDataList.toArray(new ItemData[0]));
	}

	/**
	 * This method is called when the user selects the "Remove Item" menu item.
	 * 
	 * @pre canRemoveItem()
	 * @post !itemManager.contains(old(view.getSelectedItem().getTag()))
	 */
	@Override
	public void removeItem() {
	}

	/**
	 * This method is called when the user selects the "Remove Items" menu item.
	 * 
	 * @pre canRemoveItems()
	 * @post itemManager no longer contains any of the items matching those removed by the
	 *       user.
	 */
	@Override
	public void removeItems() {
		getView().displayRemoveItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Transfer Items" menu item.
	 * 
	 * @pre canTransferItems()
	 * @post the current storage unit no longer contains the items specified by the user
	 * @post the target storage unit(s) now contain the items specified by the user according
	 *       to where they were put
	 */
	@Override
	public void transferItems() {
		getView().displayTransferItemBatchView();
	}

	private String getRandomBarcode() {
		Random rand = new Random();
		StringBuilder barcode = new StringBuilder();
		for (int i = 0; i < 12; ++i) {
			barcode.append(((Integer) rand.nextInt(10)).toString());
		}
		return barcode.toString();
	}

	/**
	 * Sets the enable/disable state of all components in the controller's view. A component
	 * should be enabled only if the user is currently allowed to interact with that component.
	 * 
	 * {@pre None}
	 * 
	 * {@post The enable/disable state of all components in the controller's view have been set
	 * appropriately.}
	 */
	@Override
	protected void enableComponents() {
		return;
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IInventoryView getView() {
		return (IInventoryView) super.getView();
	}

	/**
	 * Loads data into the controller's view.
	 * 
	 * {@pre None}
	 * 
	 * {@post The controller has loaded data into its view}
	 */
	@Override
	protected void loadValues() {
		ProductContainerData root = new ProductContainerData();

		ProductContainerData basementCloset = new ProductContainerData("Basement Closet");

		ProductContainerData toothpaste = new ProductContainerData("Toothpaste");
		toothpaste.addChild(new ProductContainerData("Kids"));
		toothpaste.addChild(new ProductContainerData("Parents"));
		basementCloset.addChild(toothpaste);

		root.addChild(basementCloset);

		ProductContainerData foodStorage = new ProductContainerData("Food Storage Room");

		ProductContainerData soup = new ProductContainerData("Soup");
		soup.addChild(new ProductContainerData("Chicken Noodle"));
		soup.addChild(new ProductContainerData("Split Pea"));
		soup.addChild(new ProductContainerData("Tomato"));
		foodStorage.addChild(soup);

		root.addChild(foodStorage);

		getView().setProductContainers(root);
	}

}
