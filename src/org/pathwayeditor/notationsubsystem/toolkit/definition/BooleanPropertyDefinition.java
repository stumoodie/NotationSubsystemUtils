package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class BooleanPropertyDefinition extends AbstractPropertyDefinition<Boolean> implements IBooleanPropertyDefinition {
	

	public BooleanPropertyDefinition(String name, Boolean value) {
		super(name, value);
	}

	@Override
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyBooleanProperty((IBooleanAnnotationProperty) prop);
	}

	@Override
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createBooleanProperty(this);
	}

}
