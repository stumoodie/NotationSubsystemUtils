package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;

public abstract class  ObjectParentingRules<T extends IObjectType> implements IObjectTypeParentingRules {
	private final Set<IObjectType> childSet;
	private final T owningShape;
	
	/**
	 * 
	 * @param shapeObjectType
	 */
	public ObjectParentingRules(T shapeObjectType){
		if(shapeObjectType == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet = new HashSet<IObjectType>();
		this.owningShape = shapeObjectType;
	}
	
	public void addChild(T childShape){
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

	@Override
	public T getObjectType() {
		return this.owningShape;
	}

	@Override
	public boolean isValidChild(IObjectType possibleChild) {
		return isValidChildByCode(possibleChild);
	}

}
