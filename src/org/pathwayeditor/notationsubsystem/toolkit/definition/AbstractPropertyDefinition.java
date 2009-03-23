package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public abstract class AbstractPropertyDefinition<T> implements IPropertyDefinition{
	private final String name;
	private final T value;
	private final boolean visualisable;
	private final boolean editable;
	private final LabelAttributeDefaults labelAttributeDefaults;
	
	protected AbstractPropertyDefinition(String name, T value, boolean isVisualisable, boolean isEditable){
		if(name==null)
			throw new IllegalArgumentException("Name cannot be null");
		this.name = name;
		this.value = value;
		this.visualisable = isVisualisable;
		this.editable = isEditable;
		this.labelAttributeDefaults = new LabelAttributeDefaults(this);
	}
	public T getDefaultValue(){
		return value;
	}
	public String getName() {
		return this.name;
	}

	public boolean isEditable() {
		return this.editable;
	}

	public boolean isVisualisable() {
		return this.visualisable;
	}

	public int compareTo(IPropertyDefinition o) {
		return this.name.compareTo(o.getName());
	}

	public LabelAttributeDefaults getLabelDefaults() {
		return labelAttributeDefaults;
	}

}
