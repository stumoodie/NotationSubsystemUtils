package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class NumberPropertyDefinition extends AbstractPropertyDefinition<BigDecimal> implements INumberPropertyDefinition {
	

	public NumberPropertyDefinition(String name, BigDecimal value, boolean isVisualisable, boolean isEditable) {
		super(name, value, isVisualisable, isEditable);
	}

	public NumberPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name, new BigDecimal(value), isVisualisable, isEditable);
	}

	public BigDecimal getDefaultValue() {
		return super.getDefaultValue();
	}

	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyNumberProperty((INumberAnnotationProperty) prop);
	}

	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createNumberProperty(this);
	}

}
