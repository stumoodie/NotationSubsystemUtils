package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

public abstract class  TextPropertyDefinition extends AbstractPropertyDefinition  {
	
	public TextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name, value, isVisualisable, isEditable);
	}

	public String getDefaultValue() {
		return (String) super.getDefaultValue();
	}
	
	
}
