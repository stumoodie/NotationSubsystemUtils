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

import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public class RootObjectParentingRules implements IRootObjectParentingRules {
	private final IRootObjectType owningType;
	private final Set<IObjectType> childSet;
	
	/**
	 * Constructor
	 * @param owningType owning type form these rules
	 * @throws IllegalArgumentException if owningType is null. 
	 */
	RootObjectParentingRules(IRootObjectType owningType){
		if(owningType == null) throw new IllegalArgumentException("owningType cannot be null");
		this.childSet = new HashSet<IObjectType>();
		this.owningType = owningType;
	}
	
	@Override
	public IRootObjectType getObjectType() {
		return this.owningType;
	}
 
	/**
	 * add permitted child 
	 * @param childShape child shape 
	 * @throws IllegalArgumentException if childShape is null.
	 */
	public void addChild(IShapeObjectType childShape){
		if(childShape == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet.add(childShape);
	}
	
	@Override
	public boolean isValidChild(IObjectType possibleChild) {
		if(possibleChild == null) throw new IllegalArgumentException("possibleChild cannot be null");
		return this.childSet.contains(possibleChild);
	}
		
}
