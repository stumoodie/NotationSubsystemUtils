package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

public class LabelObjectType extends AbstractObjectType implements ILabelObjectType {
	private final LabelAttributeDefaults defaultLabelAttributes;
	private boolean isAlwaysDisplayed = false;
	private final LabelParentingRules parentingRules;
	
	public LabelObjectType(INotationSyntaxService in, int uniqueID, String name){
		super(uniqueID, name, in);
		this.defaultLabelAttributes = new LabelAttributeDefaults();
		this.parentingRules = new LabelParentingRules(this);
	}
	
	@Override
	public LabelParentingRules getParentingRules(){
		return this.parentingRules;
	}

	@Override
	public LabelAttributeDefaults getDefaultAttributes() {
		return this.defaultLabelAttributes;
	}

	@Override
	public boolean isAlwaysDisplayed() {
		return this.isAlwaysDisplayed;
	}

	public void setAlwaysDisplayed(boolean isAlwaysDisplayed) {
		this.isAlwaysDisplayed = isAlwaysDisplayed;
	}

}
