package gui.product;

import model.Product;
import model.ProductManager;
import model.ProductQuantity;
import model.Unit;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnits;
import gui.common.View;

/**
 * Controller class for the edit product view.
 */
public class EditProductController extends Controller implements IEditProductController {

	private ProductData target;
	
	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the edit product view
	 * @param target
	 *            Product being edited
	 */
	public EditProductController(IView view, ProductData target) {
		super(view);
		this.target = target;
		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the edit product view.
	 */
	@Override
	public void editProduct() {
		Product oldProduct = (Product) target.getTag();
		String newDescription = getView().getDescription();
		Unit newUnit = Unit.convertToUnit(getView().getSizeUnit().toString());
		float quantity = Float.parseFloat(getView().getSizeValue());
		ProductQuantity newQuantity = new ProductQuantity(quantity, newUnit);
		int newShelfLife = Integer.parseInt(getView().getShelfLife());
		int newTms = Integer.parseInt(getView().getSupply());
		getView().getProductManager().editProduct(oldProduct, 
				newDescription, newQuantity, newShelfLife, newTms);
	}

	/**
	 * This method is called when any of the fields in the edit product view is changed by the
	 * user.
	 */
	@Override
	public void valuesChanged() {
		if (getView().getSizeUnit().equals(SizeUnits.Count))
			getView().setSizeValue("1");
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
		getView().enableDescription(true);
		getView().enableOK(isAllDataValid());
		getView().enableShelfLife(true);
		getView().enableSizeUnit(true);
		getView().enableSizeValue(!getView().getSizeUnit().equals(SizeUnits.Count));
		getView().enableSupply(true);
	}

	//
	// IEditProductController overrides
	//

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IEditProductView getView() {
		return (IEditProductView) super.getView();
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
		getView().setDescription(target.getDescription());
		getView().setShelfLife(target.getShelfLife());
		String unitString = ((Product)target.getTag()).getProductQuantity().getUnits().toString();
		if (unitString.contains(" "))
			unitString = unitString.replace(" ", "");

		getView().setSizeUnit(SizeUnits.valueOf(unitString));
		getView().setSizeValue(target.getSize());
		getView().setSupply(target.getSupply());
	}
	
	
	private boolean isAllDataValid() {
		int shelfLife = 0;
		try {
			shelfLife = Integer.parseInt(getView().getShelfLife());
			if (!Product.isValidShelfLife(shelfLife))
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		double sizeValue = 1;
		try {
			sizeValue = Double.parseDouble(getView().getSizeValue());
			Unit unit = Unit.convertFromSizeUnits(getView().getSizeUnit());
			if (!ProductQuantity.isValidProductQuantity((float) sizeValue, unit))
				return false;
			ProductQuantity pq = new ProductQuantity((float) sizeValue, unit);
			if (!Product.isValidProductQuantity(pq))
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		int tms = 0;
		try {
			tms = Integer.parseInt(getView().getSupply());
			if (!Product.isValidThreeMonthSupply(tms))
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return (!getView().getBarcode().equals("") && !getView().getDescription().equals(""));
	}

}
