package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public class RootObjectParentingRules implements IRootObjectParentingRules {
	private final IRootObjectType owningType;
	private final Set<IObjectType> childSet;
	
	/**
	 * Constructor
	 * @param owningType owning type form these rules
	 * @throws IllegalArgumentException if owningType is null. 
	 */
	RootObjectParentingRules(IRootObjectType owningType){
		if(owningType == null) throw new IllegalArgumentException("owningType cannot be null");
		this.childSet = new HashSet<IObjectType>();
		this.owningType = owningType;
	}
	
	public IRootObjectType getObjectType() {
		return this.owningType;
	}
 
	/**
	 * add permitted child 
	 * @param childShape child shape 
	 * @throws IllegalArgumentException if childShape is null.
	 */
	public void addChild(IShapeObjectType childShape){
		if(childShape == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet.add(childShape);
	}
	
	public boolean isValidChild(IObjectType possibleChild) {
		if(possibleChild == null) throw new IllegalArgumentException("possibleChild cannot be null");
		return this.childSet.contains(possibleChild);
	}
		
}
