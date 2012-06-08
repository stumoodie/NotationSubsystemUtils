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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

public class LinkConnectionRules implements ILinkConnectionRules {
	private final Map<INodeObjectType, Set<INodeObjectType>> rules;
	private final ILinkObjectType owningLink;
	
	LinkConnectionRules(ILinkObjectType owningLinkObjectType){
		rules = new HashMap<INodeObjectType, Set<INodeObjectType>>();
		this.owningLink = owningLinkObjectType;
	}
	
	public void addConnection(INodeObjectType srcType, INodeObjectType tgtType){
		if(srcType == null || tgtType == null){
			throw new IllegalArgumentException("Both srcType and tgtType must be not null");
		}
		if(!this.rules.containsKey(srcType)){
			this.rules.put(srcType, new HashSet<INodeObjectType>());
		}
		Set<INodeObjectType> tgtSet = this.rules.get(srcType);
		tgtSet.add(tgtType);
	}
	
	@Override
	public boolean isValidSource(INodeObjectType source) {
		if(source == null){
			throw new IllegalArgumentException("source must be not null");
		}
		return this.rules.containsKey(source);
	}

	@Override
	public boolean isValidTarget(INodeObjectType source, INodeObjectType target) {
		if(source == null || target == null){
			throw new IllegalArgumentException("source and target must be not null");
		}
		boolean retVal = false;
		
		if(this.rules.containsKey(source)){
			retVal = this.rules.get(source).contains(target);
		}
		return retVal;
	}

	@Override
	public ILinkObjectType getLinkObjectType() {
		return this.owningLink;
	}

}
