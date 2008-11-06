package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class NumberPropertyDefinition extends AbstractPropertyDefinition implements INumberPropertyDefinition {
	

	public NumberPropertyDefinition(String name, Object value, boolean isVisualisable, boolean isEditable) {
		super(name, value, isVisualisable, isEditable);
	}

	public BigDecimal getDefaultValue() {
		return (BigDecimal) super.getDefaultValue();
	}

	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyNumberProperty((INumberAnnotationProperty) prop);
	}

	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createNumberProperty(this);
	}

}
