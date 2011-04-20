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
	
	
	@Override
	public final String getDescription() {
		return description;
	}
	
	public final void setDescription(String in){
		if(in==null)
			throw new IllegalArgumentException("description must be non null");
		description=in;
	}

	@Override
	public final String getName() {
		return name;
	}
	
	@Override
	public final int getUniqueId() {
		return uniqueID;
	}

	@Override
	public final INotationSyntaxService getSyntaxService() {
		return notationSyntaxService;
	}

    @Override
	public final int compareTo(IObjectType o) {
        return (getUniqueId() == o.getUniqueId()) ? 0 : (getUniqueId() < o.getUniqueId() ? -1 : 1);
    }
}
