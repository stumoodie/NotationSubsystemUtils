package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;

public class ShapeAttributeDefaults implements IShapeAttributeDefaults {
    private final ShapeObjectType shapeObjectType;
    private PrimitiveShapeType shapeType;
    private String name;
    private String description;
    private String detailedDescription;
    private RGB fillColour;
    private RGB lineColour;
    private LineStyle lineStyle;
    private int lineWidth; 
    private Size size;
    private String url;
    private final Set<IPropertyDefinition> properties;
    
    public ShapeAttributeDefaults(ShapeObjectType objectType) {
        this.shapeObjectType = objectType;
        this.properties = new HashSet<IPropertyDefinition>();
    }
    
    public String getDescription() {
        return this.description;
    }

    public String getDetailedDescription() {
        return this.detailedDescription;
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

    public int getLineWidth() {
        return this.lineWidth;
    }

    public String getName() {
        return this.name;
    }

    public PrimitiveShapeType getShapeType() {
        return this.shapeType;
    }

    public Size getSize() {
        return this.size;
    }

    public String getURL() {
        return this.url;
    }

    public boolean containsPropertyDefinition(IPropertyDefinition defn) {
        boolean retVal = false;
        if(defn != null) {
            retVal = this.properties.contains(defn);
        }
        return retVal;
    }
    
    public void addPropertyDefinition(IPropertyDefinition defn) {
        this.properties.add(defn);
    }
    
    public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
        return this.properties.iterator();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShapeObjectType getShapeObjectType() {
        return shapeObjectType;
    }

    public void setShapeType(PrimitiveShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
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

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
