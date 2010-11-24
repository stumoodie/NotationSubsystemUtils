package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class PlainTextPropertyDefinition extends AbstractPropertyDefinition<String> implements IPlainTextPropertyDefinition{

	public PlainTextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name, value);
//		this.setVisualisable(isVisualisable);
		this.setEditable(isEditable);
	}

	public PlainTextPropertyDefinition(String name, String value) {
		super(name, value);
	}
	
	@Override
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyPlainTextProperty((IPlainTextAnnotationProperty) prop);
	}

	@Override
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createPlainTextProperty(this);
	}

}
