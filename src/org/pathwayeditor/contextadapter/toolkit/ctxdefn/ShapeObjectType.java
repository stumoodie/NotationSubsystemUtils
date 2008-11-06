package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;


public final class ShapeObjectType extends AbstractObjectType implements IShapeObjectType {

	private IShapeAttributeDefaults defaultShapeAttributes;
	private final IShapeParentingRules parentingRules= new ShapeParentingRules(this);
	
	public ShapeObjectType(IShapeAttributeDefaults defaultShapeAttributes, int uniqueId,String description, String name, INotationSyntaxService syntaxService){
		super(uniqueId,description,name,syntaxService);
		if(defaultShapeAttributes==null)
			throw new IllegalArgumentException("Shape Attribute Defaults cannot be null");
		this.defaultShapeAttributes=defaultShapeAttributes;
	}

	public IShapeAttributeDefaults getDefaultAttributes() {
		return defaultShapeAttributes;
	}

	public EnumSet<EditableShapeAttributes> getEditableAttributes() {
		return EnumSet.allOf(IShapeObjectType.EditableShapeAttributes.class);
	}

	public IShapeParentingRules getParentingRules() {
		return parentingRules;
	}

	public int compareTo(IObjectType o) {
		return getName().compareTo(o.getName());
	}

	public void setShapeAttributeDefaults(IShapeAttributeDefaults in) {
		if(in==null)
			throw new IllegalArgumentException("Shape Attribute Defaults cannot be null");
		this.defaultShapeAttributes=in;
	}
}
