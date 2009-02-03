package org.pathwayeditor.notationsubsystem.toolkit.validation;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.notationsubsystem.toolkit.ndom.INdomModel;

/**
 * @author nhanlon
 * Specific contextadapters will use methods of this validation interface; it will NOT be visible to the EPE core
 */
public interface IToolkitValidationService extends INotationValidationService{

	public INdomModel getNDOM();
}
