package model.report;

import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Abstract class for reports. 
 * @author Matthew
 *
 */
public abstract class Report {

	private Date lastRunTime;
	
	/**
	 * Will update the lastRunTime to now. Should be called
	 * when generating a report.
	 * 
	 * @pre true
	 * @post lastRunTime = now
	 */
	protected void updateLastRunTime(){
		throw new NotImplementedException();
	}
	
	/**
	 * Gets the last time this report was run
	 * @return Date describing the last time this report was run or null
	 * if this report has not been run
	 * 
	 * @pre true
	 * @post true
	 */
	public Date getLastRunTime(){
		throw new NotImplementedException();
	}
}
