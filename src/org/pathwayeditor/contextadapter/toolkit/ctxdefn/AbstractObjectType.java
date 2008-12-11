package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

public abstract class AbstractObjectType implements IObjectType{
    private static final String DEFAULT_DESCN = "No descn";  
	private int uniqueID;
	private String description = DEFAULT_DESCN;
	private final String name;
	private final INotationSyntaxService notationSyntaxService;
	
	protected AbstractObjectType( int uniqueID, String name, INotationSyntaxService in){
		if(name==null||name.equals(""))
			throw new IllegalArgumentException("name must be a non null, non empty string");
		this.name=name;
		if(in==null)
			throw new IllegalArgumentException("notation syntax service cannot be null");
		notationSyntaxService=in;
		if(uniqueID<0) 
			throw new IllegalArgumentException("unique ID must be a positive integer");
		this.uniqueID=uniqueID;
	}
	
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notationSyntaxService== null) ? 0 : notationSyntaxService.hashCode());
		result = prime * result + uniqueID;
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
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
	
	
	public final String getDescription() {
		return description;
	}
	
	public final void setDescription(String in){
		if(in==null)
			throw new IllegalArgumentException("description must be non null");
		description=in;
	}

	public final String getName() {
		return name;
	}
	
	public final int getUniqueId() {
		return uniqueID;
	}

	public final INotationSyntaxService getSyntaxService() {
		return notationSyntaxService;
	}

    public final int compareTo(IObjectType o) {
        return (getUniqueId() == o.getUniqueId()) ? 0 : (getUniqueId() < o.getUniqueId() ? -1 : 1);
    }
}
