package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public abstract class AbstractPropertyDefinition<T> implements IPropertyDefinition{
	private final String name;
	private final T value;
	private String displayName = "A Property";
	private boolean visualisable = false;
	private boolean editable = false;
	private boolean alwaysDisplayed = false;
	private final LabelAttributeDefaults labelAttributeDefaults;
	
	protected AbstractPropertyDefinition(String name, T value){
		if(name==null)
			throw new IllegalArgumentException("Name cannot be null");
		this.name = name;
		this.value = value;
		this.labelAttributeDefaults = new LabelAttributeDefaults(this);
	}
	
	public void setAlwaysDisplayed(boolean alwaysDisplayed){
		this.alwaysDisplayed = alwaysDisplayed;
		// if it is always displayed then this implies is is visualisable
		// the converse is not true
		if(alwaysDisplayed){
			this.visualisable = alwaysDisplayed;
		}
	}
	
	public void setEditable(boolean editable){
		this.editable = editable;
	}
	
	public void setVisualisable(boolean visualisable){
		this.visualisable = visualisable;
	}
	
	public T getDefaultValue(){
		return value;
	}
	public String getName() {
		return this.name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setIsAlwaysDisplayed(boolean alwaysDisplayed){
		this.alwaysDisplayed = alwaysDisplayed;
	}
	
	public boolean isAlwaysDisplayed(){
		return this.alwaysDisplayed;
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
