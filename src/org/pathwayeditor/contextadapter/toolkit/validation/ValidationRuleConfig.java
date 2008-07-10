package org.pathwayeditor.contextadapter.toolkit.validation;

import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition.RuleLevel;



public class ValidationRuleConfig implements IValidationRuleConfig {

	private IValidationRuleDefinition definition;
	private boolean mustBeRun;
	private boolean isError;
	public ValidationRuleConfig(IValidationRuleDefinition definition, boolean mustBeRun, boolean isError) {
		super();
		if(definition == null){
			throw new IllegalArgumentException("Rule definition cannot be null");
		}
		this.definition = definition;
		if(definition.getRuleLevel().equals(RuleLevel.MANDATORY)){
			this.mustBeRun = true;
		}else {
			this.mustBeRun = mustBeRun;
		}
		
		this.isError = isError;
	}

	public IValidationRuleDefinition getValidationRuleDefinition() {
		return definition;
	}

	public boolean isErrorRule() {
		return ruleIsMandatory() || ruleIsOptional() || (ruleisGuideline() && isError ==true);
	}

	private boolean ruleisGuideline() {
		return definition.getRuleLevel().equals(RuleLevel.GUIDELINE);
	}

	private boolean ruleIsOptional() {
		return definition.getRuleLevel().equals(RuleLevel.OPTIONAL);
	}

	private boolean ruleIsMandatory() {
		return definition.getRuleLevel().equals(RuleLevel.MANDATORY);
	}

	public boolean isWarningRule() {
		return !isErrorRule();
	}

	public boolean mustBeRun() {
		return mustBeRun;
	}

	public void promoteToError(boolean isError) {
	   if(isError == false && (ruleIsMandatory() ||ruleIsOptional())){
		   return;
	   } else {
		   this.isError = isError;
	   }
	}
    
	/**
	 * If
	 */
	public void setMustBeRun(boolean mustBeRun) {
		if(ruleIsMandatory()){
			return;
		}
		this.mustBeRun=mustBeRun;
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

	/**
	 * Compares by rule definition rule number
	 */
	public int compareTo(IValidationRuleConfig other) {
		return definition.compareTo(other.getValidationRuleDefinition());
	}

}
