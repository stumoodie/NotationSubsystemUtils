package org.pathwayeditor.notationsubsystem.toolkit.definition;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;


public final class RootObjectType extends AbstractObjectType implements IRootObjectType {
	public static String DEFAULT_NAME = "rootObjectType";
	private final RootObjectParentingRules parentingRules = new RootObjectParentingRules(this);

	public RootObjectType(int uniqueId,String description, String name, INotationSyntaxService syntaxService){
		super(uniqueId,name,syntaxService);
	}

    public RootObjectType(int uniqueId, INotationSyntaxService syntaxService){
        super(uniqueId, DEFAULT_NAME, syntaxService);
    }

	public RootObjectParentingRules getParentingRules() {
		return parentingRules;
	}


}
