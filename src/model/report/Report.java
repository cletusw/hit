package model.report;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Abstract class for reports.
 * 
 * @author Matthew
 * 
 */
public abstract class Report {

	private Date lastRunTime;
	protected final String directory = "reports/";
	protected String reportName = "report";

	public Report() {
		File reportDirectory = new File(directory);
		if (!reportDirectory.exists()) {
			reportDirectory.mkdirs();
		}
	}

	public String getFileName() {
		Calendar cal = Calendar.getInstance();

		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		if (month.length() < 2)
			month = "0" + month;

		String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		if (day.length() < 2)
			day = "0" + day;

		String hour = Integer.toString(cal.get(Calendar.HOUR));
		if (hour.length() < 2)
			hour = "0" + hour;

		String minute = Integer.toString(cal.get(Calendar.MINUTE));
		if (minute.length() < 2)
			minute = "0" + minute;

		String second = Integer.toString(cal.get(Calendar.SECOND));
		if (second.length() < 2)
			second = "0" + second;

		String year = Integer.toString(cal.get(Calendar.YEAR));

		return directory + reportName + "-" + month + day + year + hour + minute + second;
	}

	/**
	 * Gets the last time this report was run
	 * 
	 * @return Date describing the last time this report was run or null if this report has not
	 *         been run
	 * 
	 * @pre true
	 * @post true
	 */
	public Date getLastRunTime() {
		return lastRunTime;
	}

	/**
	 * Will update the lastRunTime to now. Should be called when generating a report.
	 * 
	 * @pre true
	 * @post lastRunTime = now
	 */
	protected void updateLastRunTime() {
		lastRunTime = new Date();
	}
}
