package gui.batches;

import gui.common.Controller;
import gui.common.IView;
import gui.inventory.ProductContainerData;
import model.Product;
import model.ProductQuantity;
import model.Unit;

/**
 * Controller class for the add item batch view.
 */
public class AddItemBatchController extends Controller implements IAddItemBatchController {

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the add item batch view.
	 * @param target
	 *            Reference to the storage unit to which items are being added.
	 */
	public AddItemBatchController(IView view, ProductContainerData target) {
		super(view);
		// Phase 2: not using the scanner
		getView().setUseScanner(false);
		getView().setCount("1");
		construct();
	}

	/**
	 * This method is called when the user clicks the "Add Item" button in the add item batch
	 * view.
	 * 
	 * @post The item specified by the fields on AddItemBatchView now exists in the selected
	 *       StorageUnit
	 */
	@Override
	public void addItem() {
		// Item item = new Item();

		// if view.getController().
		getView().displayAddProductView();

		// target.addItem(item);

		// Clear the view for the next item!
		getView().setBarcode("");
		loadValues();
		enableComponents();
	}

	/**
	 * This method is called when the "Product Barcode" field in the add item batch view is
	 * changed by the user.
	 */
	@Override
	public void barcodeChanged() {
		enableComponents();
	}

	/**
	 * This method is called when the "Count" field in the add item batch view is changed by
	 * the user.
	 */
	@Override
	public void countChanged() {
		enableComponents();
	}

	/**
	 * This method is called when the user clicks the "Done" button in the add item batch view.
	 */
	@Override
	public void done() {
		getView().close();
	}

	/**
	 * This method is called when the "Entry Date" field in the add item batch view is changed
	 * by the user.
	 */
	@Override
	public void entryDateChanged() {
	}

	/**
	 * This method is called when the user clicks the "Redo" button in the add item batch view.
	 */
	@Override
	public void redo() {
	}

	/**
	 * This method is called when the selected product changes in the add item batch view.
	 */
	@Override
	public void selectedProductChanged() {
		// TODO: Need to update the items shown in the Items pane
		loadValues();
	}

	/**
	 * This method is called when the user clicks the "Undo" button in the add item batch view.
	 */
	@Override
	public void undo() {
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting in the add item batch view
	 * is changed by the user.
	 */
	@Override
	public void useScannerChanged() {
		if (getView().getUseScanner()) {
			getView().setBarcode("");
		}
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
		getView().enableUndo(false);
		getView().enableRedo(false);
		boolean isValidCount = true;
		try {
			int count = Integer.parseInt(getView().getCount());
			isValidCount = ProductQuantity.isValidProductQuantity(count, Unit.COUNT);
		} catch (NumberFormatException e) {
			isValidCount = false;
		}
		getView().enableItemAction(
				Product.isValidBarcode(getView().getBarcode()) && isValidCount);
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IAddItemBatchView getView() {
		return (IAddItemBatchView) super.getView();
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
	}

}
