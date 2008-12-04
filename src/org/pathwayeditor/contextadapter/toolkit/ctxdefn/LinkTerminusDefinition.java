package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType.EditableShapeAttributes;


public class LinkTerminusDefinition implements ILinkTerminusDefinition {
	private final LinkTermType endType;
	private final ILinkObjectType parentType; 
	private ILinkTerminusDefaults linkTerminusDefaults;
	private EnumSet<LinkTermEditableAttributes> editableAttributes=EnumSet.noneOf(LinkTermEditableAttributes.class);
	
	/**
	 * Constructor that takes the "owning" link object type and the end type. These are immutable. 
	 * @param linkObjectType The owning link object type definition.
	 * @param endType Code defining if this is a source or target definition.
	 * @throws IllegalArgumentException if any of the parameters are null. 
	 */
	public LinkTerminusDefinition(ILinkObjectType linkObjectType, LinkTermType endType, ILinkTerminusDefaults linkTerminusDefaults) {
		if(linkObjectType == null || endType == null) throw new IllegalArgumentException("linkObjectType and endType cannot be null");
		this.parentType = linkObjectType;
		this.endType = endType;
		if(linkTerminusDefaults==null)
			throw new IllegalArgumentException("link terminus defaults cannot be null");
		this.linkTerminusDefaults=linkTerminusDefaults;
	}

	public EnumSet<LinkTermEditableAttributes> getEditableAttributes() {
		return editableAttributes;
	}

	public void setEditableAttributes(
			EnumSet<LinkTermEditableAttributes> in) {
		if(in==null)
			throw new IllegalArgumentException("Shape Attribute Defaults cannot be null");

		this.editableAttributes = editableAttributes;
	}

	public LinkTermType getLinkEndCode() {
		return endType;
	}

	public ILinkTerminusDefaults getLinkTerminusDefaults() {
		return linkTerminusDefaults;
	}

	public ILinkObjectType getOwningObjectType() {
		return parentType;
	}	

}
