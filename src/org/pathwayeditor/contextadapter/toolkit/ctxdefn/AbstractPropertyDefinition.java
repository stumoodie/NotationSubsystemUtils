package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

public abstract class AbstractPropertyDefinition implements IPropertyDefinition{
	private final String name;
	private final Object value;
	private final boolean visualisable;
	private final boolean editable;
	private ILabelAttributeDefaults labelAttributeDefaults;
	
	public AbstractPropertyDefinition(String name, Object value, boolean isVisualisable, boolean isEditable){
		if(name==null)
			throw new IllegalArgumentException("Name cannot be null");
		this.name = name;
		this.value = value;
		this.visualisable = isVisualisable;
		this.editable = isEditable;
		if(isVisualisable()){
			setLabelAttributeDefaults(new LabelAttributeDefaults());
		}
	}
	public Object getDefaultValue(){
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

	public void setLabelAttributeDefaults(ILabelAttributeDefaults defaults) {
		this.labelAttributeDefaults=defaults;
	}
	
	public ILabelAttributeDefaults getLabelDefaults() {
		return labelAttributeDefaults;
	}

}
