package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;

public class LinkParentingRules implements IObjectTypeParentingRules {
	private final LinkObjectType owner;
	private final Set<IObjectType> childSet;

	public LinkParentingRules(LinkObjectType linkObjectType) {
		this.owner = linkObjectType;
		this.childSet = new HashSet<IObjectType>();
	}

	@Override
	public IObjectType getObjectType() {
		return this.owner;
	}

	public void addChild(IObjectType childShape){
		if(childShape == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet.add(childShape);
	}

	@Override
	public boolean isValidChild(IObjectType possibleChild) {
		return this.childSet.contains(possibleChild);
	}

}
