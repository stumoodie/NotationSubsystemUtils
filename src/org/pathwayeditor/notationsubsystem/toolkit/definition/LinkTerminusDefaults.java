package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;

public class LinkTerminusDefaults implements ILinkTerminusDefaults {
	private Size endSize;
	private short gap;
	private RGB termColour;
	private Size termSize;
	private LinkEndDecoratorShape linkEndDecoratorShape;
	private PrimitiveShapeType termDecoratorType;
	private final PropertyDefinitionContainer propertyDefinitions = new PropertyDefinitionContainer();
	private final LinkTerminusDefinition linkTerminusDefinition; 
	
	public LinkTerminusDefaults(LinkTerminusDefinition linkTermDefn) {
	    this.linkTerminusDefinition = linkTermDefn;
	}

//	/**
//	 * 
//	 */
//	public LinkTerminusDefaults(LinkTerminusDefaults other) {
//		if(other==null) throw new IllegalArgumentException("Copy constructor require non NULL template object");
//		Size endSizeT = other.getEndSize();
//		setEndSize(new Size(endSizeT.getWidth(),endSizeT.getHeight()));
//		setGap(other.getGap());
//		setLinkEndDecoratorShape(other.getEndDecoratorType());
//		RGB termColourT = other.getTermColour();
//		setTermColour(new RGB(termColourT.getRed(),termColourT.getGreen(),termColourT.getBlue()));
//		setTermDecoratorType(other.getTermDecoratorType());
//		Size termSizeT = other.getTermSize();
//		setTermSize(new Size(termSizeT.getWidth(),termSizeT.getHeight()));
//		Iterator<IPropertyDefinition> it=other.propertyDefinitionIterator();
//		while(it.hasNext()){
//			addPropertyDefinition(it.next());
//		}
//	}

	public LinkEndDecoratorShape getEndDecoratorType() {
		return linkEndDecoratorShape;
	}
	
	public void setEndDecoratorType(LinkEndDecoratorShape in){
		linkEndDecoratorShape=in;
	}

	public Size getEndSize() {
		return endSize;
	}
	
	public void setEndSize(Size in){
		endSize=in;
	}

	public short getGap() {
		return gap;
	}
	
	public void setGap(short in){
		gap=in;
	}

	public RGB getTermColour() {
		return termColour;
	}
	
	public void setTermColour(RGB in){
		termColour=in;
	}

	public PrimitiveShapeType getTermDecoratorType() {
		return termDecoratorType;
	}
	
	public void setTermDecoratorType(PrimitiveShapeType in){
		termDecoratorType=in;
	}

	public Size getTermSize() {
		return termSize;
	}
	
	public void setTermSize(Size in){
		termSize=in;
	}

	public int getPropertyDefinitionNumber(){
		return propertyDefinitions.numPropertyDefinitions();
	}
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return propertyDefinitions.propertyDefinitionIterator();
	}
	
	public void addPropertyDefinition(IPropertyDefinition in){
		propertyDefinitions.addDefinition(in);
	}

    public LinkTerminusDefinition getLinkTerminusDefinition() {
        return linkTerminusDefinition;
    }

	public boolean containsPropertyDefinition(String name) {
		return this.propertyDefinitions.containsPropertyDefinition(name);
	}

	public IPropertyDefinition getPropertyDefinition(String name) {
		return this.propertyDefinitions.getPropertyDefinition(name);
	}

	public int numPropertyDefinitions() {
		return this.propertyDefinitions.numPropertyDefinitions();
	}

	public boolean containsPropertyDefinition(IPropertyDefinition defn) {
		return this.propertyDefinitions.containsPropertyDefinition(defn);
	}

}