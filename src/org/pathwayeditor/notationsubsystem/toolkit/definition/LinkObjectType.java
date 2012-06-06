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

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;


public final class LinkObjectType extends AbstractObjectType implements ILinkObjectType {
	private final LinkConnectionRules linkConnectionRules;
	private final LinkTerminusDefinition linkSourceEndDefinition;
	private final LinkTerminusDefinition linkTargetEndDefinition;
	private final LinkAttributeDefaults defaultLinkAttributes;
	private EnumSet<LinkEditableAttributes> editableAttributes= EnumSet.noneOf(LinkEditableAttributes.class);
	private final LinkParentingRules linkParentingRules;
	

	public LinkObjectType(INotationSyntaxService in, int uniqueID, String name){
		super(uniqueID, name, in);
		this.defaultLinkAttributes = new LinkAttributeDefaults();
		this.linkSourceEndDefinition = new LinkTerminusDefinition(this, LinkTermType.SOURCE);
		this.linkTargetEndDefinition = new LinkTerminusDefinition(this, LinkTermType.TARGET);
		this.linkConnectionRules = new LinkConnectionRules(this);
		this.linkParentingRules = new LinkParentingRules(this);
	}

	@Override
	public LinkAttributeDefaults getDefaultAttributes() {
		return defaultLinkAttributes;
	}
	
	@Override
	public EnumSet<LinkEditableAttributes> getEditableAttributes() {
		return editableAttributes ;
	}
	public void setEditableAttributes(EnumSet<LinkEditableAttributes>in){
		editableAttributes=in;
	}

	@Override
	public LinkTerminusDefinition getSourceTerminusDefinition() {
		return linkSourceEndDefinition;
	}

	@Override
	public LinkTerminusDefinition getTargetTerminusDefinition() {
		return linkTargetEndDefinition;
	}
	

	@Override
	public LinkConnectionRules getLinkConnectionRules() {
		return linkConnectionRules;
	}

	@Override
	public LinkParentingRules getParentingRules() {
		return this.linkParentingRules;
	}
}
