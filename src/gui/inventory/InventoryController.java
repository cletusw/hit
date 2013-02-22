package gui.inventory;

import gui.common.Controller;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.ConcreteItemManager;
import model.ConcreteProductContainerManager;
import model.ConcreteProductManager;
import model.ItemManager;
import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;
import model.ProductManager;
import model.StorageUnit;

/**
 * Controller class for inventory view.
 * 
 * @invariant itemManager != null
 * @invariant productManager != null
 * @invariant productContainerManager != null
 */
public class InventoryController extends Controller implements IInventoryController {
	private final ItemManager itemManager;
	private final ProductManager productManager;
	private final ProductContainerManager productContainerManager;
	private final Random rand = new Random();

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
		this(view, new ConcreteItemManager(), new ConcreteProductManager(),
				new ConcreteProductContainerManager());
	}

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the inventory view
	 * @param itemManager
	 *            Reference to the item manager
	 * @param productManager
	 *            Reference to the product manager
	 * @param productContainerManager
	 *            Reference to the product container manager
	 * 
	 * @pre view != null
	 * @pre itemManager != null
	 * @pre productManager != null
	 * @pre productContainerManager != null
	 * @post true
	 */
	public InventoryController(IInventoryView view, ItemManager itemManager,
			ProductManager productManager, ProductContainerManager productContainerManager) {
		super(view);
		this.itemManager = itemManager;
		this.productManager = productManager;
		this.productContainerManager = productContainerManager;
		construct();
	}

	/**
	 * This method is called when the user selects the "Add Items" menu item.
	 * 
	 * @pre canAddItems()
	 * @post true
	 */
	@Override
	public void addItems() {
		if (!canAddItems()) {
			throw new IllegalStateException("Unable to add Items");
		}
		getView().displayAddItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Add Product Group" menu item.
	 * 
	 * @pre canAddProductGroup()
	 * @post true
	 */
	@Override
	public void addProductGroup() {
		if (!canAddProductGroup()) {
			throw new IllegalStateException("Unable to add Product Groups");
		}
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
	 * @pre canAddStorageUnit()
	 * @post true
	 */
	@Override
	public void addStorageUnit() {
		if (!canAddStorageUnit()) {
			throw new IllegalStateException("Unable to add Storage Units");
		}
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

		// case 1:
		ProductContainerData selected = getView().getSelectedProductContainer();
		if (selected == null)
			return false;

		// case 2:

		return true;
	}

	/**
	 * Returns true if and only if the "Delete Product Group" menu item should be enabled.
	 * 
	 * @pre getView().getSelectedProductContainer().getTag() instanceof ProductGroup
	 * @post true
	 */
	@Override
	public boolean canDeleteProductGroup() {
		// Enabled only if getView().getSelectedProductContainer() does not contain any
		// items (including it's sub Product Groups)
		// See Functional Spec p17

		return ((ProductGroup) getSelectedProductContainerTag()).canRemove();
	}

	/**
	 * Returns true if and only if the "Delete Storage Unit" menu item should be enabled.
	 * 
	 * @pre getView().getSelectedProductContainer().getTag() instanceof StorageUnit
	 * @post true
	 */
	@Override
	public boolean canDeleteStorageUnit() {
		// Enabled only if getView().getSelectedProductContainer() does not contain any
		// items (including it's Product Groups)
		// See Functional Spec p15

		return ((StorageUnit) getSelectedProductContainerTag()).canRemove();
	}

	/**
	 * Returns true if and only if the "Edit Item" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canEditItem() {
		// Always enabled, since it is only called when the Item context menu is displayed
		// See Functional Spec p22
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Product" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canEditProduct() {
		// Always enabled, since it is only called when the Product context menu is displayed
		// See Functional Spec p21
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Product Group" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canEditProductGroup() {
		// Always enabled per Functional Spec p16
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Storage Unit" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canEditStorageUnit() {
		// Always enabled per Functional Spec p14
		return true;
	}

	/**
	 * Returns true if and only if the "Remove Item" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canRemoveItem() {
		// Always enabled per Functional Spec p26
		return true;
	}

	/**
	 * Returns true if and only if the "Remove Items" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canRemoveItems() {
		// Always enabled per Functional Spec p26
		return true;
	}

	/**
	 * Returns true if and only if the "Transfer Items" menu item should be enabled.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean canTransferItems() {
		// Always enabled per Functional Spec p24
		return true;
	}

	/**
	 * This method is called when the user selects the "Delete Product" menu item.
	 * 
	 * @pre canDeleteProduct()
	 * @post !productManager.contains(old(getView().getSelectedProduct().getTag()))
	 */
	@Override
	public void deleteProduct() {
		if (!canDeleteProduct()) {
			throw new IllegalStateException("Unable to delete Product");
		}
	}

	/**
	 * This method is called when the user selects the "Delete Product Group" menu item.
	 * 
	 * @pre canDeleteProductGroup()
	 * @post !productContainerManager.contains(PREVIOUS
	 *       getView().getSelectedProductContainer().getTag())
	 */
	@Override
	public void deleteProductGroup() {
		if (!canDeleteProductGroup()) {
			throw new IllegalStateException("Unable to delete Product Group");
		}
	}

	/**
	 * This method is called when the user selects the "Delete Storage Unit" menu item.
	 * 
	 * @pre canDeleteStorageUnit()
	 * @post !productContainerManager.contains(PREVIOUS
	 *       getView().getSelectedProductContainer().getTag())
	 */
	@Override
	public void deleteStorageUnit() {
		if (!canDeleteStorageUnit()) {
			throw new IllegalStateException("Unable to delete Storage Unit");
		}
	}

	/**
	 * This method is called when the user selects the "Edit Item" menu item.
	 * 
	 * @pre canEditItem()
	 * @post itemManager.contains(old(getView().getSelectedItem().getTag()))
	 */
	@Override
	public void editItem() {
		if (!canEditItem()) {
			throw new IllegalStateException("Unable to edit Item");
		}
		getView().displayEditItemView();
	}

	/**
	 * This method is called when the user selects the "Edit Product" menu item.
	 * 
	 * @pre canEditProduct()
	 * @post productManager.contains(old(getView().getSelectedProduct().getTag()))
	 */
	@Override
	public void editProduct() {
		if (!canEditProduct()) {
			throw new IllegalStateException("Unable to edit Product");
		}
		getView().displayEditProductView();
	}

	/**
	 * This method is called when the user selects the "Edit Product Group" menu item.
	 * 
	 * @pre canEditProductGroup()
	 * @post productContainerManager.contains(PREVIOUS
	 *       getView().getSelectedProductContainer().getTag())
	 */
	@Override
	public void editProductGroup() {
		if (!canEditProductGroup()) {
			throw new IllegalStateException("Unable to edit Product Group");
		}
		getView().displayEditProductGroupView();
	}

	/**
	 * This method is called when the user selects the "Edit Storage Unit" menu item.
	 * 
	 * @pre canEditStorageUnit()
	 * @post productContainerManager.contains(PREVIOUS
	 *       getView().getSelectedProductContainer().getTag())
	 */
	@Override
	public void editStorageUnit() {
		if (!canEditStorageUnit()) {
			throw new IllegalStateException("Unable to edit Storage Unit");
		}
		getView().displayEditStorageUnitView();
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

	private String getRandomBarcode() {
		Random rand = new Random();
		StringBuilder barcode = new StringBuilder();
		for (int i = 0; i < 12; ++i) {
			barcode.append(((Integer) rand.nextInt(10)).toString());
		}
		return barcode.toString();
	}

	private Object getSelectedProductContainerTag() {
		ProductContainerData selectedPC = getView().getSelectedProductContainer();
		if (selectedPC == null)
			throw new NullPointerException("selected container is null");

		return selectedPC.getTag();
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IInventoryView getView() {
		return (IInventoryView) super.getView();
	}

	/**
	 * This method is called when the selected item changes.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public void itemSelectionChanged() {
		// Shouldn't really be anything to do here, but I'm not sure.
		return;
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
		// TODO: Load real data
		ProductContainerData root = new ProductContainerData();
		ProductContainerManager manager = getView().getProductContainerManager();
		Iterator<StorageUnit> storageUnitIterator = manager.getStorageUnitIterator();
		while (storageUnitIterator.hasNext()) {
			ProductContainer pc = storageUnitIterator.next();
			ProductContainerData suData = new ProductContainerData(pc.getName());
			suData.setTag(pc);
			root.addChild(suData);
			ArrayList<ProductGroup> childProductGroupStack = new ArrayList<ProductGroup>();
			// ArrayList<ProductContainerData> productContainerDataStack = new
			// ArrayList<ProductContainerData>();
			do {
				Iterator<ProductGroup> productGroupIterator = pc.getProductGroupIterator();
				// for ()
			} while (!childProductGroupStack.isEmpty());
		}
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

	/**
	 * This method is called when the user drags an item into a product container.
	 * 
	 * @param itemData
	 *            Item dragged into the target product container
	 * @param containerData
	 *            Target product container
	 * @pre getView().getSelectedProductContainer() != null
	 * @pre itemData != null
	 * @pre containerData != null
	 * @pre getView().getSelectedProductContainer().getTag().contains(itemData.getTag())
	 * @pre !containerData.getTag().contains(itemData.getTag())
	 * @post !old(getView().getSelectedProductContainer().getTag().contains(itemData.getTag()))
	 * @post containerData.getTag().contains(itemData.getTag())
	 */
	@Override
	public void moveItemToContainer(ItemData itemData, ProductContainerData containerData) {
	}

	/**
	 * This method is called when the selected item container changes.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public void productContainerSelectionChanged() {
		// TODO: load productDataList from Model
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

		// TODO: Load itemDataList from Model
		List<ItemData> itemDataList = new ArrayList<ItemData>();
		getView().setItems(itemDataList.toArray(new ItemData[0]));
	}

	/**
	 * This method is called when the selected product changes.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public void productSelectionChanged() {
		// TODO: Load itemDataList from Model
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
	 * @post !itemManager.contains(old(getView().getSelectedItem().getTag()))
	 */
	@Override
	public void removeItem() {
		if (!canRemoveItem()) {
			throw new IllegalStateException("Unable to remove Item");
		}
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
		if (!canRemoveItems()) {
			throw new IllegalStateException("Unable to remove Items");
		}
		getView().displayRemoveItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Transfer Items" menu item.
	 * 
	 * @pre canTransferItems()
	 * @post true
	 */
	@Override
	public void transferItems() {
		if (!canTransferItems()) {
			throw new IllegalStateException("Unable to edit Storage Unit");
		}
		getView().displayTransferItemBatchView();
	}

}
