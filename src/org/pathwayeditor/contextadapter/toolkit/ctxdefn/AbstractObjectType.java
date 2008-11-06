package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

public class AbstractObjectType implements IObjectType{
	private int uniqueID;
	private String description="";
	private String name;
	private final INotationSyntaxService notationSyntaxService;
	
	public AbstractObjectType( int uniqueID,
			String description, String name, INotationSyntaxService in){
		if(name==null||name.equals(""))
			throw new IllegalArgumentException("name must be a non null, non empty string");
		if(description==null)
			throw new IllegalArgumentException("description must be non null");
		this.name=name;
		if(in==null)
			throw new IllegalArgumentException("notation syntax service cannot be null");
		notationSyntaxService=in;
		if(uniqueID<0) 
			throw new IllegalArgumentException("unique ID must be a positive integer");
		this.uniqueID=uniqueID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notationSyntaxService== null) ? 0 : notationSyntaxService.hashCode());
		result = prime * result + uniqueID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AbstractObjectType other = (AbstractObjectType) obj;
		if (notationSyntaxService== null) {
			if (other.notationSyntaxService != null)
				return false;
		} else if (!notationSyntaxService.equals(other.notationSyntaxService))
			return false;
		if (getUniqueId()!=other.getUniqueId())
			return false;
		return true;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String in){
		if(in==null)
			throw new IllegalArgumentException("description must be non null");
		description=in;
	}

	public String getName() {
		return name;
	}
	
	public int getUniqueId() {
		return uniqueID;
	}

	public INotationSyntaxService getSyntaxService() {
		return notationSyntaxService;
	}

	public int compareTo(IObjectType o) {
		return name.compareTo(o.getName());
	}
}
