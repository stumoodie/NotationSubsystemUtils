package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IObjectType;
import org.pathwayeditor.contextadapter.publicapi.IShapeObjectType;
import org.pathwayeditor.contextadapter.publicapi.IShapeParentingRules;

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

}
