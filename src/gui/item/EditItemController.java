package gui.item;

import gui.common.Controller;
import gui.common.IView;
import model.Item;
import model.Product;

/**
 * Controller class for the edit item view.
 */
public class EditItemController extends Controller implements IEditItemController {

	private final ItemData target;

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to edit item view
	 * @param target
	 *            Item that is being edited
	 */
	public EditItemController(IView view, ItemData target) {
		super(view);
		this.target = target;
		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the edit item view.
	 */
	@Override
	public void editItem() {
		if (target.getEntryDate().equals(getView().getEntryDate())) {
			// these are not the droids you're looking for...
			return;
		}
		getItemManager().editItem((Item) target.getTag(), getView().getEntryDate());
	}

	/**
	 * This method is called when any of the fields in the edit item view is changed by the
	 * user.
	 */
	@Override
	public void valuesChanged() {
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
		getView().enableBarcode(false);
		getView().enableDescription(false);
		getView().enableEntryDate(true);

		boolean enableOK = false;
		if (Item.isValidEntryDate(getView().getEntryDate()))
			enableOK = true;
		getView().enableOK(enableOK);
	}

	//
	// IEditItemController overrides
	//

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IEditItemView getView() {
		return (IEditItemView) super.getView();
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
		getView().setBarcode(target.getBarcode());
		Product product = ((Item) target.getTag()).getProduct();
		getView().setDescription(product.getDescription());
		getView().setEntryDate(target.getEntryDate());
	}

}
