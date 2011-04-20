/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package org.pathwayeditor.notationsubsystem.toolkit.definition;

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

	@Override
	public String getQualifiedName() {
		return this.qualifiedName;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(qualifiedName=");
		builder.append(this.qualifiedName);
		builder.append(", version=");
		builder.append(version);
		builder.append(")");
		return builder.toString();
	}
	
	@Override
	public int compareTo(INotation notation){
	    int retVal = this.qualifiedName.compareTo(notation.getQualifiedName());
	    return retVal == 0 ? this.getVersion().compareTo(notation.getVersion()) : retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getVersion()
	 */
	@Override
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
