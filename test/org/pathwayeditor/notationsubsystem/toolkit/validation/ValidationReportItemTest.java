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
package org.pathwayeditor.notationsubsystem.toolkit.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

@RunWith(JMock.class)
public class ValidationReportItemTest {
	Mockery mockery = new JUnit4Mockery();
	final ICompoundGraphElement mockDrawingNode = mockery.mock(ICompoundGraphElement.class);
	final IValidationRuleDefinition mockRule = mockery.mock(IValidationRuleDefinition.class);
    private ValidationReportItem reportItem;

	@Test
	public void testValidationReportItem() {
		reportItem = createAValidationReportItem();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidationReportItemDoesNotAllowNullRule() {
		reportItem = new ValidationReportItem(mockDrawingNode, null, null,null);
	}

	@Test
	public void testGetBrokenRule() {
		reportItem = createAValidationReportItem();
		assertEquals(mockRule, reportItem.getBrokenRule());
	}

	@Test
	public void testGetChildReportsNotNull() {
		reportItem = createAValidationReportItem();
		assertNotNull(reportItem.getChildReports());
	}

	@Test
	public void testAddChildReportItem() {
		reportItem = createAValidationReportItem();
		final IValidationReportItem CHILD_ITEM = mockery.mock(IValidationReportItem.class);
		assertEquals(0, reportItem.getChildReports().size());
		assertTrue(reportItem.addChildReportItem(CHILD_ITEM));
		assertEquals(1, reportItem.getChildReports().size());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testReturnedChildReportItemsDoesNotAllowAdditionOfNewReportItems() {
		reportItem = createAValidationReportItem();
		List<IValidationReportItem> childItems = reportItem.getChildReports();
		childItems.add(mockery.mock(IValidationReportItem.class));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testReturnedChildReportItemsDoesNotAllowRemovalOfNewReportItems() {
		reportItem = createAValidationReportItem();
		List<IValidationReportItem> childItems = reportItem.getChildReports();
		childItems.remove(mockery.mock(IValidationReportItem.class));
	}

	private ValidationReportItem createAValidationReportItem() {
		return  new ValidationReportItem(mockDrawingNode, mockRule, IValidationReportItem.Severity.WARNING, "");
	}

	@Test
	public void testGetMapObject() {
		reportItem = createAValidationReportItem();
		assertEquals(mockDrawingNode, reportItem.getInvalidObject());
	}
	
	@Test
	public void testGetSeverity() {
		reportItem = createAValidationReportItem();
		assertEquals(IValidationReportItem.Severity.WARNING, reportItem.getSeverity());
	}
	
	@Test
	public void testEquals() {
		reportItem = createAValidationReportItem();
		assertEquals(reportItem, reportItem);
		IValidationReportItem item2 = createAValidationReportItem();
		assertFalse("not equal", item2.equals(reportItem));
	}
	
	@Test
	public void testHashcode() {
		reportItem = createAValidationReportItem();
		assertEquals(reportItem.hashCode(), reportItem.hashCode());
		IValidationReportItem item2 = createAValidationReportItem();
		assertFalse(item2.hashCode() == reportItem.hashCode());
	}
	
	

}
