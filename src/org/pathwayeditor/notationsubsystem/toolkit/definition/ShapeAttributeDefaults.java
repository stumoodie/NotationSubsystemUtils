package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;

public class ShapeAttributeDefaults implements IShapeAttributeDefaults {
//    private final ShapeObjectType shapeObjectType;
    private String shapeType;
    private RGB fillColour;
    private RGB lineColour;
    private LineStyle lineStyle;
    private double lineWidth; 
    private Dimension size;
	private final PropertyDefinitionContainer properties = new PropertyDefinitionContainer();
    
    public ShapeAttributeDefaults() {
//        this.shapeObjectType = objectType;
    }
    
    @Override
	public RGB getFillColour() {
        return this.fillColour;
    }

    @Override
	public RGB getLineColour() {
        return this.lineColour;
    }

    @Override
	public LineStyle getLineStyle() {
        return this.lineStyle;
    }

    @Override
	public double getLineWidth() {
        return this.lineWidth;
    }

    @Override
	public String getShapeDefinition() {
        return this.shapeType;
    }

    @Override
	public Dimension getSize() {
        return this.size;
    }

    public void addPropertyDefinition(IPropertyDefinition defn) {
        this.properties.addDefinition(defn);
    }
    
//    public ShapeObjectType getShapeObjectType() {
//        return shapeObjectType;
//    }

    public void setShapeDefinition(String shapeType) {
        this.shapeType = shapeType;
    }

    public void setFillColour(RGB fillColour) {
        this.fillColour = fillColour;
    }

    public void setLineColour(RGB lineColour) {
        this.lineColour = lineColour;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

	@Override
	public boolean containsPropertyDefinition(String name) {
		return this.properties.containsPropertyDefinition(name);
	}

	@Override
	public IPropertyDefinition getPropertyDefinition(String name) {
		return this.properties.getPropertyDefinition(name);
	}

	@Override
	public int numPropertyDefinitions() {
		return this.properties.numPropertyDefinitions();
	}

	@Override
	public boolean containsPropertyDefinition(IPropertyDefinition defn) {
		return this.properties.containsPropertyDefinition(defn);
	}

	@Override
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return this.properties.propertyDefinitionIterator();
	}

}
