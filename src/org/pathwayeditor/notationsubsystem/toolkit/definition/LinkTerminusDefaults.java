package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.figure.geometry.Dimension;

public class LinkTerminusDefaults implements ILinkTerminusDefaults {
	private Dimension endSize;
	private double gap;
	private LinkEndDecoratorShape linkEndDecoratorShape;
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
//		Dimension endSizeT = other.getEndSize();
//		setEndSize(new Dimension(endSizeT.getWidth(),endSizeT.getHeight()));
//		setGap(other.getGap());
//		setLinkEndDecoratorShape(other.getEndDecoratorType());
//		RGB termColourT = other.getTermColour();
//		setTermColour(new RGB(termColourT.getRed(),termColourT.getGreen(),termColourT.getBlue()));
//		setTermDecoratorType(other.getTermDecoratorType());
//		Dimension termSizeT = other.getTermSize();
//		setTermSize(new Dimension(termSizeT.getWidth(),termSizeT.getHeight()));
//		Iterator<IPropertyDefinition> it=other.propertyDefinitionIterator();
//		while(it.hasNext()){
//			addPropertyDefinition(it.next());
//		}
//	}

	@Override
	public LinkEndDecoratorShape getEndDecoratorType() {
		return linkEndDecoratorShape;
	}
	
	public void setEndDecoratorType(LinkEndDecoratorShape in){
		linkEndDecoratorShape=in;
	}

	@Override
	public Dimension getEndSize() {
		return endSize;
	}
	
	public void setEndSize(Dimension in){
		endSize=in;
	}

	@Override
	public double getGap() {
		return gap;
	}
	
	public void setGap(double in){
		gap=in;
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
