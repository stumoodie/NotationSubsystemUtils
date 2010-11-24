package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public abstract class AbstractPropertyDefinition<T> implements IPropertyDefinition{
	private final String name;
	private T value;
	private String displayName = "A Property";
//	private boolean visualisable = false;
	private boolean editable = false;
//	private boolean alwaysDisplayed = false;
//	private final LabelAttributeDefaults labelAttributeDefaults;
	
	protected AbstractPropertyDefinition(String name, T value){
		if(name==null)
			throw new IllegalArgumentException("Name cannot be null");
		this.name = name;
		this.value = value;
//		this.labelAttributeDefaults = new LabelAttributeDefaults(this);
	}
	
//	public void setAlwaysDisplayed(boolean alwaysDisplayed){
//		this.alwaysDisplayed = alwaysDisplayed;
//		// if it is always displayed then this implies is is visualisable
//		// the converse is not true
//		if(alwaysDisplayed){
//			this.visualisable = alwaysDisplayed;
//		}
//	}
	
	public void setEditable(boolean editable){
		this.editable = editable;
	}
	
//	public void setVisualisable(boolean visualisable){
//		this.visualisable = visualisable;
//	}
	
	@Override
	public T getDefaultValue(){
		return value;
	}
	
	public void setDefaultValue(T defaultValue){
		this.value = defaultValue;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

//	public void setIsAlwaysDisplayed(boolean alwaysDisplayed){
//		this.alwaysDisplayed = alwaysDisplayed;
//	}
//	
//	public boolean isAlwaysDisplayed(){
//		return this.alwaysDisplayed;
//	}
	
	@Override
	public boolean isEditable() {
		return this.editable;
	}

//	public boolean isVisualisable() {
//		return this.visualisable;
//	}

	@Override
	public int compareTo(IPropertyDefinition o) {
		return this.name.compareTo(o.getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IPropertyDefinition)) {
			return false;
		}
		IPropertyDefinition other = (IPropertyDefinition) obj;
		if (name == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}
		return true;
	}

//	public LabelAttributeDefaults getLabelDefaults() {
//		return labelAttributeDefaults;
//	}
}
