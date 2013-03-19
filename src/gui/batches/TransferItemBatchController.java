package gui.batches;

import gui.common.Controller;
import gui.common.DataWrapper;
import gui.common.IView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import model.Item;
import model.Product;
import model.ProductContainer;
import model.undo.TransferItem;
import model.undo.UndoManager;

/**
 * Controller class for the transfer item batch view.
 */
public class TransferItemBatchController extends Controller implements
		ITransferItemBatchController {
	private final ProductContainer target;
	private final Map<Product, Set<Item>> transferredProductToItems;
	private final UndoManager undoManager;

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the transfer item batch view.
	 * @param target
	 *            Reference to the storage unit to which items are being transferred.
	 */
	public TransferItemBatchController(IView view, ProductContainerData target) {
		super(view);

		undoManager = new UndoManager();
		this.target = (ProductContainer) target.getTag();
		transferredProductToItems = new TreeMap<Product, Set<Item>>();

		construct();
	}

	/**
	 * This method is called when the "Item Barcode" field in the transfer item batch view is
	 * changed by the user.
	 */
	@Override
	public void barcodeChanged() {
	}

	/**
	 * This method is called when the user clicks the "Done" button in the transfer item batch
	 * view.
	 */
	@Override
	public void done() {
		getView().close();
	}

	/**
	 * This method is called when the user clicks the "Redo" button in the transfer item batch
	 * view.
	 */
	@Override
	public void redo() {
		TransferItem transferItem = (TransferItem) undoManager.redo();
		updateDisplayAfterExecute(transferItem);
	}

	/**
	 * This method is called when the selected product changes in the transfer item batch view.
	 */
	@Override
	public void selectedProductChanged() {
		refreshItems();
	}

	/**
	 * This method is called when the user clicks the "Transfer Item" button in the transfer
	 * item batch view.
	 */
	@Override
	public void transferItem() {
		Item item = getItemManager().getItemByItemBarcode(getView().getBarcode());
		if (item == null) {
			getView().displayErrorMessage("There is no item with this barcode.");
			getView().setBarcode("");
			return;
		}

		ProductContainer source = item.getContainer();
		if (target.contains(item)) {
			getView().displayErrorMessage("Source and destination containers are the same!");
			getView().setBarcode("");
			return;
		}

		TransferItem transferItemCommand = new TransferItem(item, source, target);
		undoManager.execute(transferItemCommand);
		updateDisplayAfterExecute(transferItemCommand);
	}

	/**
	 * This method is called when the user clicks the "Undo" button in the transfer item batch
	 * view.
	 */
	@Override
	public void undo() {
		TransferItem transferItem = (TransferItem) undoManager.undo();
		updateDisplayAfterUndo(transferItem);
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting in the transfer item batch
	 * view is changed by the user.
	 */
	@Override
	public void useScannerChanged() {
	}

	private void refreshItems() {
		ProductData selectedProduct = getView().getSelectedProduct();
		ItemData[] items = new ItemData[0];
		if (selectedProduct != null) {
			if (selectedProduct.getTag() != null) {
				Product product = (Product) selectedProduct.getTag();
				items = DataWrapper.wrap(transferredProductToItems.get(product));
			}
		}

		getView().setItems(items);
	}

	private void refreshProducts() {
		ProductData[] products = DataWrapper.wrap(transferredProductToItems);
		getView().setProducts(products);
	}

	private void updateDisplayAfterExecute(TransferItem transferItemCommand) {
		Item item = transferItemCommand.getTransferredItem();

		if (!transferredProductToItems.containsKey(item.getProduct())) {
			transferredProductToItems.put(item.getProduct(), new TreeSet<Item>());
		}
		transferredProductToItems.get(item.getProduct()).add(item);
		refreshProducts();
		refreshItems();
		enableComponents();
	}

	private void updateDisplayAfterUndo(TransferItem transferItemCommand) {
		Item item = transferItemCommand.getTransferredItem();
		transferredProductToItems.get(item.getProduct()).remove(item);

		Set<Item> itemsForProduct = transferredProductToItems.get(item.getProduct());
		itemsForProduct.remove(item);
		if (itemsForProduct.size() == 0)
			transferredProductToItems.remove(item.getProduct());
		else
			transferredProductToItems.put(item.getProduct(), itemsForProduct);
		refreshProducts();
		refreshItems();
		enableComponents();
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
		getView().enableRedo(undoManager.canRedo());
		getView().enableUndo(undoManager.canUndo());
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected ITransferItemBatchView getView() {
		return (ITransferItemBatchView) super.getView();
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
