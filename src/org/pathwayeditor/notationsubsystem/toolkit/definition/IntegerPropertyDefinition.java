package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class IntegerPropertyDefinition extends AbstractPropertyDefinition<Integer> implements IIntegerPropertyDefinition {
	

	public IntegerPropertyDefinition(String name, Integer value) {
		super(name, value);
	}

	@Override
	public Integer getDefaultValue() {
		return super.getDefaultValue();
	}

	@Override
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyIntegerProperty((IIntegerAnnotationProperty) prop);
	}

	@Override
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createIntegerProperty(this);
	}

}
