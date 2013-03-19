package gui.reports.supply;

import gui.common.Controller;
import gui.common.IView;

import java.io.File;
import java.io.IOException;

import model.report.NMonthSupplyReport;
import model.report.builder.HtmlBuilder;
import model.report.builder.PdfBuilder;
import model.report.builder.ReportBuilder;

/**
 * Controller class for the N-month supply report view.
 */
public class SupplyReportController extends Controller implements ISupplyReportController {
	/*---	STUDENT-INCLUDE-BEGIN

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the N-month supply report view
	 */
	public SupplyReportController(IView view) {
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the N-month supply report
	 * view.
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
		NMonthSupplyReport report = getReportManager().getnMonthSupplyReport();
		report.construct(builder, Integer.parseInt(getView().getMonths()));

		try {
			File file = builder.print(report.getFileName());
			java.awt.Desktop.getDesktop().open(file);
		} catch (IOException e) {
			System.out.println("Not able to open!! " + report.getFileName());
		}
	}

	/**
	 * This method is called when any of the fields in the N-month supply report view is
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
	// IExpiredReportController overrides
	//

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected ISupplyReportView getView() {
		return (ISupplyReportView) super.getView();
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
