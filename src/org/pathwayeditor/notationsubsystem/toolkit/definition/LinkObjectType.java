package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;


public final class LinkObjectType extends AbstractObjectType implements ILinkObjectType {
	private final LinkConnectionRules linkConnectionRules;
	private final LinkTerminusDefinition linkSourceEndDefinition;
	private final LinkTerminusDefinition linkTargetEndDefinition;
	private final LinkAttributeDefaults defaultLinkAttributes;
	private EnumSet<LinkEditableAttributes> editableAttributes= EnumSet.noneOf(LinkEditableAttributes.class);
	

	public LinkObjectType(INotationSyntaxService in, int uniqueID, String name){
		super(uniqueID, name, in);
		this.defaultLinkAttributes = new LinkAttributeDefaults(this);
		this.linkSourceEndDefinition = new LinkTerminusDefinition(this, LinkTermType.SOURCE);
		this.linkTargetEndDefinition = new LinkTerminusDefinition(this, LinkTermType.TARGET);
		this.linkConnectionRules = new LinkConnectionRules(this);
	}

	public LinkAttributeDefaults getDefaultLinkAttributes() {
		return defaultLinkAttributes;
	}
	
	public EnumSet<LinkEditableAttributes> getEditiableAttributes() {
		return editableAttributes ;
	}
	public void setEditableAttributes(EnumSet<LinkEditableAttributes>in){
		editableAttributes=in;
	}

	public LinkTerminusDefinition getSourceTerminusDefinition() {
		return linkSourceEndDefinition;
	}

	public LinkTerminusDefinition getTargetTerminusDefinition() {
		return linkTargetEndDefinition;
	}
	

	public LinkConnectionRules getLinkConnectionRules() {
		return linkConnectionRules;
	}
}
