package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;

public class ShapeAttributeDefaults implements IShapeAttributeDefaults {
    private final ShapeObjectType shapeObjectType;
    private String shapeType;
    private RGB fillColour;
    private RGB lineColour;
    private LineStyle lineStyle;
    private double lineWidth; 
    private Dimension size;
	private final PropertyDefinitionContainer properties = new PropertyDefinitionContainer();
    
    public ShapeAttributeDefaults(ShapeObjectType objectType) {
        this.shapeObjectType = objectType;
    }
    
    public RGB getFillColour() {
        return this.fillColour;
    }

    public RGB getLineColour() {
        return this.lineColour;
    }

    public LineStyle getLineStyle() {
        return this.lineStyle;
    }

    public double getLineWidth() {
        return this.lineWidth;
    }

    public String getShapeDefinition() {
        return this.shapeType;
    }

    public Dimension getSize() {
        return this.size;
    }

    public void addPropertyDefinition(IPropertyDefinition defn) {
        this.properties.addDefinition(defn);
    }
    
    public ShapeObjectType getShapeObjectType() {
        return shapeObjectType;
    }

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

	public boolean containsPropertyDefinition(String name) {
		return this.properties.containsPropertyDefinition(name);
	}

	public IPropertyDefinition getPropertyDefinition(String name) {
		return this.properties.getPropertyDefinition(name);
	}

	public int numPropertyDefinitions() {
		return this.properties.numPropertyDefinitions();
	}

	public boolean containsPropertyDefinition(IPropertyDefinition defn) {
		return this.properties.containsPropertyDefinition(defn);
	}

	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return this.properties.propertyDefinitionIterator();
	}

}
