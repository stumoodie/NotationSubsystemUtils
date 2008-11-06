package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public final class FormattedTextPropertyDefinition extends TextPropertyDefinition implements IHtmlPropertyDefinition {

	public FormattedTextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name,value,isVisualisable,isEditable);
	}

	public int compareTo(IPropertyDefinition o) {
		return getName().compareTo(o.getName());
	}
	
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyHtmlProperty((IHtmlAnnotationProperty) prop);
	}

	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createHtmlProperty(this);
	}

}
