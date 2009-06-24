package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @deprecated this type of property is not displayed any may we be removed. Do not use.
 * @author smoodie
 *
 */
public final class FormattedTextPropertyDefinition extends TextPropertyDefinition implements IHtmlPropertyDefinition {

	public FormattedTextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name,value);
		this.setVisualisable(isVisualisable);
		this.setEditable(isEditable);
	}

	public FormattedTextPropertyDefinition(String name, String value) {
		super(name,value);
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
