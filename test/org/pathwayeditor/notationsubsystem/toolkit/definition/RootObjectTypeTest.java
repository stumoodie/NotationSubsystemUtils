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

import static org.junit.Assert.assertNotNull;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

//Note - the bulk of the class uses AbstractObjectType and this is tested elsewhere

@RunWith(JMock.class)
public class RootObjectTypeTest {
	private Mockery mockery= new JUnit4Mockery();
	private int positiveId=1;
	private String description="a";
	private String name="name";
	private INotationSyntaxService iNotationSyntaxService = mockery.mock(INotationSyntaxService.class);
	private IRootObjectType rootObjectType = new RootObjectType(positiveId,description,name,iNotationSyntaxService);

	@Test
	public final void testShapeParentingRulesAreNotNullByDefault() {
		assertNotNull(rootObjectType.getParentingRules());
	}
}
