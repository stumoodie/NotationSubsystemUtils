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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

@RunWith(JMock.class)
public class ShapeObjectTypeTest {
	private Mockery mockery= new JUnit4Mockery();
	private ShapeObjectType shapeObjectType;
	private int positiveId=1;
	private int negativeId=-1;
	private String emptyDescription="";
	private String name="name";
	private String emptyName="";
	private INotationSyntaxService iNotationSyntaxService = mockery.mock(INotationSyntaxService.class);
	
	@Before
	public void setUp() {
	    shapeObjectType = new ShapeObjectType(iNotationSyntaxService, positiveId, name); 
	}
	
	@Test
	public void testParentingRulesAreNotNull(){
		IShapeParentingRules notNull=shapeObjectType.getParentingRules();
		assertNotNull(notNull);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testUniqueIdNotPositiveThrowsException(){
		shapeObjectType=new ShapeObjectType(iNotationSyntaxService, negativeId, name);
	}
	
	@Test
	public void testUniqueIdPositive(){
		assertEquals(positiveId,shapeObjectType.getUniqueId());
	}
	
	@Test
	public void testDescriptionEmptyStringValid(){
	    shapeObjectType.setDescription(emptyDescription);
		assertEquals(emptyDescription,shapeObjectType.getDescription());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNullDescriptionThrowsException(){
		shapeObjectType.setDescription(null);
	}
	
	@Test
	public void testSetDescriptionValid(){
		shapeObjectType.setDescription("not empty");
		assertEquals("not empty",shapeObjectType.getDescription());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNameNullThrowsException(){
		shapeObjectType=new ShapeObjectType(iNotationSyntaxService, positiveId, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNameEmptyThrowsException(){
        shapeObjectType=new ShapeObjectType(iNotationSyntaxService, positiveId, emptyName);
	}
	
	@Test
	public void testNameValid(){
		assertEquals(name,shapeObjectType.getName());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testNullNotationSyntaxServiceThrowsException(){
		shapeObjectType=new ShapeObjectType(null, positiveId, name);
	}
	
	@Test
	public void testNotationSyntaxServiceValid(){
		assertEquals(iNotationSyntaxService,shapeObjectType.getSyntaxService());
	}
	
	@Test
	public void testHashCodeUsesSyntaxService() {
		IShapeObjectType sameSyntaxService = new ShapeObjectType(iNotationSyntaxService, positiveId, name);
		INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
		IShapeObjectType differentSyntaxService = new ShapeObjectType(differentService, positiveId, name);
		assertTrue(shapeObjectType.hashCode()==sameSyntaxService.hashCode());
		assertFalse(shapeObjectType.hashCode()==differentSyntaxService.hashCode());
	}
	
	@Test
	public void testHashCodeUsesUniqueId() {
		IShapeObjectType sameId = new ShapeObjectType(iNotationSyntaxService, positiveId, name);
		IShapeObjectType differentId = new ShapeObjectType(iNotationSyntaxService, positiveId+1, name);
		assertTrue(shapeObjectType.hashCode()==sameId.hashCode());
		assertFalse(shapeObjectType.hashCode()==differentId.hashCode());
	}
	
	@Test
	public void testEqualsOnId(){
        IShapeObjectType sameId = new ShapeObjectType(iNotationSyntaxService, positiveId, name);
        IShapeObjectType differentId = new ShapeObjectType(iNotationSyntaxService, positiveId+1, name);
		assertTrue(shapeObjectType.equals(sameId));
		assertFalse(shapeObjectType.equals(differentId));
	}
	
	@Test
	public void testEqualsOnSyntaxService(){
        IShapeObjectType sameSyntaxService = new ShapeObjectType(iNotationSyntaxService, positiveId, name);
        INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
        IShapeObjectType differentSyntaxService = new ShapeObjectType(differentService, positiveId, name);
		assertTrue(shapeObjectType.equals(sameSyntaxService));
		assertFalse(shapeObjectType.equals(differentSyntaxService));
	}
	

}
