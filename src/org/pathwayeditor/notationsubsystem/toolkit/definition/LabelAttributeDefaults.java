package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LabelLocationPolicy;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;

public class LabelAttributeDefaults implements ILabelAttributeDefaults {
	private RGB fillColour = new RGB(255, 255, 255);
	private RGB lineColour = new RGB(0, 0, 0);
	private LineStyle lineStyle = LineStyle.SOLID;
	private double lineWidth = 1;
	private LabelLocationPolicy labelLocationPolicy = LabelLocationPolicy.CENTRE;
	private boolean noFill = true;
	private boolean noBorder = true;
	private Dimension minimumSize;

	public LabelAttributeDefaults() {
	}

	@Override
	public RGB getFillColour() {
		return fillColour;
	}

	public void setFillColour(RGB in) {
		fillColour = in;
	}

	@Override
	public RGB getLineColour() {
		return lineColour;
	}

	public void setLineColour(RGB in) {
		lineColour = in;
	}

	@Override
	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(LineStyle in) {
		lineStyle = in;
	}

	@Override
	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double width) {
		lineWidth = width;
	}

    public void setLabelLocationPolicy(LabelLocationPolicy labelLocationPolicy){
    	this.labelLocationPolicy = labelLocationPolicy;
    }
    
	@Override
	public LabelLocationPolicy getLabelLocationPolicy() {
		return this.labelLocationPolicy;
	}

	public void setMinimumSize(Dimension newMinSize){
		this.minimumSize = newMinSize;
	}
	
	@Override
	public Dimension getMinimumSize() {
		return this.minimumSize;
	}

	public void setNoBorder(boolean noBorder){
		this.noBorder = noBorder;
	}
	
	@Override
	public boolean hasNoBorder() {
		return this.noBorder;
	}

	public void setNoFill(boolean noFill){
		this.noFill = noFill;
	}
	
	@Override
	public boolean hasNoFill() {
		return this.noFill;
	}

}
