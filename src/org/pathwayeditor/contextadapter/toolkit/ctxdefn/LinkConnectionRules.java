package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.ILinkConnectionRules;
import org.pathwayeditor.contextadapter.publicapi.ILinkObjectType;
import org.pathwayeditor.contextadapter.publicapi.IObjectType;
import org.pathwayeditor.contextadapter.publicapi.IShapeObjectType;

public class LinkConnectionRules implements ILinkConnectionRules {
	private final Map<IObjectType, Set<IObjectType>> rules;
	private final ILinkObjectType owningLink;
	
	LinkConnectionRules(ILinkObjectType owningLinkObjectType){
		rules = new HashMap<IObjectType, Set<IObjectType>>();
		this.owningLink = owningLinkObjectType;
	}
	
	public void addConnection(IShapeObjectType srcType, IShapeObjectType tgtType){
		if(srcType == null || tgtType == null){
			throw new IllegalArgumentException("Both srcType and tgtType must be not null");
		}
		if(!this.rules.containsKey(srcType)){
			this.rules.put(srcType, new HashSet<IObjectType>());
		}
		Set<IObjectType> tgtSet = this.rules.get(srcType);
		tgtSet.add(tgtType);
	}
	
	public boolean isValidSource(IObjectType source) {
		if(source == null){
			throw new IllegalArgumentException("source must be not null");
		}
		return this.rules.containsKey(source);
	}

	public boolean isValidTarget(IObjectType source, IObjectType target) {
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
