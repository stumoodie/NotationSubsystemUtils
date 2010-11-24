package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectTypeParentingRules;

public class LabelParentingRules extends ObjectParentingRules<ILabelObjectType> implements ILabelObjectTypeParentingRules {

	public LabelParentingRules(ILabelObjectType objectType) {
		super(objectType);
	}

}
