package org.pathwayeditor.contextadapter.toolkit.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

/**
 * Default implementation of a report item
 * @See {@link IValidationReportItem}
 * @author Richard Adams
 *
 */
public class ValidationReportItem implements IValidationReportItem {

	private IDrawingElement invalidDrawingElement;
	private IValidationRuleDefinition ruleDefinition;
	private List<IValidationReportItem> childReports = new ArrayList<IValidationReportItem>();
	private Severity severity;
	private String message;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invalidDrawingElement == null) ? 0 : invalidDrawingElement.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((ruleDefinition == null) ? 0 : ruleDefinition.hashCode());
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
		final ValidationReportItem other = (ValidationReportItem) obj;
		if (invalidDrawingElement == null) {
			if (other.invalidDrawingElement != null)
				return false;
		} else if (!invalidDrawingElement.equals(other.invalidDrawingElement))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (ruleDefinition == null) {
			if (other.ruleDefinition != null)
				return false;
		} else if (!ruleDefinition.equals(other.ruleDefinition))
			return false;
		return true;
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
