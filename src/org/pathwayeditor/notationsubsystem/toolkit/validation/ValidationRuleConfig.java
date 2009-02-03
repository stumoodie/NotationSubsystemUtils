package org.pathwayeditor.notationsubsystem.toolkit.validation;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;



public class ValidationRuleConfig implements IValidationRuleConfig {

	private IValidationRuleDefinition definition;
	private boolean mustBeRun;
	private Object isError;
	
	public ValidationRuleConfig(IValidationRuleDefinition definition) {
		super();
		if(definition == null){
			throw new IllegalArgumentException("Rule definition cannot be null");
		}
		this.definition = definition;
		if(definition.getRuleLevel().equals(RuleLevel.MANDATORY)){
			this.mustBeRun = true;
		}else {
			this.mustBeRun = false;
		}
		this.isError = definition.getDefaultEnforcementLevel().equals(RuleEnforcement.ERROR);
	}

	public IValidationRuleDefinition getValidationRuleDefinition() {
		return definition;
	}
	
	/**
	 * Sorts by ascending rule number
	 */
	public int compareTo(IValidationRuleConfig other) {
		return definition.compareTo(other.getValidationRuleDefinition());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((definition == null) ? 0 : definition.hashCode());
		return result;
	}

	@Override
	/**
	 * Two rule configurations are equal if they share the same rule definition;
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ValidationRuleConfig other = (ValidationRuleConfig) obj;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		return true;
	}
	
	public String toString () {
		StringBuffer sb = new StringBuffer();
		sb.append("Config for: ").append(definition.getRuleNumber()).append("\n")
		.append("Must be run: ").append(mustBeRun).append("\n")
		.append("IsError? :").append(isError);
		return sb.toString();
	}


	public RuleEnforcement getCurrentRuleEnforcement() {
		return definition.getDefaultEnforcementLevel();
	}

	public void setRuleEnforcement(RuleEnforcement ruleEnforcement) {
		if(definition.isValidEnforcement(ruleEnforcement))
			((ValidationRuleDefinition)definition).setRuleEnforcement(ruleEnforcement);
		else
			throw new IllegalArgumentException("This rule enforcement is not valid for the rule level");
	}

}
