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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;



/**
 * Used
 * @author Richard Adams /Stuart Moodie
 *
 */
public interface IRuleValidationReportBuilder {
	
	/**
	 * Tests whether the builder is ready to start creating a report.
	 * @return <code>true</code> if the builder has access to an instantiated {@link IValidationRuleStore} and 
	 * following a call to reset(), <code>false</code>otherwise.
	 */
	boolean isReadyToValidate();
	
	
	/**
	 * Resets the builder to a state such that isReadyToValidate() will return <code>true</code>.
	 * @throws IllegalStateException if this method is called when isValidating() == <code>true</code>.
	 */
	void reset ();
	
	
	/**
	 * Records a rule as having failed.<br>
	 * The <code>ruleDefinition</code> parameter must be a rule found in the {@link IValidationRuleStore}.<br>
	 * The first  time this method is called by a  client, isValidating() should return <code>true</code> and
	 * any changes by external clients to the rule configuration should have no effect in this validation run.
	 * @param inValidObject An {@link IDrawingElement}, possibly null
	 * @param ruleNum the number that uniquely identifies the rule.
	 * @param message A non-null<code>String</code>.
	 * @throws IllegalStateException if the builder is not in a state where isReadyToValidate() or isValidating() == true.
	 * @throws IllegalArgumentException if the <code>ruleDefinition</code>parameter is not in the IValidationRuleStore().
	 */
	void setRuleFailed(ICompoundGraphElement inValidObject, int ruleNum, String message);
	
	// do we need this? A rule may be tested several times; if it passes once it can still fail later.
	// This is not used to generate the validation report.
	// How about setting all rules as passed unless failed?
	// Then if they all pass we just have an empty list of report items.
	
	// SLM - Yes. We need to know that the validator has considered all the rules. All rules should fail if not explicitly
	//        passed (fail safe). That way if we add in a new rule then the validation will fail if we forget to 
	//        check it. If everything is passed by default we could easily introduce a bug where invalid maps are being passed as valid
	//        by adding in a new rule and forgetting to write validation code for it.
	//		  In addition, it may assist debugging as you would be able to catch when a specific rule has being considered by the validator.
	
	// RA - now does nothing apart from move builder to isValidating==true state. No longer records a rule as passed.
	/**
	 * @param ruleNum the number that uniquely identifies the rule.
	 * @throws IllegalStateException if the builder is not in a state where isReadyToValidate() or isValidating() == true.
	 * @throws IllegalArgumentException if the <code>ruleDefinition</code>parameter is not in the IValidationRuleStore().
	 */
	void setRulePassed(int ruleNum);
	
	/**
	 * Accessor for the <code>IValidationRuleStore</code>
	 * @return A non-null {@link IValidationRuleStore}.
	 */
	IValidationRuleStore getRuleStore();
	
	/**
	 * Generates an {@link IValidationReport} following validation.<br>
	 * Following the creation of a validation report, isComplete() will return <code>true</code>.
	 * @throws IllegalStateException if isValidating()== false;
	 */
	void createValidationReport();
	
	/**
	 * State test for the builder.  
	 * @return <code>true</code> if a validation report has been created and is available, <code>false</code>otherwise.
	 */
	boolean isComplete();
	
	
	/**
	 * Return <code>true</code> if validation is in progress. This implies that validation has begin but a 
	 *  validation report is not yet available
	 * @return <code>true</code> if validation is in progress.
	 */
	boolean isValidating();
	
	/**
	 * Retrieves the generated validation report. 
	 * @return An {@link IValidationReport}
	 * @throws IllegalStateException if isComplete() == <code>false</code>.
	 */
	IValidationReport getValidationReport();
	
	

}
