package org.pathwayeditor.notationsubsystem.toolkit.validation;

import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.notationsubsystem.toolkit.ndom.INDOMValidationService;

public final class NotationValidationService implements INotationValidationService {

	private final INDOMValidationService ndomValidation;
	private final INotationSubsystem serviceProvider;
	
	/**
	 * @param provider service provider for the validationService
	 * @param ndomValidation validation for theNDOM for this validationService
	 * @throws IllegalArgumentException if provider or ndomValidator are null
	 */
	public  NotationValidationService(INotationSubsystem provider, INDOMValidationService ndomValidation) {
		this.serviceProvider= provider;
		if(provider==null)
			throw new IllegalArgumentException("provider cannot be null");
		if(ndomValidation==null)
			throw new IllegalArgumentException("ndomValidation cannot be null");
		this.ndomValidation=ndomValidation;
	}
	
	public INotationSubsystem getServiceProvider() {
		return serviceProvider;
	}

	@Override
	public IModel getCanvasBeingValidated() {
		return ndomValidation.getMapBeingValidated();
	}
    
	@Override
	public IValidationReport getValidationReport() {
		if(!(hasBeenValidated())){
			throw new IllegalStateException("Map has not been validated - no report available");
		}
		return ndomValidation.getValidationReport();
	}
    
	@Override
	public boolean hasBeenValidated() {
		return ndomValidation.hasBeenValidated();
	}
	
    /**
     * Template pattern. Subclasses add specialized handling of:
     * <ul>
     * <li> creating an Ndom factory
     * <li> Generating an NDom
     * <li> Handling exceptions thrown by the parser
     * @throw {@link IllegalStateException} if service isReadyToValidate == false
     */
	@Override
	public void validate() {
		if(!isReadyToValidate())
			throw new IllegalStateException("Service not ready to validate");
		this.ndomValidation.validateMap();		
	}
	
	@Override
	public boolean isReadyToValidate() {
		return ndomValidation.isReadyToValidate();
	}

	@Override
	public void setCanvasToValidate(IModel mapToValidate) {
		if(mapToValidate==null) throw new IllegalArgumentException("Map to be validated should not be null");
		ndomValidation.setMapToValidate(mapToValidate);
	}


	@Override
	public boolean isImplemented() {
		return true;
	}

	@Override
	public Set<IValidationRuleDefinition> getRules() {
		return ndomValidation.getRuleStore().getAllRuleDefinitions();
	}

	@Override
	public INotation getNotation() {
		return serviceProvider.getNotation();
	}

	@Override
	public INotationSubsystem getNotationSubsystem() {
		return serviceProvider;
	}
}
