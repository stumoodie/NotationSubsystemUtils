package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.contextadapter.publicapi.ILabelDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;


public final class FormattedTextPropertyDefinition implements IPropertyDefinition {
	private final String name;
	private final PropDefnType type;
	private final String value;
	private final boolean visualisable;
	private final boolean editable;
	
	public FormattedTextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable){
		this.name = name;
		this.type = PropDefnType.FORMATTED_TEXT;
		this.value = value;
		this.visualisable = isVisualisable;
		this.editable = isEditable;
		if(isVisualisable()){
			setAppearance(new DefaultLabelDefinition());
		}

	}
	
	public String getName() {
		return this.name;
	}

	public PropDefnType getType() {
		return this.type;
	}

	public String getValue() {
		return this.value;
	}

	public String getValueObject() {
		return this.value;
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

	private ILabelDefinition appearance;

	public ILabelDefinition getAppearance() {
		return appearance;
	}

	public void setAppearance(ILabelDefinition appearance) {
		this.appearance = appearance;
	}


}
