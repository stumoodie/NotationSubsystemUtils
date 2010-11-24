package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;


public class LinkTerminusDefinition implements ILinkTerminusDefinition {
	private final LinkTermType endType;
	private final LinkObjectType parentType; 
	private LinkTerminusDefaults linkTerminusDefaults;
	private EnumSet<LinkTermEditableAttributes> editableAttributes=EnumSet.noneOf(LinkTermEditableAttributes.class);
	
	/**
	 * Constructor that takes the "owning" link object type and the end type. These are immutable. 
	 * @param linkObjectType The owning link object type definition.
	 * @param endType Code defining if this is a source or target definition.
	 * @throws IllegalArgumentException if any of the parameters are null. 
	 */
	public LinkTerminusDefinition(LinkObjectType linkObjectType, LinkTermType endType) {
		if(linkObjectType == null || endType == null) throw new IllegalArgumentException("linkObjectType and endType cannot be null");
		this.parentType = linkObjectType;
		this.endType = endType;
		this.linkTerminusDefaults= new LinkTerminusDefaults(this);
	}

	@Override
	public EnumSet<LinkTermEditableAttributes> getEditableAttributes() {
		return editableAttributes;
	}

	public void setEditableAttributes(EnumSet<LinkTermEditableAttributes> in) {
		if(in==null)
			throw new IllegalArgumentException("Shape Attribute Defaults cannot be null");

		this.editableAttributes = in;
	}

	@Override
	public LinkTermType getLinkEndCode() {
		return endType;
	}

	@Override
	public LinkTerminusDefaults getDefaultAttributes() {
		return linkTerminusDefaults;
	}

	@Override
	public LinkObjectType getOwningObjectType() {
		return parentType;
	}	

}
