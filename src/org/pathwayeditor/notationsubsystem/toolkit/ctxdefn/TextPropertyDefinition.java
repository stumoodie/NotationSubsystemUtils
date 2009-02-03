package org.pathwayeditor.notationsubsystem.toolkit.ctxdefn;

public abstract class  TextPropertyDefinition extends AbstractPropertyDefinition  {
	
	protected TextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name, value, isVisualisable, isEditable);
	}

	public String getDefaultValue() {
		return (String) super.getDefaultValue();
	}
	
	
}
