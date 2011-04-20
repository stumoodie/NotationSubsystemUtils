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

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

public class PlainTextPropertyDefinition extends AbstractPropertyDefinition<String> implements IPlainTextPropertyDefinition{

	public PlainTextPropertyDefinition(String name, String value, boolean isVisualisable, boolean isEditable) {
		super(name, value);
//		this.setVisualisable(isVisualisable);
		this.setEditable(isEditable);
	}

	public PlainTextPropertyDefinition(String name, String value) {
		super(name, value);
	}
	
	@Override
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty prop) {
		return propertyBuilder.copyPlainTextProperty((IPlainTextAnnotationProperty) prop);
	}

	@Override
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createPlainTextProperty(this);
	}

}
