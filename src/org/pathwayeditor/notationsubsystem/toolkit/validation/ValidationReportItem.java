package org.pathwayeditor.notationsubsystem.toolkit.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

/**
 * Default implementation of a report item
 * @See {@link IValidationReportItem}
 * @author Richard Adams
 *
 */
public class ValidationReportItem implements IValidationReportItem {

	private final IDrawingElement invalidDrawingElement;
	private final IValidationRuleDefinition ruleDefinition;
	private final List<IValidationReportItem> childReports = new ArrayList<IValidationReportItem>();
	private final Severity severity;
	private final String message;
	
	public ValidationReportItem(IValidationRuleDefinition ruleDefinition, Severity severity, String message) {
		this(null, ruleDefinition, severity, message);
	}
		
	public ValidationReportItem(IDrawingElement invalidDrawingElement,
			                    IValidationRuleDefinition ruleDefinition, 
			                     Severity severity,
			                     String message) {
		if(ruleDefinition == null || severity ==null || message ==null){
			throw new IllegalArgumentException("Rule definition must not be null");
		}
		this.invalidDrawingElement = invalidDrawingElement;
		this.ruleDefinition = ruleDefinition;
		this.severity=severity;
		this.message = message;
	}

	public IValidationRuleDefinition getBrokenRule() {
		return ruleDefinition;
	}

	/**
	 * @param childReportItem An {@link IValidationReportItem}
	 * @return <code>true</code> if the child report item is added to this report item.
	 */
	public boolean addChildReportItem(IValidationReportItem childReportItem){
		return childReports.add(childReportItem);
	}
	
	public List<IValidationReportItem> getChildReports() {
		return Collections.unmodifiableList(childReports);
	}
	
	public IDrawingElement getInvalidObject() {
		return invalidDrawingElement;
	}

	public Severity getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}
	
	public String toString () {
		StringBuffer sb = new StringBuffer();
		sb.append("Rule id: ").append(ruleDefinition.getRuleNumber()).append("\n")
		  .append("Message: ").append(message)
		  .append("Severity:").append(severity);
		if(invalidDrawingElement !=null){
			sb.append("invalidDrawingNode: ").append(invalidDrawingElement.getAttribute().getCreationSerial());
		}
		return sb.toString();
	}

}
