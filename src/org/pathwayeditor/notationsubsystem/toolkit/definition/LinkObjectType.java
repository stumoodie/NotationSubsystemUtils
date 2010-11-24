package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;


public final class LinkObjectType extends AbstractObjectType implements ILinkObjectType {
	private final LinkConnectionRules linkConnectionRules;
	private final LinkTerminusDefinition linkSourceEndDefinition;
	private final LinkTerminusDefinition linkTargetEndDefinition;
	private final LinkAttributeDefaults defaultLinkAttributes;
	private EnumSet<LinkEditableAttributes> editableAttributes= EnumSet.noneOf(LinkEditableAttributes.class);
	

	public LinkObjectType(INotationSyntaxService in, int uniqueID, String name){
		super(uniqueID, name, in);
		this.defaultLinkAttributes = new LinkAttributeDefaults();
		this.linkSourceEndDefinition = new LinkTerminusDefinition(this, LinkTermType.SOURCE);
		this.linkTargetEndDefinition = new LinkTerminusDefinition(this, LinkTermType.TARGET);
		this.linkConnectionRules = new LinkConnectionRules(this);
	}

	@Override
	public LinkAttributeDefaults getDefaultAttributes() {
		return defaultLinkAttributes;
	}
	
	@Override
	public EnumSet<LinkEditableAttributes> getEditableAttributes() {
		return editableAttributes ;
	}
	public void setEditableAttributes(EnumSet<LinkEditableAttributes>in){
		editableAttributes=in;
	}

	@Override
	public LinkTerminusDefinition getSourceTerminusDefinition() {
		return linkSourceEndDefinition;
	}

	@Override
	public LinkTerminusDefinition getTargetTerminusDefinition() {
		return linkTargetEndDefinition;
	}
	

	@Override
	public LinkConnectionRules getLinkConnectionRules() {
		return linkConnectionRules;
	}

	@Override
	public IObjectTypeParentingRules getParentingRules() {
		// TODO Auto-generated method stub
		return null;
	}
}
