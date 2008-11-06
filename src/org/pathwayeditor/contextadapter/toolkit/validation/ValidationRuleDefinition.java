package org.pathwayeditor.contextadapter.toolkit.validation;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

/**
 * Implementation of rule definition.<br>
 * Equality depends on rule Number and its validationService.
 * 
 * @author Richard Adams
 * 
 */
public class ValidationRuleDefinition implements IValidationRuleDefinition {

	private INotationSubsystem context;
	private String desc, detailedDesc, name, ruleCategory;
	private int ruleNumber;
	private RuleLevel ruleLevel;
	private RuleEnforcement ruleEnforcement;
	private INotationValidationService validationService;

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
	 * @throws IllegalArgumentException
	 *             if any argument is null
	 */
	public ValidationRuleDefinition(INotationValidationService validationService, String name, String ruleCategory, int ruleNumber, RuleLevel ruleLevel, RuleEnforcement enforcement) {
		if (validationService== null || name == null || ruleCategory == null || ruleLevel == null||enforcement==null) {
			throw new IllegalArgumentException("No null arguments allowed in constructor");
		}
		this.context = validationService.getNotationSubsystem();
		this.validationService=validationService;
		this.name = name;
		this.ruleCategory = ruleCategory;
		this.ruleNumber = ruleNumber;
		this.ruleLevel = ruleLevel;
		this.ruleEnforcement = enforcement;
	}

	public void setRuleEnforcement(RuleEnforcement in) {
		ruleEnforcement = in;
	}

	public String getDescription() {
		return desc == null ? "" : desc;
	}

	public String getDetailedDescription() {
		return detailedDesc == null ? "" : detailedDesc;
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
		result = prime * result + ((context == null) ? 0 : context.hashCode());
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
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (ruleNumber != other.ruleNumber)
			return false;
		return true;
	}

	void setDesc(String desc) {
		this.desc = desc;
	}

	void setDetailedDesc(String detailedDesc) {
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
		return new Integer(ruleNumber).compareTo(new Integer(other.getRuleNumber()));
	}

	public RuleEnforcement getDefaultEnforcementLevel() {
		if (isValidEnforcement(ruleEnforcement))
			return ruleEnforcement;
		throw new RuntimeException("Default rule enforcement is invalid for rule level");
	}

	public INotationValidationService getValidationService() {
		return validationService;
	}

	public boolean isValidEnforcement(RuleEnforcement ruleEnforecement) {
		if (ruleLevel.equals(RuleLevel.MANDATORY) && ruleEnforecement.equals(RuleEnforcement.ERROR))
			return true;
		else if (ruleLevel.equals(RuleLevel.MANDATORY))
			return false;
		else
			// optional rule level is valid with any type of enforcement
			return true;
	}
}
