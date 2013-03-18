package gui.reports.productstats;

import gui.common.Controller;
import gui.common.IView;

import java.io.File;
import java.io.IOException;

import model.report.builder.HtmlBuilder;
import model.report.builder.PdfBuilder;
import model.report.builder.ReportBuilder;

/**
 * Controller class for the product statistics report view.
 */
public class ProductStatsReportController extends Controller implements
		IProductStatsReportController {

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the item statistics report view
	 */
	public ProductStatsReportController(IView view) {
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the product statistics
	 * report view.
	 */
	@Override
	public void display() {
		ReportBuilder builder;
		switch (getView().getFormat()) {
		case HTML:
			builder = new HtmlBuilder();
			break;
		case PDF:
			builder = new PdfBuilder();
			break;
		default:
			return;
		}

		builder.addDocumentTitle("NEW PRODUCT STATISTICS REPORT");
		String filename = "test";
		try {
			File file = builder.print(filename);
			java.awt.Desktop.getDesktop().open(file);
		} catch (IOException e) {
			getView().displayErrorMessage("Unable to create report '" + filename + "'");
		}
	}

	/**
	 * This method is called when any of the fields in the product statistics report view is
	 * changed by the user.
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
		getView().enableFormat(true);
		getView().enableMonths(true);
		try {
			int months = Integer.parseInt(getView().getMonths());
			getView().enableOK(months >= 1 && months <= 100);
		} catch (NumberFormatException ex) {
			getView().enableOK(false);
		}
	}

	//
	// IProductStatsReportController overrides
	//

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IProductStatsReportView getView() {
		return (IProductStatsReportView) super.getView();
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
