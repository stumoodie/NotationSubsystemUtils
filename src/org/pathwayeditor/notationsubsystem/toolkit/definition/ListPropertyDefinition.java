package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class ListPropertyDefinition extends AbstractPropertyDefinition<List<String>>
		implements IListPropertyDefinition {

	public ListPropertyDefinition(String name, List<String> value, boolean isVisualisable, boolean isEditable) {
		super(name, value);
		this.setVisualisable(isVisualisable);
		this.setEditable(isEditable);
	}

	public ListPropertyDefinition(String name, List<String> value) {
		super(name, value);
	}

	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder,
			IAnnotationProperty otherProperty) {
		return propertyBuilder.copyListProperty((IListAnnotationProperty) otherProperty);
	}

	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createListProperty(this);
	}

}
