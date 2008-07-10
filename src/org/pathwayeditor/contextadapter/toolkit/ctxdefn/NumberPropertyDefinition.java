package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.math.BigDecimal;

import org.pathwayeditor.contextadapter.publicapi.ILabelDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;

public class NumberPropertyDefinition implements IPropertyDefinition {
	private final String name;
	private final PropDefnType type;
	private final BigDecimal value;
	private final boolean visualisable;
	private final boolean editable;
	
	public NumberPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable){
		this(name, new BigDecimal(value),isVisualisable,isEditable);
	}
	
	public NumberPropertyDefinition(String name, BigDecimal value, boolean isVisualisable, boolean isEditable){
		this.name = name;
		this.type = PropDefnType.NUMBER;
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
		return this.value.toString();
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


	public BigDecimal getValueObject() {
		return value;
	}
	private ILabelDefinition appearance;

	public ILabelDefinition getAppearance() {
		return appearance;
	}

	public void setAppearance(ILabelDefinition appearance) {
		this.appearance = appearance;
	}

}
