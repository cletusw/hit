package gui.storageunit;

import gui.common.Controller;
import gui.common.IView;
import model.ProductContainerManager;
import model.StorageUnit;

/**
 * Controller class for the add storage unit view.
 */
public class AddStorageUnitController extends Controller implements IAddStorageUnitController {

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to add storage unit view
	 */
	public AddStorageUnitController(IView view) {
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the add storage unit view.
	 * 
	 * @post A new Storage Unit with the specified name now exists in the
	 *       ProductContainerManager
	 */
	@Override
	public void addStorageUnit() {
		ProductContainerManager manager = getProductContainerManager();
		new StorageUnit(getView().getStorageUnitName(), manager);
	}

	/**
	 * This method is called when any of the fields in the add storage unit view is changed by
	 * the user.
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
		getView().enableStorageUnitName(true);
		ProductContainerManager manager = getProductContainerManager();
		getView().enableOK(manager.isValidStorageUnitName(getView().getStorageUnitName()));
	}

	//
	// IAddStorageUnitController overrides
	//

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IAddStorageUnitView getView() {
		return (IAddStorageUnitView) super.getView();
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
		getView().setStorageUnitName("");
	}
}
