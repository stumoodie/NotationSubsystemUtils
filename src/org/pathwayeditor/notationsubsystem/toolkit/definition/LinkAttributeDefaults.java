package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;

public class LinkAttributeDefaults implements ILinkAttributeDefaults{
	private String description;
	private String detailedDescription;
	private String url;
	private final Set <IPropertyDefinition> propertyDefinitions = new HashSet<IPropertyDefinition>();
	private ConnectionRouter router;
	private RGB lineColour;
	private LineStyle lineStyle;
	private int lineWidth = 1;
	private String name;
	private final LinkObjectType linkObjectType;
	
	public LinkAttributeDefaults(LinkObjectType linkObjectType) {
		this.linkObjectType = linkObjectType;
	}


	public String getDescription() {
		return description;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public RGB getLineColour() {
		return lineColour;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public String getName() {
		return name;
	}
			
	public void setName(String in){
		name=in;
	}

	public ConnectionRouter getRouter() {
		return router;
	}
	
	public void setRouter(ConnectionRouter in){
		router =in;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String in){
		url=in;
	}

	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return propertyDefinitions.iterator();
	}
	
	public void addPropertyDefinition(IPropertyDefinition in){
		propertyDefinitions.add(in);
	}

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

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}


    public LinkObjectType getLinkObjectType() {
        return linkObjectType;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

}
