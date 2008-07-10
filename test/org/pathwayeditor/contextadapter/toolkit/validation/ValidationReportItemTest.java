package org.pathwayeditor.contextadapter.toolkit.validation;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjectsAPI.IMapObject;
import org.pathwayeditor.contextadapter.publicapi.IValidationReportItem;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;

@RunWith(JMock.class)
public class ValidationReportItemTest {
	Mockery mockery = new JUnit4Mockery();
	final IMapObject mockIMO = mockery.mock(IMapObject.class);
	final IValidationRuleDefinition mockRule = mockery.mock(IValidationRuleDefinition.class);
    private ValidationReportItem reportItem;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidationReportItem() {
		reportItem = createAValidationReportItem();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidationReportItemDoesNotAllowNullRule() {
		reportItem = new ValidationReportItem(mockIMO, null, null,null);
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
		return  new ValidationReportItem(mockIMO, mockRule, IValidationReportItem.Severity.WARNING, "");
	}

	@Test
	public void testGetMapObject() {
		reportItem = createAValidationReportItem();
		assertEquals(mockIMO, reportItem.getMapObject());
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
		assertEquals(item2, reportItem);
	}
	
	@Test
	public void testHashcode() {
		reportItem = createAValidationReportItem();
		assertEquals(reportItem.hashCode(), reportItem.hashCode());
		IValidationReportItem item2 = createAValidationReportItem();
		assertEquals(item2.hashCode(), reportItem.hashCode());
	}
	
	

}
