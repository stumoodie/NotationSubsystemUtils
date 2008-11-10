package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import java.util.EnumSet;
import java.util.HashSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType.LinkEditableAttributes;


public final class LinkObjectType extends AbstractObjectType implements ILinkObjectType {
	private ILinkConnectionRules linkConnectionRules;
	private ILinkTerminusDefinition linkSourceEndDefinition;
	private  ILinkTerminusDefinition linkTargetEndDefinition;
	private ILinkAttributeDefaults defaultLinkAttributes;
	private EnumSet<LinkEditableAttributes> editableAttributes= EnumSet.noneOf(LinkEditableAttributes.class);
	

	public LinkObjectType(ILinkAttributeDefaults defaultLinkAttributes, int uniqueID,
			String description, String name, INotationSyntaxService in, ILinkTerminusDefaults linkTerminusDefaults){
		super(uniqueID,description,name,in);
		if(defaultLinkAttributes==null)
			throw new IllegalArgumentException("Default link attributes cannot be null");
		this.defaultLinkAttributes=defaultLinkAttributes;
		this.linkSourceEndDefinition = new LinkTerminusDefinition(this, LinkTermType.SOURCE, linkTerminusDefaults);
		this.linkTargetEndDefinition = new LinkTerminusDefinition(this, LinkTermType.TARGET, linkTerminusDefaults);
		this.linkConnectionRules = new LinkConnectionRules(this);
	}

	public ILinkAttributeDefaults getDefaultLinkAttributes() {
		return defaultLinkAttributes;
	}
	
	public void setDefaultLinkAttributes(ILinkAttributeDefaults in){
		if(in==null)
			throw new IllegalArgumentException("Default link attributes cannot be null");
		this.defaultLinkAttributes=in;
	}

	public EnumSet<LinkEditableAttributes> getEditiableAttributes() {
		return editableAttributes ;
	}
	public void setEditableAttributes(EnumSet<LinkEditableAttributes>in){
		editableAttributes=in;
	}

	public ILinkTerminusDefinition getSourceTerminusDefinition() {
		return linkSourceEndDefinition;
	}
	
	public void  setSourceTerminusDefinition(ILinkTerminusDefinition in) {
		if(in==null)
			throw new IllegalArgumentException("Source end definition cannot be null");
		this.linkSourceEndDefinition=in;
	}

	public ILinkTerminusDefinition getTargetTerminusDefinition() {
		return linkTargetEndDefinition;
	}
	
	public void setTargetTerminusDefinition(ILinkTerminusDefinition in){
		if(in==null)
			throw new IllegalArgumentException("Target end definition cannot be null");
		linkTargetEndDefinition=in;
	}

	public ILinkConnectionRules getLinkConnectionRules() {
		return linkConnectionRules;
	}
	
	public void setLinkConnectionRules(ILinkConnectionRules in){
		if(in==null)
			throw new IllegalArgumentException("connection rules cannot be null");
		this.linkConnectionRules=in;
	}
	
}
