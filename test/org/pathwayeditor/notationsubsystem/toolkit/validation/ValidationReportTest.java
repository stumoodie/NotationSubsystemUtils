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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;

@RunWith(JMock.class)
public class ValidationReportTest {
    private ValidationReport report;
    Mockery mockery = new JUnit4Mockery();
	
    final IModel map = mockery.mock(IModel.class);
    final IValidationReportItem item1 = mockery.mock(IValidationReportItem.class);
    List<IValidationReportItem>contents ;
    
	@Before
	public void setUp() throws Exception {
		
	}

	private List<IValidationReportItem> createASingleItemList() {
		   final List<IValidationReportItem> contents = new ArrayList<IValidationReportItem>();
		   contents.add(item1);
		   return contents;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullConstructorMapShouldThrowIllegalArgumentException() {
		report = new ValidationReport(null);	
	}
	
	@Test
	public void testReportRecordsCreationTime(){
		Date BEFORE = Calendar.getInstance().getTime();
		report = new ValidationReport(map);	
		Date AFTER = Calendar.getInstance().getTime();
	
		assertFalse(BEFORE.after(report.getValidationTime()));
		assertFalse(AFTER.before(report.getValidationTime()));
	}
	

	
	@Test
	public void testGetCanvas() {
		report = new ValidationReport(map);	
		assertEquals(map, report.getModel());
	}

	@Test
	public void testGetValidationReportItems() {
		report = new ValidationReport(map);
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
		assertEquals(1, report.getValidationReportItems().size());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetValidationReportItemsIsUnmodifiableForAdding() {
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
	    final IValidationReportItem secondItem = mockery.mock(IValidationReportItem.class);
	    List<IValidationReportItem> items = report.getValidationReportItems();
	    items.add(secondItem);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetValidationReportItemsIsUnmodifiableForRemoving() {
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
	    List<IValidationReportItem> items = report.getValidationReportItems();
	    items.remove(item1);
	}

	

	@Test
	public void testHasWarningsTrueIfThereIsABrokenGuidelineRule() {
		configureReportToHaveGuidelineRule();
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
		assertTrue(report.hasWarnings());
	}

	private void configureReportToHaveGuidelineRule() {
		mockery.checking(new Expectations() {
			{atLeast(1).of(item1).getSeverity(); will(returnValue(IValidationReportItem.Severity.WARNING));}
		
		});
	}
	
	@Test
	public void testHasWarningsFalseIfThereIsNoBrokenGuidelineRule() {
		configureReportToHaveAErrorRule();
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
		assertFalse(report.hasWarnings());
	}

	private void configureReportToHaveAErrorRule() {
		mockery.checking(new Expectations() {
			{atLeast(1).of(item1).getSeverity(); will(returnValue(IValidationReportItem.Severity.ERROR));}
		
		});
	}

	@Test
	public void testIsMapValidIsTrueIfOnlyGuidelinesArePresent() {
		configureReportToHaveGuidelineRule();
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
		assertTrue(report.isValid());
	}
	
	@Test
	public void testMapIsNotValidIfOptionalRuleApplied() {
		configureReportToHaveAnOptionalRuleBroken();
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
		assertFalse(report.isValid());
	}
	
	private void configureReportToHaveAnOptionalRuleBroken() {
		mockery.checking(new Expectations() {
			{atLeast(1).of(item1).getSeverity(); will(returnValue(IValidationReportItem.Severity.ERROR));}
		
		});
	}

	@Test
	public void testMapIsNotValidIfMAndatoryRuleApplied() {
		configureReportToHaveAErrorRule();
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
		assertFalse(report.isValid());
	}

	@Test
	public void testDateIsImmutable() throws Exception{
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
        Date date = report.getValidationTime();
        date.setTime(1000000);
        Date d2 = report.getValidationTime();
        assertFalse(d2.equals(date));
		
	}
	
	@Test
	public void testEquals() {
		report = new ValidationReport(map);	
		for(IValidationReportItem reportItem : createASingleItemList()){
			report.addReportItem(reportItem);
		}
		assertEquals(report ,report);
		assertFalse(report.equals(null));
	}

	

}
