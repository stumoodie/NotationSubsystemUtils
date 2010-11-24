package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer;

public class PropertyDefinitionContainer implements	IPropertyDefinitionContainer {
	private final Map<String, IPropertyDefinition> propertyDefinitions = new HashMap<String, IPropertyDefinition>();

	@Override
	public boolean containsPropertyDefinition(String name) {
		boolean retVal = false;
		if(name != null){
			retVal = this.propertyDefinitions.containsKey(name);
		}
		return retVal;
	}


	@Override
	public IPropertyDefinition getPropertyDefinition(String name) {
		IPropertyDefinition retVal = this.propertyDefinitions.get(name);
		if(retVal == null){
			throw new IllegalArgumentException("No property definition for: " + name);
		}
		return retVal;
	}


	@Override
	public int numPropertyDefinitions() {
		return this.propertyDefinitions.size();
	}


	@Override
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return this.propertyDefinitions.values().iterator();
	}


	public void addDefinition(IPropertyDefinition propDefn) {
		this.propertyDefinitions.put(propDefn.getName(), propDefn);
	}


	@Override
	public boolean containsPropertyDefinition(IPropertyDefinition defn) {
		boolean retVal = false;
		if(defn != null){
			retVal = this.propertyDefinitions.values().contains(defn);
		}
		return retVal;
	}

}
