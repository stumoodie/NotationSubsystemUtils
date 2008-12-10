package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;

public class GeneralNotation implements INotation {
	private final String qualifiedName;
	private final String displayName;
	private final String description;
	private final Version version; 

	public GeneralNotation(String qualifiedName, String displayName, String description, Version version) {
		this.qualifiedName = qualifiedName;
		this.displayName = displayName;
		this.description = description;
		this.version = version;
	}

	public String getQualifiedName() {
		return this.qualifiedName;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(qualifiedName=");
		builder.append(this.qualifiedName);
		builder.append(", version=");
		builder.append(version);
		builder.append(")");
		return builder.toString();
	}
	
	public int compareTo(INotation notation){
	    int retVal = this.qualifiedName.compareTo(notation.getQualifiedName());
	    return retVal == 0 ? this.getVersion().compareTo(notation.getVersion()) : retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getDisplayName()
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getVersion()
	 */
	public Version getVersion() {
		return this.version;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((qualifiedName == null) ? 0 : qualifiedName.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof INotation))
            return false;
        INotation other = (INotation) obj;
        if (qualifiedName == null) {
            if (other.getQualifiedName() != null)
                return false;
        } else if (!qualifiedName.equals(other.getQualifiedName()))
            return false;
        if (version == null) {
            if (other.getVersion() != null)
                return false;
        } else if (!version.equals(other.getVersion()))
            return false;
        return true;
    }
}
