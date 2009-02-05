package org.pathwayeditor.notationsubsystem.toolkit.validation;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;

public class ValidationReport implements IValidationReport {
	private final List<IValidationReportItem> reportItemList;
	private final ICanvas canvas;
	private final Date creationTime;
	
	/**
	 * @param map A non-null IMap to which this validation report refers.
	 * @param itemList A non-null <code>List</code> of {@link IValidationReportItem}s. 
	 * @throws IllegalArgumentException if either parameter is null
	 */
	public ValidationReport(ICanvas canvas) {
		if(canvas == null){
			throw new IllegalArgumentException("Constructor arguments must not be null");
		}
		creationTime = new Date();
		this.canvas=canvas;
		reportItemList = new LinkedList<IValidationReportItem>();
	}
	
	
	public void addReportItem(IValidationReportItem reportItem){
		this.reportItemList.add(reportItem);
	}
	
	public ICanvas getCanvas() {
		return canvas;
	}

	public List<IValidationReportItem> getValidationReportItems() {
	     return Collections.unmodifiableList(reportItemList);
	}
    
	public Date getValidationTime() {
		return new Date(creationTime.getTime());
	}

	public boolean hasWarnings() {
		for(IValidationReportItem item: reportItemList){
			if(item.getSeverity().equals(IValidationReportItem.Severity.WARNING)){
				return true;
			}
		}
		return false;
	}

	public boolean isValid() {
		for(IValidationReportItem item: reportItemList){
			if(item.getSeverity().equals(IValidationReportItem.Severity.ERROR) ){
				return false;
			}
		}
		return true;
	}


	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append("(");
		sb.append("canvas=");
		sb.append(this.canvas);
		sb.append("creationDate=");
		sb.append(DateFormat.getDateTimeInstance().format(this.creationTime));
		sb.append("Number or report items=");
		sb.append(this.reportItemList.size());
		sb.append(")");
		return sb.toString();
	}

}
