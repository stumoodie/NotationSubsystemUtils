package org.pathwayeditor.notationsubsystem.toolkit.definition;

public abstract class  TextPropertyDefinition extends AbstractPropertyDefinition<String>  {
	
	protected TextPropertyDefinition(String name, String value) {
		super(name, value);
	}

	public String getDefaultValue() {
		return (String) super.getDefaultValue();
	}
	
	
}
