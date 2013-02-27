package gui.batches;

import gui.common.Controller;
import gui.common.DataWrapper;
import gui.common.IView;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Iterator;
import java.util.Set;

import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainer;

/**
 * Controller class for the remove item batch view.
 */
public class RemoveItemBatchController extends Controller implements
		IRemoveItemBatchController {

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the remove item batch view.
	 */
	public RemoveItemBatchController(IView view) {
		super(view);

		construct();
	}

	/**
	 * This method is called when the "Item Barcode" field is changed in the remove item batch
	 * view by the user.
	 */
	@Override
	public void barcodeChanged() {
		enableComponents();
	}

	/**
	 * This method is called when the user clicks the "Done" button in the remove item batch
	 * view.
	 */
	@Override
	public void done() {
		getView().close();
	}

	/**
	 * This method is called when the user clicks the "Redo" button in the remove item batch
	 * view.
	 */
	@Override
	public void redo() {
	}

	/**
	 * This method is called when the user clicks the "Remove Item" button in the remove item
	 * batch view.
	 */
	@Override
	public void removeItem() {
		RemoveItemBatchView view = (RemoveItemBatchView) getView();
		ItemManager manager = getItemManager();

		Item retrievedItem = manager.getItemByItemBarcode(view.getBarcode());

		// show dialog box if item doesn't exist
		if (retrievedItem == null) {
			view.displayErrorMessage("This item doesn't exist.");
			view.setBarcode("");
			return;
		}

		ProductContainer parent = retrievedItem.getContainer();
		parent.remove(retrievedItem, manager);
		// retrievedItem.remove();

		// update product view
		ProductData[] products = new ProductData[1];
		products[0] = DataWrapper.wrap(retrievedItem.getProduct(), 0);
		view.setProducts(products);

		// update item view
		ItemData[] deleted = new ItemData[1];
		deleted[0] = DataWrapper.wrap(retrievedItem);
		getView().setItems(deleted);
	}

	/**
	 * This method is called when the selected product changes in the remove item batch view.
	 */
	@Override
	public void selectedProductChanged() {
		RemoveItemBatchView view = (RemoveItemBatchView) getView();
		ItemManager manager = getItemManager();

		ProductData selected = view.getSelectedProduct();
		if (selected == null)
			return;

		// refresh item view
		Set<Item> items = manager.getItemsByProduct((Product) selected.getTag());
		ItemData[] dataItems = new ItemData[items.size()];
		Iterator<Item> it = items.iterator();
		int counter = 0;
		while (it.hasNext())
			dataItems[counter++] = DataWrapper.wrap(it.next());

		view.setItems(dataItems);
	}

	/**
	 * This method is called when the user clicks the "Undo" button in the remove item batch
	 * view.
	 */
	@Override
	public void undo() {
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting is changed in the remove
	 * item batch view by the user.
	 */
	@Override
	public void useScannerChanged() {
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
		getView().enableItemAction(!getView().getBarcode().equals(""));
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IRemoveItemBatchView getView() {
		return (IRemoveItemBatchView) super.getView();
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
		// no code needed - no pre-loaded state for this batch
	}

}
