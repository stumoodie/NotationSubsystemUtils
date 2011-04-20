/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package org.pathwayeditor.notationsubsystem.toolkit.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

/**
 * Default implementation of a report item
 * @See {@link IValidationReportItem}
 * @author Richard Adams
 *
 */
public class ValidationReportItem implements IValidationReportItem {

	private final ICompoundGraphElement invalidDrawingElement;
	private final IValidationRuleDefinition ruleDefinition;
	private final List<IValidationReportItem> childReports = new ArrayList<IValidationReportItem>();
	private final Severity severity;
	private final String message;
	
	public ValidationReportItem(IValidationRuleDefinition ruleDefinition, Severity severity, String message) {
		this(null, ruleDefinition, severity, message);
	}
		
	public ValidationReportItem(ICompoundGraphElement invalidDrawingElement,
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

	@Override
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
	
	@Override
	public List<IValidationReportItem> getChildReports() {
		return Collections.unmodifiableList(childReports);
	}
	
	@Override
	public ICompoundGraphElement getInvalidObject() {
		return invalidDrawingElement;
	}

	@Override
	public Severity getSeverity() {
		return severity;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString () {
		StringBuffer sb = new StringBuffer();
		sb.append("Rule id: ").append(ruleDefinition.getRuleNumber()).append("\n")
		  .append("Message: ").append(message)
		  .append("Severity:").append(severity);
		if(invalidDrawingElement !=null){
			sb.append("invalidDrawingNode: ").append(invalidDrawingElement);
		}
		return sb.toString();
	}

}
