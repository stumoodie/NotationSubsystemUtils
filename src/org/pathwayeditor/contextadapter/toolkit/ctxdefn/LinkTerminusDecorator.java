package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.constants.LineStyle;
import org.pathwayeditor.businessobjects.constants.ShapeType;
import org.pathwayeditor.contextadapter.publicapi.ILinkTerminusDecorator;


public class LinkTerminusDecorator implements ILinkTerminusDecorator {
	private final LinkEndDefinition linkEndDefinition;
	private ShapeType decoratorType;
	private int red;
	private int blue;
	private int green;
	private LineStyle lineStyle;
	private int lineWidth;
	private int sizeHeight;
	private int sizeWidth;
	private boolean shapeTypeEditable=true;
	private boolean colourEditable=true;
	private boolean lineWidthEditable=true;
	private boolean lineStyleEditable=true;
	private boolean sizeEditable =true;
	
	public LinkTerminusDecorator(LinkEndDefinition linkEndDefinition) {
		this.linkEndDefinition = linkEndDefinition;
	}

	public LinkEndDefinition getParentDefinition() {
		return this.linkEndDefinition;
	}
	
	public ShapeType getDecoratorType() {
		return this.decoratorType;
	}

	public int getColourBlue() {
		return this.blue;
	}

	public int getColourGreen() {
		return this.green;
	}

	public int getColourRed() {
		return this.red;
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public int getSizeHeight() {
		return this.sizeHeight;
	}

	public int getSizeWidth() {
		return this.sizeWidth;
	}

	public void setDecoratorType(ShapeType shapeType) {
		this.decoratorType = shapeType;
	}

	public void setLineProperties(int width,LineStyle style){
		this.lineStyle=style;
		this.lineWidth=width;
	}

	public void setColourProperties(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public void setSize(int width, int height) {
		this.sizeWidth = width;
		this.sizeHeight = height;
	}

	public boolean isShapeTypeEditable() {
		return shapeTypeEditable;
	}

	public void setShapeTypeEditable(boolean shapeTypeEditable) {
		this.shapeTypeEditable = shapeTypeEditable;
	}

	public boolean isColourEditable() {
		return colourEditable;
	}

	public void setColourEditable(boolean colourEditable) {
		this.colourEditable = colourEditable;
	}

	public boolean isLineWidthEditable() {
		return lineWidthEditable;
	}

	public void setLineWidthEditable(boolean lineWidthEditable) {
		this.lineWidthEditable = lineWidthEditable;
	}

	public boolean isLineStyleEditable() {
		return lineStyleEditable;
	}

	public void setLineStyleEditable(boolean lineStyleEditable) {
		this.lineStyleEditable = lineStyleEditable;
	}

	public boolean isSizeEditable() {
		return sizeEditable;
	}

	public void setSizeEditable(boolean sizeEditable) {
		this.sizeEditable = sizeEditable;
	}

}
