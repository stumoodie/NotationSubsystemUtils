package org.pathwayeditor.notationsubsystem.toolkit.definition;

public abstract class  TextPropertyDefinition extends AbstractPropertyDefinition<String>  {
	
	protected TextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name, value, isVisualisable, isEditable);
	}

	public String getDefaultValue() {
		return (String) super.getDefaultValue();
	}
	
	
}
