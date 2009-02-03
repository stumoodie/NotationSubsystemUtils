package org.pathwayeditor.notationsubsystem.toolkit.ctxdefn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public class LinkConnectionRules implements ILinkConnectionRules {
	private final Map<IShapeObjectType, Set<IShapeObjectType>> rules;
	private final ILinkObjectType owningLink;
	
	LinkConnectionRules(ILinkObjectType owningLinkObjectType){
		rules = new HashMap<IShapeObjectType, Set<IShapeObjectType>>();
		this.owningLink = owningLinkObjectType;
	}
	
	public void addConnection(IShapeObjectType srcType, IShapeObjectType tgtType){
		if(srcType == null || tgtType == null){
			throw new IllegalArgumentException("Both srcType and tgtType must be not null");
		}
		if(!this.rules.containsKey(srcType)){
			this.rules.put(srcType, new HashSet<IShapeObjectType>());
		}
		Set<IShapeObjectType> tgtSet = this.rules.get(srcType);
		tgtSet.add(tgtType);
	}
	
	public boolean isValidSource(IShapeObjectType source) {
		if(source == null){
			throw new IllegalArgumentException("source must be not null");
		}
		return this.rules.containsKey(source);
	}

	public boolean isValidTarget(IShapeObjectType source, IShapeObjectType target) {
		if(source == null || target == null){
			throw new IllegalArgumentException("source and target must be not null");
		}
		boolean retVal = false;
		
		if(this.rules.containsKey(source)){
			retVal = this.rules.get(source).contains(target);
		}
		return retVal;
	}

	public ILinkObjectType getLinkObjectType() {
		return this.owningLink;
	}

}
