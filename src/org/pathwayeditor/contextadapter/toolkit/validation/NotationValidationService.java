package org.pathwayeditor.contextadapter.toolkit.validation;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.contextadapter.toolkit.ndom.INDOMValidationService;

public final class NotationValidationService implements INotationValidationService {

	private INDOMValidationService ndomValidation;
	private INotationSubsystem serviceProvider;
	
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

	public ICanvas getMapBeingValidated() {
		return ndomValidation.getMapBeingValidated();
	}
    
	public IValidationReport getValidationReport() {
		if(!(hasMapBeenValidated())){
			throw new IllegalStateException("Map has not been validated - no report available");
		}
		return ndomValidation.getValidationReport();
	}
    
	public boolean hasMapBeenValidated() {
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
	public void validateMap() {
		if(!isReadyToValidate())
			throw new IllegalStateException("Service not ready to validate");
		this.ndomValidation.validateMap();		
	}
	
	public boolean isReadyToValidate() {
		return ndomValidation.isReadyToValidate();
	}

	public void setMapToValidate(ICanvas mapToValidate) {
		if(mapToValidate==null) throw new IllegalArgumentException("Map to be validated should not be null");
		ndomValidation.setMapToValidate(mapToValidate);
	}


	public boolean isImplemented() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<IValidationRuleDefinition> getRules() {
		return ndomValidation.getRuleStore().getAllRuleDefinitions();
	}

	public INotation getNotation() {
		return serviceProvider.getNotation();
	}

	public INotationSubsystem getNotationSubsystem() {
		return serviceProvider;
	}
}
