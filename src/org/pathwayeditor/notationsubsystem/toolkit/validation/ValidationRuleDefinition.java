package org.pathwayeditor.notationsubsystem.toolkit.validation;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

/**
 * Implementation of rule definition.<br>
 * Equality depends on rule Number and its validationService.
 * 
 * @author Richard Adams
 * 
 */
public class ValidationRuleDefinition implements IValidationRuleDefinition {
	private String desc = "";
	private String detailedDesc = "";
	private final String name;
	private final String ruleCategory;
	private final int ruleNumber;
	private final RuleLevel ruleLevel;
	private final RuleEnforcement ruleEnforcement;

	/**
	 * 
	 * @param validationService
	 *            An {@link IContext}, the validationService to which this rule definition applies.
	 * @param name
	 *            A <code>String</code> short name for the rule
	 * @param ruleCategory
	 *            A <code>String</code> indicating the category of rule (e.g., layout, graph etc)
	 * @param ruleNumber
	 *            A unique <code>int</code> identifier for the rule in its validationService.
	 * @param ruleLevel
	 *            A {@link RuleLevel} Enum.
	 * @param defaultEnforcementLevel The default enforcement for the given rule, must be valid for the run level.
	 * @throws IllegalArgumentException
	 *             if any argument is null
	 * @throws IllegalArgumentException if the enforcement level if not consistent with the run level. 
	 */
	public ValidationRuleDefinition(String name, String ruleCategory, int ruleNumber, RuleLevel ruleLevel, RuleEnforcement defaultEnforcementLevel) {
		if (name == null || ruleCategory == null || ruleLevel == null||defaultEnforcementLevel==null) {
			throw new IllegalArgumentException("No null arguments allowed in constructor");
		}
		if(!isValidEnforcement(ruleLevel, defaultEnforcementLevel)){
			throw new IllegalArgumentException("A mandatory rule must have a default enforecment of ERROR");
		}
		this.name = name;
		this.ruleCategory = ruleCategory;
		this.ruleNumber = ruleNumber;
		this.ruleLevel = ruleLevel;
		this.ruleEnforcement = defaultEnforcementLevel;
	}

	public String getDescription() {
		return desc;
	}
	public String getDetailedDescription() {
		return detailedDesc;
		
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public String getRuleCategory() {
		return ruleCategory;
	}

	public int getRuleNumber() {
		return ruleNumber;
	}

	public RuleLevel getRuleLevel() {
		return ruleLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ruleNumber;
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
		final ValidationRuleDefinition other = (ValidationRuleDefinition) obj;
		if (ruleNumber != other.ruleNumber)
			return false;
		return true;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setDetailedDesc(String detailedDesc) {
		this.detailedDesc = detailedDesc;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID: ").append(ruleNumber).append("CAtegory: ").append(ruleCategory).append("\n").append("Level: ").append(ruleLevel).append("\n").append("Description: ").append(desc);
		return sb.toString();
	}

	/**
	 * Sorts by ascending rule number
	 */
	public int compareTo(IValidationRuleDefinition other) {
		return ruleNumber == other.getRuleNumber() ? 0 : (ruleNumber < other.getRuleNumber() ? -1 : 1);
	}

	public RuleEnforcement getDefaultEnforcementLevel() {
		return ruleEnforcement;
	}

	private static boolean isValidEnforcement(RuleLevel level, RuleEnforcement enforcement){
		return level != null && enforcement != null
			&& (
				(level == IValidationRuleDefinition.RuleLevel.MANDATORY && enforcement == IValidationRuleDefinition.RuleEnforcement.ERROR)
					|| level == IValidationRuleDefinition.RuleLevel.OPTIONAL
				);
	}
	
	public boolean isValidEnforcement(RuleEnforcement ruleEnforcement) {
		return isValidEnforcement(this.ruleLevel, ruleEnforcement);
	}
}
