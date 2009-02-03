package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

public class ShapeParentingRules implements IShapeParentingRules {
	private final Set<IObjectType> childSet;
	private final IShapeObjectType owningShape;
	
	/**
	 * 
	 * @param shapeObjectType
	 */
	public ShapeParentingRules(IShapeObjectType shapeObjectType){
		if(shapeObjectType == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet = new HashSet<IObjectType>();
		this.owningShape = shapeObjectType;
	}
	
	public void addChild(IShapeObjectType childShape){
		if(childShape == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet.add(childShape);
	}
	
	public void clear(){
		this.childSet.clear();
	}
	
	public boolean isValidChildByCode(IObjectType possibleChild) {
		if(possibleChild == null) throw new IllegalArgumentException("possibleChild cannot be null");
		return this.childSet.contains(possibleChild);
	}

	public IShapeObjectType getObjectType() {
		return this.owningShape;
	}

	public boolean isValidChild(IObjectType possibleChild) {
		return isValidChildByCode(possibleChild);
	}

}
