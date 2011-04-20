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
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

public class LabelObjectType extends AbstractObjectType implements ILabelObjectType {
	private final LabelAttributeDefaults defaultLabelAttributes;
	private boolean isAlwaysDisplayed = false;
	private final LabelParentingRules parentingRules;
	
	public LabelObjectType(INotationSyntaxService in, int uniqueID, String name){
		super(uniqueID, name, in);
		this.defaultLabelAttributes = new LabelAttributeDefaults();
		this.parentingRules = new LabelParentingRules(this);
	}
	
	@Override
	public LabelParentingRules getParentingRules(){
		return this.parentingRules;
	}

	@Override
	public LabelAttributeDefaults getDefaultAttributes() {
		return this.defaultLabelAttributes;
	}

	@Override
	public boolean isAlwaysDisplayed() {
		return this.isAlwaysDisplayed;
	}

	public void setAlwaysDisplayed(boolean isAlwaysDisplayed) {
		this.isAlwaysDisplayed = isAlwaysDisplayed;
	}

}
