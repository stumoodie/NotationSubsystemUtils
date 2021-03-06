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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;


/**
 * Default implementation of a <code>IValidationRuleStore</code>.<br>
 * @author Richard Adams/Stuart Moodie 
 *
 */
public class RuleStore implements IValidationRuleStore {
	private final Set<IValidationRuleConfig> configs;
   
	public RuleStore() {
		this.configs = new HashSet<IValidationRuleConfig>();
	}
	
	public void addConfiguredRule(IValidationRuleConfig newRule){
		if(this.configs.contains(newRule)) throw new IllegalArgumentException("Rule is already contained in the rule store.");
		
		this.configs.add(newRule);
	}
	
	@Override
	public boolean containsRule(int ruleNumber) {
		for(IValidationRuleConfig config: configs){
			if(config.getValidationRuleDefinition().getRuleNumber()==ruleNumber){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Set<IValidationRuleDefinition> getAllRuleDefinitions() {
		Set <IValidationRuleDefinition> definitions = new HashSet<IValidationRuleDefinition>();
		for(IValidationRuleConfig config: configs){
			definitions.add(config.getValidationRuleDefinition());
		}
		return definitions;
	}
    
	/**
	 * Return those configs whose type is not Mandatory
	 */
	@Override
	public Set<IValidationRuleConfig> getConfigurableRules() {
		Set<IValidationRuleConfig> configurableRules = new HashSet<IValidationRuleConfig>();
		for(IValidationRuleConfig config: configs) {
			if(!(config.getValidationRuleDefinition().getRuleLevel().equals(RuleLevel.MANDATORY))){
				configurableRules.add(config);
			}
		}
		return Collections.unmodifiableSet(configurableRules);
	}
	
	@Override
	public Set<IValidationRuleConfig> getAllRuleConfigurations() {
		return Collections.unmodifiableSet(configs);
	}
	
	@Override
	public IValidationRuleDefinition getRuleById(int ruleNumber) {
		IValidationRuleDefinition retVal = null;
		for(IValidationRuleConfig config: configs){
			if(config.getValidationRuleDefinition().getRuleNumber()==ruleNumber){
				retVal = config.getValidationRuleDefinition();
				break;
			}
		}

		return retVal;
	}

	@Override
	public IValidationRuleConfig getRuleConfigByID(int ruleNumber) {
		IValidationRuleConfig retVal = null;
		for(IValidationRuleConfig config: configs){
			if(config.getValidationRuleDefinition().getRuleNumber()==ruleNumber){
				retVal = config;
				break;
			}
		}
		
		return retVal;
	}
}
