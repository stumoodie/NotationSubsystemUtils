package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class PlainTextPropertyDefinition extends TextPropertyDefinition implements IPlainTextPropertyDefinition{

	public PlainTextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name, value, isVisualisable, isEditable);
	}

	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyPlainTextProperty((IPlainTextAnnotationProperty) prop);
	}

	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createPlainTextProperty(this);
	}

}
