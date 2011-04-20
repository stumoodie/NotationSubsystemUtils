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

import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;


/**
 * Provides a store of rules and their configurations. This is typically accessed by the validator
 * when generating a validation report.
 * 
 * @author Richard Adams /Stuart Moodie
 */
public interface IValidationRuleStore {
	
	/**
	 * Test if the store contains a rule of a given id.
	 * @param ruleNumber The unique identifier of the rule
	 * @return <code>true</code> if the rule set contains this rule, false otherwise.
	 */
	boolean containsRule (int ruleNumber);
	
	/**
	 * Accessor for a rule of a givenID
	 * @param ruleID The unique rule identifier
	 * @return The {@link IValidationRule} with the requested ID, or <code>null</code> if no rule is found.
	 */
	IValidationRuleDefinition getRuleById(int ruleID);
	
	/**
	 * Accessor for a rule configuration  of a givenID
	 * @param ruleID The unique rule identifier
	 * @return The {@link IValidationRuleConfig} with the requested ID, or <code>null</code> if no rule is found.
	 */
	IValidationRuleConfig getRuleConfigByID(int id);
	
	/**
	 * Obtains <code>Set</code> of configurable rules, i.e., those rules whose execution is not mandatory.
	 * @return An immutable {@link Set}of {@link IValidationRuleConfig}s, not null. 
	 */
	Set<IValidationRuleConfig> getConfigurableRules();
	
	/**
	 * Obtains a <code>Set</code> of all the rule configurations.
	 * @return An immutable {@link Set}of {@link IValidationRuleConfig}s, not null. 
	 */
	Set<IValidationRuleConfig> getAllRuleConfigurations();
		
	/**
	 * Obtains <code>List</code> of all rule definition rules, both mandatory and optional.
	 * @return An immutable {@link List}of {@link IValidationRuleDefinition}s, not null. 
	 */
	Set<IValidationRuleDefinition>getAllRuleDefinitions();
	
}
