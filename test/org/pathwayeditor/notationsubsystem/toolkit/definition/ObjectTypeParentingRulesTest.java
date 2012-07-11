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

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


@RunWith(JMock.class)
public class ObjectTypeParentingRulesTest {
	private Mockery context = new JUnit4Mockery();
	private ObjectTypeParentingRules testInstance;
	private IShapeObjectType testOwningInstance;
//	private enum ObjectType { TEST1{ public String toString() { return "1"; } }};
	
	@Before
	public void setUp() throws Exception {
		this.testOwningInstance = this.context.mock(IShapeObjectType.class);
		this.testInstance = new ObjectTypeParentingRules(this.testOwningInstance);
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testShapeParentingRulesNullParam() {
		new ObjectTypeParentingRules(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testAddChildNullParam() {
		this.testInstance.addChild(null);
	}

	@Test
	public final void testAddChild() {
		final IShapeObjectType shapeType = this.context.mock(IShapeObjectType.class);
		this.context.checking(new Expectations(){{
//			allowing(shapeType).getTypeCode();
//			will(returnValue(ObjectType.TEST1));
		}});
		this.testInstance.addChild(shapeType);
		boolean actualResult = this.testInstance.isValidChildByCode(shapeType);
		assertEquals("child found", true, actualResult);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testIsValidParentByCodeNull() {
		this.testInstance.isValidChildByCode(null);
	}

	@Test
	public final void testGetObjectType() {
		assertEquals("objectType the same", this.testOwningInstance, this.testInstance.getObjectType());
	}

}
