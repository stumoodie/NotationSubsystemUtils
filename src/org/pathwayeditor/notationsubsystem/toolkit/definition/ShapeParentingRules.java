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
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

public class ShapeParentingRules implements IShapeParentingRules {
	private final Set<IObjectType> childSet;
	private final IShapeObjectType owningShape;
	
	/**
	 * 
	 * @param shapeObjectType
	 */
	public ShapeParentingRules(IShapeObjectType shapeObjectType){
		if(shapeObjectType == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet = new HashSet<IObjectType>();
		this.owningShape = shapeObjectType;
	}
	
	public void addChild(IShapeObjectType childShape){
		if(childShape == null) throw new IllegalArgumentException("paremeter is null");
		this.childSet.add(childShape);
	}
	
	public void clear(){
		this.childSet.clear();
	}
	
	public boolean isValidChildByCode(IObjectType possibleChild) {
		if(possibleChild == null) throw new IllegalArgumentException("possibleChild cannot be null");
		return this.childSet.contains(possibleChild);
	}

	@Override
	public IShapeObjectType getObjectType() {
		return this.owningShape;
	}

	@Override
	public boolean isValidChild(IObjectType possibleChild) {
		return isValidChildByCode(possibleChild);
	}

}
