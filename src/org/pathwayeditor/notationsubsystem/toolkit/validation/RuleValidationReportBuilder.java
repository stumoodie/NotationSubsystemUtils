package org.pathwayeditor.notationsubsystem.toolkit.validation;

import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
/**
 * Builds a report.
 * Can be in 3 states:
 * <ul>
 * <li> Ready to validate
 * <li> Validating
 * <li>Completed
 * </ul>
 * When the first call to setRuleFailed() or setRulePassed() is called by the validator,
 * a copy of the rule configuration is taken. This is to prevent
 * @author Richard Adams/Stuart Moodie
 *
 */
 public class RuleValidationReportBuilder implements IRuleValidationReportBuilder {
     int state;
    // states
    static final int READY_TO_VALIDATE = 1;
    static final int VALIDATING        = 2;
    static final int COMPLETED         = 3;
    
    private final IValidationRuleStore validationRuleStore;
    private final ICanvas mapToValidate;
    private ValidationReport currValidationReport;
    private Map<Integer, Boolean> checkedRules = new HashMap<Integer, Boolean>();

    public RuleValidationReportBuilder(IValidationRuleStore store, ICanvas map) {
    	if(map == null || store == null){
    		throw new IllegalArgumentException("Arguments must not be null");
    	}
    	this.validationRuleStore=store;
    	this.mapToValidate=map;
    	this.currValidationReport = new ValidationReport(this.mapToValidate);
    	state=READY_TO_VALIDATE;
    }
    
	public void createValidationReport() {
		if(!isValidating() && !isComplete() && !isReadyToValidate()){
			throw new IllegalStateException("");
		}
		
		state=COMPLETED;
		
	}
	
	public IValidationRuleStore getRuleStore() {
		return validationRuleStore;
	}

	public IValidationReport getValidationReport() {
		if(getState()!=COMPLETED){
			throw new IllegalStateException("CAnnot access report until validation completed");
		}
		return this.currValidationReport;
	}

	public boolean isComplete() {
		return getState()==COMPLETED;
	}

	public boolean isReadyToValidate() {
		return getState()==READY_TO_VALIDATE;
	}

	public boolean isValidating() {
		return getState()==VALIDATING;
	}

	public void reset() {
		if(state==VALIDATING){
			throw new IllegalStateException("Cannot clear during validation");
		}
    	this.currValidationReport = new ValidationReport(this.mapToValidate);
		checkedRules.clear();
		state=READY_TO_VALIDATE;
	}

	public void setRuleFailed(IDrawingElement inValidObject, int ruleId, String message) {
		checkRuleDefinition(ruleId);
		state=VALIDATING;
		IValidationRuleConfig config = validationRuleStore.getRuleConfigByID(ruleId);
		// if set to ignore then do nothing
		if(config.getCurrentRuleEnforcement() != RuleEnforcement.IGNORE){
			IValidationReportItem.Severity severity = (config.getCurrentRuleEnforcement() == RuleEnforcement.ERROR) ?
					IValidationReportItem.Severity.ERROR : IValidationReportItem.Severity.WARNING;
			this.currValidationReport.addReportItem(new ValidationReportItem(inValidObject, config.getValidationRuleDefinition(), severity, message));
		}
	}


	private void checkRuleDefinition(int ruleId) {
		if(getState()==COMPLETED){
			throw new IllegalStateException("validation completed");
		}
		if(!validationRuleStore.containsRule(ruleId)){
			throw new IllegalArgumentException("Rule not in rule store");
		}
	}

	public void setRulePassed(int ruleId) {
		state=VALIDATING;
	}
	int getState() {
		return state;
	}

}
