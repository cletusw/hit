package gui.reports.removed;

import gui.common.Controller;
import gui.common.IView;

import java.util.Date;

import model.report.RemovedItemsReport;
import model.report.builder.HtmlBuilder;
import model.report.builder.PdfBuilder;
import model.report.builder.ReportBuilder;

/**
 * Controller class for the removed items report view.
 */
public class RemovedReportController extends Controller implements IRemovedReportController {
	private final RemovedItemsReport report;

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the removed items report view
	 */
	public RemovedReportController(IView view) {
		super(view);
		report = getReportManager().getRemovedItemsReport();
		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the removed items report
	 * view.
	 */
	@Override
	public void display() {
		Date sinceDate = getView().getSinceDate() ? getView().getSinceDateValue() : report
				.getLastRunTime();
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

		report.construct(builder, sinceDate);
	}

	/**
	 * This method is called when any of the fields in the removed items report view is changed
	 * by the user.
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
		getView().enableSinceDateValue(getView().getSinceDate());
		getView().enableSinceLast(report.getLastRunTime() != null);
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
	protected IRemovedReportView getView() {
		return (IRemovedReportView) super.getView();
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
		getView().setSinceDate(true);
		if (report.getLastRunTime() != null) {
			getView().setSinceLast(true);
			getView().setSinceLastValue(report.getLastRunTime());
		}
	}

}
