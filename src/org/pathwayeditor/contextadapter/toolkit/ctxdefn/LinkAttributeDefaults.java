package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

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
	private Set <IPropertyDefinition> propertyDefinitions = new HashSet<IPropertyDefinition>();
	private ConnectionRouter router;
	private RGB lineColour = new RGB(0, 0, 0);
	private LineStyle lineStyle = LineStyle.SOLID;
	private int lineWidth = 1;
	private String name;
	
	public LinkAttributeDefaults(String description, String detailedDescription, String url, Set<IPropertyDefinition> propertyDefinitions, ConnectionRouter router,
			RGB lineColour, LineStyle lineStyle, int lineWidth, String name) {
		super();
		this.description = description;
		this.detailedDescription = detailedDescription;
		this.url = url;
		if(propertyDefinitions!=null)
			this.propertyDefinitions = propertyDefinitions;
		if(router==null)
			throw new IllegalArgumentException("router cannot be null");
		this.router = router;
		this.lineColour = lineColour;
		if(lineStyle!=null)
			this.lineStyle = lineStyle;
		if(lineWidth>0)
			this.lineWidth = lineWidth;
		this.name = name;
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

	public void setLineColour(RGB lineColour) {
		this.lineColour = lineColour;
	}

	public  void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

}
