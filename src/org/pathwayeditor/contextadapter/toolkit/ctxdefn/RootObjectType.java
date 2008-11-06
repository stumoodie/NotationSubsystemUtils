package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;


public final class RootObjectType extends AbstractObjectType implements IRootObjectType {
	
	private final IRootObjectParentingRules parentingRules = new RootObjectParentingRules(this);

	public RootObjectType(int uniqueId,String description, String name, INotationSyntaxService syntaxService){
		super(uniqueId,description,name,syntaxService);
	}

	public IRootObjectParentingRules getParentingRules() {
		return parentingRules;
	}


}
