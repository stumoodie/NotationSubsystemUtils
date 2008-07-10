package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.contextadapter.publicapi.ILabelDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;


public class TextPropertyDefinition implements IPropertyDefinition {
	private final String name;
	private final String value;
	private final boolean isVisualisable;
	private final boolean isEditable;
	
	public TextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable){
		this.name = name;
		this.value = value;
		this.isVisualisable = isVisualisable;
		this.isEditable = isEditable;
		if(isVisualisable()){
			setAppearance(new DefaultLabelDefinition());
		}
	}
	
	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public boolean isVisualisable() {
		return this.isVisualisable;
	}

	public boolean isEditable() {
		return this.isEditable;
	}

	public String getValueObject() {
		return this.value;
	}

	public int compareTo(IPropertyDefinition o) {
		return this.name.compareTo(o.getName());
	}

	public PropDefnType getType() {
		return PropDefnType.PLAIN_TEXT;
	}
	private ILabelDefinition appearance;

	public ILabelDefinition getAppearance() {
		return appearance;
	}

	public void setAppearance(ILabelDefinition appearance) {
		this.appearance = appearance;
	}

}
