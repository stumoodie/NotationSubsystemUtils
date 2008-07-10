package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.contextadapter.publicapi.ILinkEndDefinition;
import org.pathwayeditor.contextadapter.publicapi.ILinkObjectType;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinitionFilter;


public class LinkEndDefinition implements ILinkEndDefinition {
	private final LinkEndType endType;
	private final PropertyDefinitionFilter properties;
	private final LinkTerminusDecorator terminusDecorator;
	private final LinkEndDecorator endDecorator;
	private final ILinkObjectType parentType; 
	private int offset=0;
	private boolean offsetEditable;
	
	/**
	 * Constructor that takes the "owning" link object type and the end type. These are immutable. 
	 * @param linkObjectType The owning link object type definition.
	 * @param endType Code defining if this is a source or target definition.
	 * @throws IllegalArgumentException if any of the parameters are null. 
	 */
	public LinkEndDefinition(LinkObjectType linkObjectType, LinkEndType endType) {
		if(linkObjectType == null || endType == null) throw new IllegalArgumentException("linkObjectType and endType cannot be null");
		this.parentType = linkObjectType;
		this.endType = endType;
		this.properties = new PropertyDefinitionFilter(this.parentType);
		this.terminusDecorator = new LinkTerminusDecorator(this);
		this.endDecorator = new LinkEndDecorator(this);
	}

	/**
	 * Add a new property to the link end definition.
	 * @param newProperty
	 * @throws IllegalArgumentException if newProperty is null. 
	 */
	public void addProperty(IPropertyDefinition newProperty) {
		if(newProperty == null) throw new IllegalArgumentException("newProperty cannot be null");
		this.properties.add(newProperty);
	}

	public LinkEndType getLinkEndCode() {
		return this.endType;
	}

	public int getOffset() {
		return this.offset;
	}

	public LinkTerminusDecorator getTerminusDecorator() {
		return this.terminusDecorator;
	}

	public LinkEndDecorator getLinkEndDecorator() {
		return this.endDecorator;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public ILinkObjectType getOwningType() {
		return this.parentType;
	}

	public IPropertyDefinitionFilter getPropertiesFilter() {
		return this.properties;
	}

	public boolean isOffsetEditable() {
		return this.offsetEditable;
	}

	public void setOffsetEditable(boolean offsetEditable) {
		this.offsetEditable = offsetEditable;
	}

}
