package org.pathwayeditor.notationsubsystem.toolkit.validation;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;



public class ValidationRuleConfig implements IValidationRuleConfig {

	private final IValidationRuleDefinition definition;
	private RuleEnforcement currentEnforcement;
	
	public ValidationRuleConfig(IValidationRuleDefinition definition) {
		super();
		if(definition == null){
			throw new IllegalArgumentException("Rule definition cannot be null");
		}
		this.definition = definition;
		this.currentEnforcement = definition.getDefaultEnforcementLevel();
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((definition == null) ? 0 : definition.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IValidationRuleConfig))
			return false;
		IValidationRuleConfig other = (ValidationRuleConfig) obj;
		if (definition == null) {
			if (other.getValidationRuleDefinition() != null)
				return false;
		} else if (!definition.equals(other.getValidationRuleDefinition()))
			return false;
		return true;
	}

	public String toString () {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append("(").append("definition=");
		sb.append(definition);
		sb.append("currentEnforcement=");
		sb.append(currentEnforcement);
		sb.append(")");
		return sb.toString();
	}


	public RuleEnforcement getCurrentRuleEnforcement() {
		return this.currentEnforcement;
	}

	public void setRuleEnforcement(RuleEnforcement ruleEnforcement) {
		if(!this.definition.isValidEnforcement(ruleEnforcement)) throw new IllegalArgumentException("Enforcement level invalid for the run level of this rule: " + this.definition);
		
		this.currentEnforcement = ruleEnforcement;
	}

}
