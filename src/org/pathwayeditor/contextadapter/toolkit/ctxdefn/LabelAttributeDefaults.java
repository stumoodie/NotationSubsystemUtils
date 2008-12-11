package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

public class LabelAttributeDefaults implements ILabelAttributeDefaults {
    private final AbstractPropertyDefinition propertyDefinition;
	private RGB fillColour = new RGB(255, 255, 255);
	private RGB lineColour = new RGB(0, 0, 0);
	private LineStyle lineStyle = LineStyle.SOLID;
	private int lineWidth = 1;
	private Size size= new Size(10,10); 

	public LabelAttributeDefaults(AbstractPropertyDefinition propDefn) {
	    this.propertyDefinition = propDefn;
	}

	public RGB getFillColour() {
		return fillColour;
	}

	public void setFillColour(RGB in) {
		fillColour = in;
	}

	public RGB getLineColour() {
		return lineColour;
	}

	public void setLineColour(RGB in) {
		lineColour = in;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size in) {
		this.size = in;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(LineStyle in) {
		lineStyle = in;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(int width) {
		lineWidth = width;
	}

    public AbstractPropertyDefinition getPropertyDefinition() {
        return propertyDefinition;
    }

}
