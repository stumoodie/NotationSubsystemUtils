package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.constants.ArrowheadStyle;
import org.pathwayeditor.contextadapter.publicapi.ILinkEndDecorator;


public final class LinkEndDecorator implements ILinkEndDecorator {
	private final LinkEndDefinition linkEndDefinition;
	private int decoratorHeight;
	private ArrowheadStyle decoratorType;
	private int decoratorWidth;
	private boolean typeEditable;
	private boolean sizeEditable;
	
	
	public LinkEndDecorator(LinkEndDefinition linkEndDefinition){
		this.linkEndDefinition = linkEndDefinition;
	}
	
	public int getDecoratorHeight() {
		return this.decoratorHeight;
	}

	public ArrowheadStyle getDecoratorType() {
		return this.decoratorType;
	}

	public int getDecoratorWidth() {
		return this.decoratorWidth;
	}

	public void setDecorator(ArrowheadStyle decoratorStyle, int width, int height) {
		this.decoratorHeight = height;
		this.decoratorType = decoratorStyle;
		this.decoratorWidth = width;
	}

	public LinkEndDefinition getParentDefinition() {
		return this.linkEndDefinition;
	}

	public boolean isDecoratorSizeEditable() {
		return this.sizeEditable;
	}

	public boolean isDecoratorTypeEditable() {
		return this.typeEditable;
	}

	public void setDecoratorTypeEditable(boolean typeEditable) {
		this.typeEditable = typeEditable;
	}

	public void setDecoratorSizeEditable(boolean sizeEditable) {
		this.sizeEditable = sizeEditable;
	}

}
