package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


public final class ShapeObjectType extends AbstractObjectType implements IShapeObjectType {

	private final ShapeAttributeDefaults defaultShapeAttributes;
	private final ShapeParentingRules parentingRules= new ShapeParentingRules(this);
	private EnumSet<EditableShapeAttributes> editableAttributes=EnumSet.noneOf(EditableShapeAttributes.class);
	
	public ShapeObjectType(INotationSyntaxService syntaxService, int uniqueId, String name){
		super(uniqueId, name, syntaxService);
		this.defaultShapeAttributes = new ShapeAttributeDefaults();
	}

	@Override
	public ShapeAttributeDefaults getDefaultAttributes() {
		return defaultShapeAttributes;
	}

	@Override
	public EnumSet<EditableShapeAttributes> getEditableAttributes() {
		return editableAttributes;
	}
	
	public void setEditableAttributes(EnumSet <EditableShapeAttributes>in){
		editableAttributes=in;
	}

	@Override
	public ShapeParentingRules getParentingRules() {
		return parentingRules;
	}
}
