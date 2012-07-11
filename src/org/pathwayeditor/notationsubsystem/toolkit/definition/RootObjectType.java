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
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;


public final class RootObjectType extends AbstractObjectType implements IRootObjectType {
	public static String DEFAULT_NAME = "rootObjectType";
	private final ObjectTypeParentingRules parentingRules = new ObjectTypeParentingRules(this);

	public RootObjectType(int uniqueId,String description, String name, INotationSyntaxService syntaxService){
		super(uniqueId,name,syntaxService);
	}

    public RootObjectType(int uniqueId, INotationSyntaxService syntaxService){
        super(uniqueId, DEFAULT_NAME, syntaxService);
    }

	@Override
	public ObjectTypeParentingRules getParentingRules() {
		return parentingRules;
	}


}
