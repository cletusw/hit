package gui.reports.notices;

import gui.common.Controller;
import gui.common.IView;

import java.io.File;
import java.io.IOException;

import model.report.NoticesReport;
import model.report.builder.HtmlBuilder;
import model.report.builder.PdfBuilder;
import model.report.builder.ReportBuilder;

/**
 * Controller class for the notices report view.
 */
public class NoticesReportController extends Controller implements INoticesReportController {

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Reference to the notices report view
	 */
	public NoticesReportController(IView view) {
		super(view);
		construct();
	}

	/**
	 * This method is called when the user clicks the "OK" button in the notices report view.
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
		NoticesReport report = getReportManager().getNoticesReport();
		report.construct(builder);

		try {
			File file = builder.print(report.getFileName());
			java.awt.Desktop.getDesktop().open(file);
		} catch (IOException e) {
			System.out.println("Not able to open!! " + report.getFileName());
		}
	}

	/**
	 * This method is called when any of the fields in the notices report view is changed by
	 * the user.
	 */
	@Override
	public void valuesChanged() {
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
	}

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected INoticesReportView getView() {
		return (INoticesReportView) super.getView();
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
