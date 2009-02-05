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
	
	public boolean containsRule(int ruleNumber) {
		for(IValidationRuleConfig config: configs){
			if(config.getValidationRuleDefinition().getRuleNumber()==ruleNumber){
				return true;
			}
		}
		return false;
	}
	
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
	public Set<IValidationRuleConfig> getConfigurableRules() {
		Set<IValidationRuleConfig> configurableRules = new HashSet<IValidationRuleConfig>();
		for(IValidationRuleConfig config: configs) {
			if(!(config.getValidationRuleDefinition().getRuleLevel().equals(RuleLevel.MANDATORY))){
				configurableRules.add(config);
			}
		}
		return Collections.unmodifiableSet(configurableRules);
	}
	
	public Set<IValidationRuleConfig> getAllRuleConfigurations() {
		return Collections.unmodifiableSet(configs);
	}
	
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
