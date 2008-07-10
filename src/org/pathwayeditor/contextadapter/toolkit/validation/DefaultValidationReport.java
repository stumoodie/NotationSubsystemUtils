package org.pathwayeditor.contextadapter.toolkit.validation;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationReportItem;

public class DefaultValidationReport implements IValidationReport {

	private List<IValidationReportItem> reportItemList;
	private IMap validatedMap;//immutable
	private long creationTime;// immutable
	private Date creationTimeAsDate; // internal representation for convenience
	
	/**
	 * @param map A non-null IMap to which this validation report refers.
	 * @param itemList A non-null <code>List</code> of {@link IValidationReportItem}s. 
	 * @throws IllegalArgumentException if either parameter is null
	 */
	public DefaultValidationReport(IMap map, List<IValidationReportItem> itemList) {
		if(map == null || itemList == null){
			throw new IllegalArgumentException("Constructor arguments must not be null");
		}
		creationTime = Calendar.getInstance().getTime().getTime();
		creationTimeAsDate = new Date(creationTime);
		reportItemList = itemList;
		validatedMap = map;
	}
	public IMap getMap() {
		return validatedMap;
	}

	public List<IValidationReportItem> getValidationReportItems() {
	     return Collections.unmodifiableList(reportItemList);
	}
    
	public Date getValidationTime() {
		return new Date(creationTime);
	}

	public boolean hasWarnings() {
		for(IValidationReportItem item: reportItemList){
			if(item.getSeverity().equals(IValidationReportItem.Severity.WARNING)){
				return true;
			}
		}
		return false;
	}

	public boolean isMapValid() {
		for(IValidationReportItem item: reportItemList){
			if(item.getSeverity().equals(IValidationReportItem.Severity.ERROR) ){
				return false;
			}
		}
		return true;
	}

	public boolean isReportCurrent() {
	    if(!creationTimeAsDate.before(validatedMap.getLastModifiedTime())){
	    	return true;
	    }
	    return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (creationTime ^ (creationTime >>> 32));
		result = prime * result + ((validatedMap == null) ? 0 : validatedMap.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DefaultValidationReport other = (DefaultValidationReport) obj;
		if (creationTime != other.creationTime)
			return false;
		if (validatedMap == null) {
			if (other.validatedMap != null)
				return false;
		} else if (!validatedMap.equals(other.validatedMap))
			return false;
		return true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Report for:" ).append(validatedMap.getName()).append(" at ")
		  .append(DateFormat.getDateTimeInstance().format(creationTimeAsDate))
		  .append("\n");
		for (IValidationReportItem item: reportItemList) {
			sb.append(item.toString());
		}
		return sb.toString();
	}

}
