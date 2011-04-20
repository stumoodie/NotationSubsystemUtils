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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

@RunWith(JMock.class)
public class LinkObjectTypeTest {

	private static final String EXPECTED_NOT_EMPTY_DESCN = "not empty";
    private Mockery mockery = new JUnit4Mockery();
	private LinkObjectType lot;
	private int positiveId = 1;
	private int negativeId = -1;
	private String emptyDescription = "";
	private String name = "name";
	private String emptyName = "";
	private INotationSyntaxService iNotationSyntaxService = mockery
			.mock(INotationSyntaxService.class);


	@Before
	public void setUp() {
	    lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
	}
	
	@After
	public void tearDown() {
	    lot = null;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUniqueIdNotPositiveThrowsException() {
		lot = new LinkObjectType(iNotationSyntaxService, negativeId, name);
	}

	@Test
	public void testUniqueIdPositive() {
		lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		assertEquals(positiveId, lot.getUniqueId());
	}

	@Test
	public void testDescriptionEmptyStringValid() {
        lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
        lot.setDescription(emptyDescription);
		assertEquals(emptyDescription, lot.getDescription());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNullDescriptionThrowsException() {
        lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
        lot.setDescription(emptyDescription);
		lot.setDescription(null);
	}

	@Test
	public void testSetDescriptionValid() {
        lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		lot.setDescription(EXPECTED_NOT_EMPTY_DESCN);
		assertEquals(EXPECTED_NOT_EMPTY_DESCN, lot.getDescription());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNameNullThrowsException() {
		lot = new LinkObjectType(iNotationSyntaxService, positiveId, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNameEmptyThrowsException() {
        lot = new LinkObjectType(iNotationSyntaxService, positiveId, emptyName);
	}

	@Test
	public void testNameValid() {
        lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		assertEquals(name, lot.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullNotationSyntaxServiceThrowsException() {
		lot = new LinkObjectType(null, positiveId, name);
	}

	@Test
	public void testNotationSyntaxServiceValid() {
		lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		assertEquals(iNotationSyntaxService, lot.getSyntaxService());
	}

	@Test
	public void testHashCodeUsesSyntaxService() {
		lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		ILinkObjectType sameSyntaxService = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
		ILinkObjectType differentSyntaxService = new LinkObjectType(differentService, positiveId, name);
		assertTrue(lot.hashCode() == sameSyntaxService.hashCode());
		assertFalse(lot.hashCode() == differentSyntaxService.hashCode());
	}

	@Test
	public void testHashCodeUsesUniqueId() {
		lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		ILinkObjectType sameId = new LinkObjectType(iNotationSyntaxService, positiveId, name);
		ILinkObjectType differentId = new LinkObjectType(iNotationSyntaxService, positiveId+1, name);
		assertTrue(lot.hashCode() == sameId.hashCode());
		assertFalse(lot.hashCode() == differentId.hashCode());
	}

	@Test
	public void testEqualsOnId() {
        lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
        ILinkObjectType sameId = new LinkObjectType(iNotationSyntaxService, positiveId, name);
        ILinkObjectType differentId = new LinkObjectType(iNotationSyntaxService, positiveId+1, name);
		assertTrue(lot.equals(sameId));
		assertFalse(lot.equals(differentId));
	}

	@Test
	public void testEqualsOnSyntaxService() {
        lot = new LinkObjectType(iNotationSyntaxService, positiveId, name);
        ILinkObjectType sameSyntaxService = new LinkObjectType(iNotationSyntaxService, positiveId, name);
        INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
        ILinkObjectType differentSyntaxService = new LinkObjectType(differentService, positiveId, name);
		assertTrue(lot.equals(sameSyntaxService));
		assertFalse(lot.equals(differentSyntaxService));
	}

	@Test
	public final void testGetLinkConnectionRulesIsNotNull() {
		assertNotNull(lot.getLinkConnectionRules());
	}

	@Test
	public final void testGetSourceTerminusDefinitionIsNotNull() {
		assertNotNull(lot.getSourceTerminusDefinition());
	}

	@Test
	public final void testGetTargetTerminusDefinitionIsNotNull() {
		assertNotNull(lot.getTargetTerminusDefinition());
	}

    @Test
    public final void testGetLinKAttrributeDefaultsIsNotNull() {
        assertNotNull(lot.getDefaultAttributes());
    }

}
