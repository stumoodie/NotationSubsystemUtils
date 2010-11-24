package org.pathwayeditor.notationsubsystem.toolkit.ndom;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.notationsubsystem.toolkit.validation.IValidationRuleStore;


public interface INDOMValidationService {
	
	/**
	 * @return true if during validation an NDOM was constructed
	 */
	public boolean ndomWasCreated();
	
	
	/**
	 * @param map map to be validated by this class
	 */
	public void setMapToValidate(IModel map);

	/**
	 *  implementers must generate a validation report and (if possible) an NDOM during validation 
	 */
	void validateMap();

	boolean isReadyToValidate();
	
	public IValidationReport getValidationReport();
	
	public INdomModel getNDOM();

	public IValidationRuleStore getRuleStore();

	public boolean hasBeenValidated();

	public IModel getMapBeingValidated();

}
