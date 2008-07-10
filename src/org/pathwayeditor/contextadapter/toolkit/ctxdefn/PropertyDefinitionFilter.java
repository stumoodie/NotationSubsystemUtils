package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.pathwayeditor.contextadapter.publicapi.IObjectType;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinitionFilter;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition.PropDefnType;
import org.pathwayeditor.contextadapter.toolkit.util.IFilterCriteria;
import org.pathwayeditor.contextadapter.toolkit.util.SetFilter;

public final class PropertyDefinitionFilter implements IPropertyDefinitionFilter {
	private final IObjectType owningType;
	private final Set<IPropertyDefinition> propertyDefs;
	
	/**
	 * Creates a definition instance for IObjectType.
	 * @param linkObjectType a valid object type instance. Cannot be null.
	 * @throws IllegalArgumentException if linkObjectType is null.
	 */
	public PropertyDefinitionFilter(IObjectType objectType) {
		if(objectType == null) throw new IllegalArgumentException("parameter objectType cannot be null");
		this.owningType = objectType;
		this.propertyDefs = new TreeSet<IPropertyDefinition>();
	}

	public Set<IPropertyDefinition> getAllProperties() {
		return new TreeSet<IPropertyDefinition>(this.propertyDefs);
	}

	public Iterator<IPropertyDefinition> getAllPropertiesIterator() {
		return this.propertyDefs.iterator();
	}

	public Set<IPropertyDefinition> getEditableProperties() {
		SetFilter<IPropertyDefinition> setFilter = new SetFilter<IPropertyDefinition>(new IFilterCriteria<IPropertyDefinition>(){

			public boolean passes(IPropertyDefinition item) {
				return item.isEditable() == true;
			}
			
		});
		return setFilter.filterSet(this.propertyDefs);
	}

	public Set<IPropertyDefinition> getFormattedTextProperties() {
		SetFilter<IPropertyDefinition> setFilter = new SetFilter<IPropertyDefinition>(new PropTypeCriteria(PropDefnType.FORMATTED_TEXT));
		return setFilter.filterSet(this.propertyDefs);
	}

	public Set<IPropertyDefinition> getNumberProperties() {
		SetFilter<IPropertyDefinition> setFilter = new SetFilter<IPropertyDefinition>(new PropTypeCriteria(PropDefnType.NUMBER));
		return setFilter.filterSet(this.propertyDefs);
	}

	public IObjectType getObjectType() {
		return this.owningType;
	}

	public Set<IPropertyDefinition> getTextProperties() {
		SetFilter<IPropertyDefinition> setFilter = new SetFilter<IPropertyDefinition>(new PropTypeCriteria(PropDefnType.PLAIN_TEXT));
		return setFilter.filterSet(this.propertyDefs);
	}

	public Set<IPropertyDefinition> getVisualisableProperties() {
		SetFilter<IPropertyDefinition> setFilter = new SetFilter<IPropertyDefinition>(new IFilterCriteria<IPropertyDefinition>(){

			public boolean passes(IPropertyDefinition item) {
				return item.isVisualisable() == true;
			}
			
		});
		return setFilter.filterSet(this.propertyDefs);
	}

	/**
	 * Adds a new propertyDefinition to the collection. The abstraction of the class is one of a set, so the same
	 * object can be added twice, but will only be stored one, i.e. the second call to add will effectively be ignored. 
	 * @param propertyDefinition a non-null instancenthat defines the new property to be added to the class.
	 * @throws IllegalArgumentException if the parameter is null.
	 */
	public void add(IPropertyDefinition propertyDefinition) {
		if(propertyDefinition == null) throw new IllegalArgumentException("Parameter must be non-null");
		this.propertyDefs.add(propertyDefinition);
	}
	
	private static class PropTypeCriteria implements IFilterCriteria<IPropertyDefinition> {
		private final PropDefnType filterType;
		
		public PropTypeCriteria(PropDefnType filterType){
			this.filterType = filterType;
		}
		
		public boolean passes(IPropertyDefinition item) {
			return item.getType() == filterType;
		}
		
	}
}
