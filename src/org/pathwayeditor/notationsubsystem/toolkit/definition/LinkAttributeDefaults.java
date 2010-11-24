package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;

public class LinkAttributeDefaults implements ILinkAttributeDefaults{
	private final Set <IPropertyDefinition> propertyDefinitions = new HashSet<IPropertyDefinition>();
	private RGB lineColour;
	private LineStyle lineStyle;
	private double lineWidth = 1.0;
//	private final LinkObjectType linkObjectType;
	
	public LinkAttributeDefaults() {
//		this.linkObjectType = linkObjectType;
	}


	@Override
	public RGB getLineColour() {
		return lineColour;
	}

	@Override
	public LineStyle getLineStyle() {
		return lineStyle;
	}

	@Override
	public double getLineWidth() {
		return lineWidth;
	}

	@Override
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return propertyDefinitions.iterator();
	}
	
	public void addPropertyDefinition(IPropertyDefinition in){
		propertyDefinitions.add(in);
	}

    @Override
	public boolean containsPropertyDefinition(IPropertyDefinition defn) {
        boolean retVal = false;
        if(defn != null) {
            retVal = this.propertyDefinitions.contains(defn);
        }
        return retVal;
    }
    
	public void setLineColour(RGB lineColour) {
		this.lineColour = lineColour;
	}

	public  void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}


//    public LinkObjectType getLinkObjectType() {
//        return linkObjectType;
//    }


	private IPropertyDefinition findPropertyDefn(String name){
		IPropertyDefinition retVal = null;
		for(IPropertyDefinition objectType : this.propertyDefinitions){
			if(objectType.getName().equals(name)){
				retVal = objectType;
				break;
			}
		}
		return retVal;
	}

	@Override
	public boolean containsPropertyDefinition(String name) {
		return findPropertyDefn(name) != null;
	}


	@Override
	public IPropertyDefinition getPropertyDefinition(String name) {
		IPropertyDefinition retVal = findPropertyDefn(name);
		if(retVal == null){
			throw new IllegalArgumentException("No property definition for: " + name);
		}
		return retVal;
	}


	@Override
	public int numPropertyDefinitions() {
		return this.propertyDefinitions.size();
	}

}
